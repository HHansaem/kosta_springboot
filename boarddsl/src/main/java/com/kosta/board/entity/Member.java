package com.kosta.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	
}
