package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.BankRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private BankRepository bankRepository;

	@Override
	public void join(MemberDto memberDto) throws Exception {
		bankRepository.insertMember(memberDto.toMember());
	}

	@Override
	public Boolean checkMemberDoubleId(String id) throws Exception {
		Member acc = bankRepository.findMemberById(id);
		return acc != null;
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Member member = bankRepository.findMemberById(id);
		if(member == null) throw new Exception("아이디 오류");
		if(!member.getPassword().equals(password)) throw new Exception("비밀번호 오류");
		return member.toMemberDto();
	}

}
