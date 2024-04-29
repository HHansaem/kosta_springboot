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

import org.modelmapper.ModelMapper;

import com.kosta.univ.dto.ProfessorDto;

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
	
	public ProfessorDto toDto() {
		//ModelMapper는 map(this, Professor.class)에서 this(Professor의 변수)를 ProfessorDto.class의 변수로 알아서 매핑
		ModelMapper modelMapper = new ModelMapper();
		ProfessorDto professorDto = modelMapper.map(this, ProfessorDto.class);
		professorDto.setDeptno(department.getDeptno());
		professorDto.setDeptName(department.getDname());
		return professorDto;
	}
	
}
