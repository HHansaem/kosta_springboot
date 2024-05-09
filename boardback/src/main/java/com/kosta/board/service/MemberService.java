package com.kosta.board.service;

import com.kosta.board.dto.MemberDto;

public interface MemberService {
	void join(MemberDto member) throws Exception;
	Boolean checkDoubleId(String id) throws Exception;
	Boolean checkDoubleNickName(String id) throws Exception;
	MemberDto login(String id, String password) throws Exception;
}
