package com.kosta.univ.dto;

import java.util.ArrayList;
import java.util.List;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

public class ConvertMapper {
	public static List<StudentDto> studListToStudDtoList(List<Student> studList) {
		if(studList == null || studList.size() == 0) return null;
		
		List<StudentDto> studDtoList = new ArrayList<>();
		for(Student stud : studList) {
			studDtoList.add(stud.toDto());
		}
		return studDtoList;
	}
	
	public static List<ProfessorDto> profListToProfDtoList(List<Professor> profList) {
		if(profList == null || profList.size() == 0) return null;
		
		List<ProfessorDto> profDtoList = new ArrayList<>();
		for(Professor prof : profList) {
			profDtoList.add(prof.toDto());
		}
		return profDtoList;
	}
	
	public static List<DepartmentDto> deptListToDeptDtoList(List<Department> deptList) {
		if(deptList == null || deptList.size() == 0) return null;
		
		List<DepartmentDto> deptDtoList = new ArrayList<>();
		for(Department dept : deptList) {
			deptDtoList.add(dept.toDto());
		}
		return deptDtoList;
	}
}
