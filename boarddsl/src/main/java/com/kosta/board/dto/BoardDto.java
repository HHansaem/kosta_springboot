package com.kosta.board.dto;

import java.sql.Date;

import com.kosta.board.entity.Board;

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
	private Integer fileNum;
	private Integer viewCount;
	private Integer likeCount;
	private Date writeDate;
	private Date modifyDate;
	private String writer;
	
	//BoardDto의 변수들을 Board로 변환해주는 함수
	public Board toBoard() {  //원하는 파라미터만 넣어서 생성 가능 (프론트에서 가져옴)
		return Board.builder()
					.num(num)
					.subject(subject)
					.content(content)
					.fileNum(fileNum)
					.viewCount(viewCount)
					.likeCount(likeCount)
					.writeDate(writeDate)
					.modifyDate(modifyDate)
					.writer(writer)
					.build();
	}
}