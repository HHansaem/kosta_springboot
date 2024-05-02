package com.kosta.univ.dto;

import java.sql.Date;

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
	private String id;
	private String name;
	private String email;
	private String position;
	private Date hiredate;
	private String hpage;
	private Integer pay;
	private Integer bonus;
	private Integer deptno;
}
