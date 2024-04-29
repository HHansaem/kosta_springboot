package com.kosta.univ.dto;

import java.sql.Date;

import org.modelmapper.ModelMapper;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDto {
	private Integer profno;
	private String name;
	private String id;
	private String position;
	private Integer pay;
	private Date hiredate;
	private Integer bonus;
	private String email;
	private String hpage;
	private Integer deptno;
	private String deptName;
	
	public Professor toEntity() {
		//ModelMapper는 map(this, Professor.class)에서 this(ProfessorDto의 변수)를 Professor.class의 변수로 알아서 매핑
		ModelMapper modelMapper = new ModelMapper();
		Professor professor = modelMapper.map(this, Professor.class);
		professor.setDepartment(Department.builder().deptno(deptno).build());
		return professor;
	}
	
	
	
	
	
}
