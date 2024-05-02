package com.kosta.univ.config;

import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration  //설정파일
public class QuerydslConfig {
	@Autowired
	EntityManager entityManager;
	
	@Bean  //자동 생성되는 (자주 쓰는 것들은 이렇게 Bean으로 생성해줌,, 객체 생성 후 리턴)
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
