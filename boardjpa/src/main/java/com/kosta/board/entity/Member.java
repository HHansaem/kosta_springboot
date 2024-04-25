package com.kosta.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	//LAZY: boardList를 요청할 떄만 쿼리에서 가져오겠다
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)  //일대다관계
	private List<Board> boardList = new ArrayList<>();
}
