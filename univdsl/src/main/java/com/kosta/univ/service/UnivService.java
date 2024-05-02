package com.kosta.univ.service;

import java.util.List;
import java.util.Map;

import com.kosta.univ.dto.StudentDto;

public interface UnivService {
	//학생-----------------------------
	//학생입학
	void enterStudent(StudentDto
			stud) throws Exception;
	//학번으로 학생정보 조회
	StudentDto getStudentByNo(Integer studno) throws Exception;
	//학번으로 학생정보 조회(학과명 포함)
	Map<String , Object> getStudentByNoWithDname(Integer studno) throws Exception;
	//학번으로 학생정보 조회(담당교수명 포함)
	Map<String , Object> getStudentByNoWithProfName(Integer studno) throws Exception;
	//학번으로 학생정보 조회(학과명, 담당교수명 포함)
	Map<String , Object> getStudentByNoWithDnameAndProfName(Integer studno) throws Exception;
	//학생이름으로 학생 정보 조회
	List<StudentDto> getStudentByName(String name) throws Exception;
	//특정학과 학생 조회(학과번호로)
	List<StudentDto> getStudentByDeptno(Integer deptno) throws Exception;
	//특정학과 학생 조회(학과명으로)
	List<StudentDto> getStudentListByDname(String dname) throws Exception;
	//특정학년 학생 조회
	List<StudentDto> getStudentListByGrade(Integer grade) throws Exception;
	//특정학과, 특정학년 학생 조회
	List<StudentDto> getStudentListByDnameAndGrade(String dname, Integer grade) throws Exception;
	
	//주전공이 deptno1이거나 부전공이 deptno2인 학생 조회
	List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno1, Integer deptno2) throws Exception;
	//주전공이나 부전공이 특정학과인 학생 조회(학과번호로)
	List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno) throws Exception;
	//주전공이 dname1이거나 부전공이 dname2인 학생 조회
	List<StudentDto> getStudentListByDname1OrDname2(String dname1, String dname2) throws Exception;
	//주전공이나 부전공이 특정학과인 학생 조회(학과명으로)
	List<StudentDto> getStudentListByDname1OrDname2(String dname) throws Exception;
	//특정 교수가 담당하는 학생목록 조회
	List<StudentDto> getStudentListByProfNo(Integer profno) throws Exception;
	
}
