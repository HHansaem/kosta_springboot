package com.kosta.univ.dto;

import com.kosta.univ.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepartmentDto {
	private Integer deptno;
	private String dname;
	private String part;
	private String build;
	
	public Department toEntity() {
		return Department.builder()
						.deptno(deptno)
						.dname(dname)
						.part(part)
						.build(build)
						.build();
	}
}
