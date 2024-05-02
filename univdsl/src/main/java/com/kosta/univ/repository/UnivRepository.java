package com.kosta.univ.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UnivRepository {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
}
