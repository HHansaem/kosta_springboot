package com.kosta.sec.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

//인가 : 로그인 처리가 되어야만 하는 요청이 들어왔을 때 실행
public class JwtAuthrizationFilter extends BasicAuthenticationFilter {
	
	private UserRepository userRepository;
	
	public JwtAuthrizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		
		//로그인(인증)이 필요없는 요청은 그대로 진행
		if(!(uri.contains("/user") || uri.contains("/admin") || uri.contains("/manager"))) {
			chain.doFilter(request, response);
			return;
		}
		
		//토큰이 없거나 Bearer가 아니거나
		String authentication = request.getHeader(JwtProperties.HEADER_STRING);
		if(authentication == null || !authentication.startsWith(JwtProperties.TOKEN_PREFIX)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
			return;
		}
		
		//Bearer 제거
		String token = authentication.replace(JwtProperties.TOKEN_PREFIX, "");
		System.out.println(token);
		try {
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
					.build()
					.verify(token)  //만료시간인지 자동체크
					.getClaim("sub")
					.asString();
			System.out.println(username);
			
			//Database에서 사용자를 검색하여 UserDetails 타입으로 변환, 다시 Authentication 타입으로 변환하여 
			//security session에 넣는다.
			User user = userRepository.findByUsername(username);
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					principalDetails, null, principalDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰 에러");
		}
	}

}
