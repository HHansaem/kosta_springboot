package com.kosta.sec.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	private JwtToken jwtToken = new JwtToken();
	
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
		if(authentication == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
			return;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> token  = objectMapper.readValue(authentication, Map.class);  //jsonString -> map
		
		//accessToken validate check
		String access_token = token.get("access_token");
		if(!access_token.startsWith(JwtProperties.TOKEN_PREFIX)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
			return;
		}
		
		access_token = access_token.replace(JwtProperties.TOKEN_PREFIX, "");
		try {  //access_token 타당성 체크
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
					.build()
					.verify(access_token)  //만료시간인지 자동체크
					.getClaim("sub")
					.asString();
			System.out.println(username);
			if(username == null || username.equals("")) throw new Exception();  //사용자가 없을 때
			
			//Database에서 사용자를 검색하여 UserDetails 타입으로 변환, 다시 Authentication 타입으로 변환하여 
			//security session에 넣는다.
			User user = userRepository.findByUsername(username);
			if(user == null) throw new Exception();  //사용자가 없을 때
			
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					principalDetails, null, principalDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response);
			return;
		} catch (JWTVerificationException ve) {  //access_token 시간 만료시 refresh_token 체크
			ve.printStackTrace();
			try {
				String refresh_token = token.get("refresh_token");
				if(!refresh_token.startsWith(JwtProperties.TOKEN_PREFIX)) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
					return;
				}
				refresh_token = refresh_token.replace(JwtProperties.TOKEN_PREFIX, "");
				
				String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
						.build()
						.verify(refresh_token)
						.getClaim("sub")
						.asString();
				
				User user = userRepository.findByUsername(username);
				if(user == null) throw new Exception();  //사용자가 없을 때
				String reAccess_token = jwtToken.makeAccessToken(username);
				String reRefresh_token = jwtToken.makeRefreshToken(username);
				Map<String, String> map = new HashMap<>();
				map.put("access_token", JwtProperties.TOKEN_PREFIX+reAccess_token);
				map.put("refresh_token", JwtProperties.TOKEN_PREFIX+reRefresh_token);
				
				String reToken = objectMapper.writeValueAsString(map);
				response.addHeader(JwtProperties.HEADER_STRING, reToken);
				response.setContentType("application/json; charset=utf-8");
			} catch (Exception e2) {
				e2.printStackTrace();
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰 오류");
		}
		
		//토큰이 없거나 Bearer가 아니거나
//		String authentication = request.getHeader(JwtProperties.HEADER_STRING);
//		if(authentication == null || !authentication.startsWith(JwtProperties.TOKEN_PREFIX)) {
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
//			return;
//		}
		
		//Bearer 제거
//		String token = authentication.replace(JwtProperties.TOKEN_PREFIX, "");
//		System.out.println(token);
//		try {
//			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
//					.build()
//					.verify(token)  //만료시간인지 자동체크
//					.getClaim("sub")
//					.asString();
//			System.out.println(username);
//			
//			//Database에서 사용자를 검색하여 UserDetails 타입으로 변환, 다시 Authentication 타입으로 변환하여 
//			//security session에 넣는다.
//			User user = userRepository.findByUsername(username);
//			PrincipalDetails principalDetails = new PrincipalDetails(user);
//			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//					principalDetails, null, principalDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(auth);
//			chain.doFilter(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰 에러");
//		}
	}

}
