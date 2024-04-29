package com.kosta.univ.service;

import java.util.List;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

public interface UnivService {
	//학과 등록
	void saveDepartment(DepartmentDto deptDto) throws Exception;
	//학생 등록
	void saveStudent(StudentDto studDto) throws Exception;
	
	////// 조회
	//학생
	//학생 이름으로 학생목록 조회
	List<Student> studentListByName(String studName) throws Exception;
	
	//제1전공으로 학생목록 조회
	List<Student> studentListInDept1ByDeptName(String deptName) throws Exception;
	List<Student> studentListInDept1ByDeptNo(Integer deptNo) throws Exception;

	//제2전공으로 학생목록 조회
	List<Student> studentListInDept2ByDeptName(String deptName) throws Exception;
	List<Student> studentListInDept2ByDeptNo(Integer deptNo) throws Exception;
	
	//학년으로 학생목록 조회
	List<Student> studentListByGrade(Integer grade) throws Exception;
	
	//담당교수가 없는 학생목록 조회
	List<Student> studentListByNoProfessor() throws Exception;
	
	//학번으로 학생 조회
	Student studentByStudentno(Integer studno) throws Exception;
	
	//주민번호로 학생 조회
	Student studentByJumin(String jumin) throws Exception;
	
	
	//교수
	//교수번호로 담당 학생목록 조회
	List<Student> studentListByProfNo(Integer profNo) throws Exception;
	
	//교수번호로 교수 조회
	Professor professorByProfNo(Integer profNo) throws Exception;
	
	//교수이름으로 교수목록 조회
	List<Professor> professorListByProfName(String profName) throws Exception;
	
	//학과번호로 교수목록 조회
	List<Professor> professorListByDeptNo(Integer deptNo) throws Exception;
	
	//학과이름으로 교수목록 조회
	List<Professor> professorListByDeptName(String deptName) throws Exception;
	
	//직급으로 교수목록 조회
	List<Professor> professorListByPosition(String position) throws Exception;
	
	
	//학과
	//학과번호로 학과 조회
	Department departmentByDeptNo(Integer deptNo) throws Exception;
	
	//학과이름으로 학과 조회
	Department departmentByDeptName(String deptName) throws Exception;
	
	//학과(part)로 학과 목록 조회
	List<Department> departmentListByPart(String part) throws Exception;
	
	//위치하는 건물로 학과목록 조회
	List<Department> departmentListByBuild(String build) throws Exception;
	
	
	
}
