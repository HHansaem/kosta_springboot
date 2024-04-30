package com.kosta.board.service;

import org.springframework.stereotype.Service;

import com.kosta.board.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Override
	public void join(MemberDto member) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean checkDoubleId(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkDoubleNickName(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDto login(String id, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
