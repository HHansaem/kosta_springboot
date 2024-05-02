package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	@Id
	private Integer studno;
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private String jumin;
	@Column
	private Date birthday;
	@Column
	private String tel;
	@Column
	private Integer grade;
	@Column
	private Integer hight;
	@Column
	private Integer weight;
	@Column
	private Integer deptno1;
	@Column
	private Integer deptno2;
	@Column
	private Integer profno;
	
}
