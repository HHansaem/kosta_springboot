package com.kosta.board.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	//EAGER: member를 항상 같이 가져오겠다
	@ManyToOne(fetch = FetchType.EAGER)  //다대일관계(앞에거가(Many) 나(Board))
	@JoinColumn(name = "writer")  //Member의 기본키를 writer라는 이름으로 join하겠다 (실제 객체는 Member 정보 다 가지고 있음)
	private Member member;
	
	//자신(Board)이 One이니까 Many인 BoardLike를 List형식으로 갖고 있어야 함
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)  
	private List<BoardLike> boardLikeList = new ArrayList<>();
	
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
						.writer(member.getId())
						.nickName(member.getNickName())
						.build();
	}
	
}
