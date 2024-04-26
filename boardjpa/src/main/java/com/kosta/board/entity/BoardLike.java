package com.kosta.board.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardLike {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT일 때 써줌
	private Integer num;
	
	@ManyToOne(fetch = FetchType.EAGER)  //앞에거가(Many) 나(BoardLike)
	@JoinColumn(name = "memberId")
	private Member member;

	@ManyToOne(fetch = FetchType.EAGER)  //Board(One)에서도 BoardLike(Many) 정보를 가지고 있어야 함
	@JoinColumn(name = "boardNum")
	private Board board;
	
	@Override
	public String toString() {
		return String.format("[%d, %s, %d]", num, member.getId(), board.getNum());
	}
	
}
