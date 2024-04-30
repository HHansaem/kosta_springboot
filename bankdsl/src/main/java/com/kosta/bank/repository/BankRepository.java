package com.kosta.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.entity.QAccount;
import com.kosta.bank.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
//동적쿼리 (select, from, where을 메서드 형식으로 씀)
public class BankRepository {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	//계좌개설
	public void insertAccount(Account acc) {
		accountRepository.save(acc);
	}
	
	//계좌조회
	public Account findAccountById(String id) {
		QAccount account = QAccount.account;
		return jpaQueryFactory.select(account)
							.from(account)
							.where(account.id.eq(id))
							.fetchOne();
	}
	
	@Transactional
	public void updateBalance(String id, Integer balance) {
		QAccount account = QAccount.account;
		jpaQueryFactory.update(account)
						.set(account.balance, balance)
						.where(account.id.eq(id))
						.execute();
		entityManager.flush();
		entityManager.clear();
	}
	
	//전체계좌조회
	public List<Account> findAllAccount(){
		QAccount account = QAccount.account;
		//select랑 from이랑 같을 때 : selectFrom, 여러개를 조회할 떄는 fetchOne이 아니라 fetch
		return jpaQueryFactory.selectFrom(account).fetch();
	}
	
	//계좌이체
	@Transactional
	public void transfer(String sid, String rid, Integer sbalance, Integer rbalance) {
		QAccount sacc = new QAccount("account1");
		QAccount racc = new QAccount("account2");
		
		jpaQueryFactory.update(sacc)
					.set(sacc.balance, sbalance).where(sacc.id.eq(sid));
		jpaQueryFactory.update(racc)
					.set(racc.balance, rbalance).where(sacc.id.eq(rid));
	}
	
	
	//회원가입
	public void insertMember(Member member) {
		memberRepository.save(member);
	}
	
	//회원조회(로그인)
	public Member findMemberById(String id) {
		QMember member = QMember.member;
		return jpaQueryFactory.select(member)
							.from(member)
							.where(member.id.eq(id))
							.fetchOne();
	}
	
}
