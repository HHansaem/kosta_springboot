package com.kosta.bank.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		Member member = modelMapper.map(memberDto, Member.class);  //memberDto의 멤버변수를 Member의 멤버변수와 같은 이름을 가진 것만 변환
		memberRepository.save(member);
	}

	@Override
	public Boolean checkMemberDoubleId(String id) throws Exception {
		Optional<Member> omem = memberRepository.findById(id);
		if(omem.isEmpty()) return false;
		return true;
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Optional<Member> omember = memberRepository.findById(id);
		if(omember.isEmpty()) throw new Exception("아이디 오류");
		Member member = omember.get();
		if(!member.getPassword().equals(password.trim())) throw new Exception("비밀번호 오류");
		return modelMapper.map(member, MemberDto.class);
	}
}
