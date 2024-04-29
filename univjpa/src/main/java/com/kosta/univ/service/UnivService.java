package com.kosta.univ.service;

import java.util.List;

import com.kosta.univ.entity.Student;

public interface UnivService {
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
}
