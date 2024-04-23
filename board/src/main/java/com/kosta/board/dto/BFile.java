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
public class BFile {
	private Integer num;
	private String directory;
	private String name;
	private Long size;
	private String contenttype;
	private Date uploaddate;
	
}
