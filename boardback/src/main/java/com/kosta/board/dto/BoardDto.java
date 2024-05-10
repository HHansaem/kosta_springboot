package com.kosta.board.dto;

import java.sql.Date;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {
	private Integer num;
	private String subject;
	private String content;
	private String fileNums;
	private Integer viewCount;
	private Integer likeCount;
	private Date writeDate;
	private Date modifyDate;
	private String writer;
	private String nickName;
	
	//BoardDto의 변수들을 Board로 변환해주는 함수
	public Board toBoard() {  //원하는 파라미터만 넣어서 생성 가능 (프론트에서 가져옴)
		return Board.builder()
					.num(num)
					.subject(subject)
					.content(content)
					.fileNums(fileNums)
					.viewCount(viewCount)
					.likeCount(likeCount)
					.writeDate(writeDate)
					.modifyDate(modifyDate)
					.member(Member.builder().id(writer).build())  //Member.builder 사용하려면 Member에서 @Builder 필요
					.build();
	}
}