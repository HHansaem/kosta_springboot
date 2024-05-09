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
	
	//자신(Member)이 One이니까 Many인 BoardLike를 List형식으로 갖고 있어야 함
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<BoardLike> boardLikeList = new ArrayList<>();
	
}
