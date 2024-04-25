package com.kosta.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="FILE")  //table의 이름은 FILE로
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String directory;
	
	@Column
	private String name;
	
	@Column
	private Long size;
	
	@Column
	private String contenttype;
	
	@Column
	@CreationTimestamp
	private Date uploaddate;
	
}
