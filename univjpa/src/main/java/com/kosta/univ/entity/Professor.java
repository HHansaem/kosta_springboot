package com.kosta.univ.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Integer pay;
	
	@Column
//	@CreationTimestamp
	private Date hiredate;
	
	@Column
	private Integer bonus;
	
	@Column
	private String email;
	
	@Column
	private String hpage;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno")
	private Department department;
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
	private List<Student> studList = new ArrayList<>();

	@Override
	public String toString() {
		return "Professor [profno=" + profno + ", name=" + name + ", id=" + id + ", position=" + position + ", pay="
				+ pay + ", hiredate=" + hiredate + ", bonus=" + bonus + ", email=" + email + ", hpage=" + hpage
				+ ", department=" + department + "]";
	}
	
}
