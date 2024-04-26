package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Professor {
	
	@Id
	private Integer profno;
	
	@Column
	private String name;
	
	@Column
	private String id;
	
	@Column
	private String position;
	
	@Column
	private Integer pqy;
	
	@Column
//	@CreationTimestamp
	private Date hiredate;
	
	@Column
	private Integer bonus;
	
	@Column
	private String email;
	
	@Column
	private String hpage;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno")
	private Department department;
	
}
