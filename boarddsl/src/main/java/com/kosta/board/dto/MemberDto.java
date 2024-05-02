package com.kosta.board.dto;

import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {
	private String id;
	private String name;
	private String nickName;
	private String password;
	private String email;
	private String address;
	private String detailAddress;
	
	public Member toEntity() {
		return Member.builder()
					.id(id)
					.name(name)
					.nickName(nickName)
					.password(password)
					.email(email)
					.address(address)
					.detailAddress(detailAddress)
					.build();
	}
}
