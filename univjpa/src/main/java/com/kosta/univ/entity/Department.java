package com.kosta.univ.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Department {
	
	@Id
	private Integer deptno;
	
	@Column
	private String dname;
	
	@Column
	private Integer part;
	
	@Column
	private String build;
	
}
