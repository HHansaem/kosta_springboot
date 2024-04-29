package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Student {
	
	@Id
	private Integer studno;
	
	@Column
	private String name;
	
	@Column
	private Integer grade;
	
	@Column
	private String jumin;
	
	@Column
	private Date birthday;
	
	@Column
	private String tel;
	
	@Column
	private Integer height;
	
	@Column
	private Integer weight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profno")
	private Professor professor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptno1")
	private Department department1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deptno2")
	private Department department2;
	
	@Override
	public String toString() {
		return "Student [studno=" + studno + ", name=" + name + ", grade=" + grade + ", jumin=" + jumin + ", birthday="
				+ birthday + ", tel=" + tel + ", height=" + height + ", weight=" + weight + "]";
	}
	
}
