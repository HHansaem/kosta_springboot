package com.kosta.bank.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.entity.QAccount;
import com.kosta.bank.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BankRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	//동적쿼리 (select, from, where을 메서드 형식으로 씀)
	
	//계좌개설
	
	//계좌조회
	public Account findAccountById(String id) {
		QAccount account = QAccount.account;
		return jpaQueryFactory.select(account)
						.from(account)
						.where(account.id.eq(id))
						.fetchOne();
	}
	
	//입금
	
	//출금
	
	//전체계좌조회
	
	
	//회원가입
	
	//회원조회(로그인)
	public Member findMemberById(String id) {
		QMember member = QMember.member;
		return jpaQueryFactory.select(member)
							.from(member)
							.where(member.id.eq(id))
							.fetchOne();
	}
	
	
}
