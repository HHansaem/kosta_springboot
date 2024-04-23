package com.kosta.board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private Integer num;
	private String subject;
	private String content;
	private Date writedate;
	private Integer filenum;
	private String writer;
	private Integer viewcount;
	private Integer likecount;
}
