package com.kosta.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.board.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private String nickName;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String detailAddress;
	
	public MemberDto toDto() {
		return MemberDto.builder()
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
