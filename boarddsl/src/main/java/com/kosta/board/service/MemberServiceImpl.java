package com.kosta.board.service;

import java.util.Optional;import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void join(MemberDto memberDto) throws Exception {
		Optional<Member> omember = memberRepository.findById(memberDto.getId());
		if(omember.isPresent()) throw new Exception("아이디 중복 오류");
		memberRepository.save(memberDto.toEntity());
	}

	@Override
	public Boolean checkDoubleId(String id) throws Exception {
		Optional<Member> omember = memberRepository.findById(id);
		return omember.isPresent();
	}

	@Override
	public Boolean checkDoubleNickName(String nickName) throws Exception {
		Optional<Member> omember = memberRepository.findByNickName(nickName);
		return omember.isPresent();
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Optional<Member> omember = memberRepository.findById(id);
		if(omember.isEmpty()) throw new Exception("아이디 오류");
		Member member = omember.get();
		if(!member.getPassword().equals(password.trim())) {
			throw new Exception("비밀번호 오류");
		}
		return member.toDto();
	}

}
