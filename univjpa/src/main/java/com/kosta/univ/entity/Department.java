package com.kosta.univ.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptno;
	
	@Column
	private String dname;
	
	@Column
	private Integer part;
	
	@Column
	private String build;
	
	@OneToMany(mappedBy = "deptno1", fetch = FetchType.LAZY)
	private List<Student> studList1 = new ArrayList<>();

	@OneToMany(mappedBy = "deptno2", fetch = FetchType.LAZY)
	private List<Student> studList2 = new ArrayList<>();
	
}
