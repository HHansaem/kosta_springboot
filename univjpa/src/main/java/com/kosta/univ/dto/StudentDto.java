package com.kosta.univ.dto;

import java.sql.Date;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {  //사용자에게 보여줄 화면
	private Integer studno;
	private String name;
	private String id;
	private Integer grade;
	private String jumin;
	private Date birthday;
	private String tel;
	private Integer height;
	private Integer weight;
	private Integer profno;
	private String profName;
	private Integer deptno1;
	private Integer deptno2;
	private String dept1Name;
	private String dept2Name;
	
	public Student toEntity() {
		Student stud = Student.builder()
					.studno(studno)
					.name(name)
					.id(id)
					.grade(grade)
					.jumin(jumin)
					.birthday(birthday)
					.jumin(jumin)
					.tel(tel)
					.height(height)
					.weight(weight)
					.build();
		if(profno != null) {
			stud.setProfessor(Professor.builder().profno(profno).build());
		}
		if(deptno1 != null) {
			stud.setDepartment1(Department.builder().deptno(deptno1).build());
		}
		if(deptno2 != null) {
			stud.setDepartment2(Department.builder().deptno(deptno2).build());
		}
		return stud;
	}
	
}
