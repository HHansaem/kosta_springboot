package com.kosta.bank.dto;

import com.kosta.bank.entity.Member;

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
	private String password;
	private String email;
	private String address;
	private String detailAddress;
	
	public Member toMember() {
		return Member.builder()
					.id(id)
					.name(name)
					.password(password)
					.email(email)
					.address(address)
					.detailAddress(detailAddress)
					.build();
	}
}
