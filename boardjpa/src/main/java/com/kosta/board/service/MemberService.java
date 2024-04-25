package com.kosta.board.service;

import com.kosta.board.dto.MemberDto;

public interface MemberService {
	void join(MemberDto member) throws Exception;
	Boolean checkMemberDoubleId(String id) throws Exception;
	void login(String id, String password) throws Exception;
}
