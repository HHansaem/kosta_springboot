package com.kosta.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
	private String id;
	private String name;
	private String nickName;
	private String password;
	private String email;
	private String address;
	private String detailAddress;
}
