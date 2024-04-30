package com.kosta.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import com.kosta.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicInsert  //@ColumnDefault를 설정하면 null을 허용하지 않음. null을 허용하려면 @DynamicInsert 필요
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO_INCREMENT일 때 써줌
	private Integer num;
	
	@Column
	private String subject;
	
	@Column
	private String content;
	
	@Column
	private Integer fileNum;
	
	@Column
	@ColumnDefault("0")  //기본값 설정
	private Integer viewCount;
	
	@Column
	@ColumnDefault("0")
	private Integer likeCount;
	
	@Column
	@CreationTimestamp  //현재날짜
	private Date writeDate;
	
	@Column
	@UpdateTimestamp
	private Date modifyDate;
	
	@Column
	private String writer;
	
	public BoardDto toBoardDto() {  //DB에서 가져옴
		return BoardDto.builder()
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
