package com.kosta.sec.config.jwt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.sec.config.auth.PrincipalDetails;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {  //로그인 시 실행됨
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	private JwtToken jwtToken = new JwtToken();
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		String accessToken = jwtToken.makeAccessToken(principalDetails.getUsername());
		String refreshToken = jwtToken.makeRefreshToken(principalDetails.getUsername());
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> map = new HashMap<>();
		map.put("access_token", JwtProperties.TOKEN_PREFIX+accessToken);
		map.put("refresh_token", JwtProperties.TOKEN_PREFIX+refreshToken);
		
		String token = objectMapper.writeValueAsString(map);  //map -> jsonString
		System.out.println(token);
		
		response.addHeader(JwtProperties.HEADER_STRING, token);
		response.setContentType("application/json; charset=utf-8");
	}
	
}
