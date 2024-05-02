package com.kosta.univ.service;

import java.util.List;
import java.util.Map;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;

public interface UnivService {
	//학생------------------------------------
	//학생입학
	void enterStudent(StudentDto stud) throws Exception;
	//학번으로 학생정보 조회
	StudentDto getStudentByNo(Integer studno) throws Exception;
	//학번으로 학생정보 조회(학과명 포함)
	Map<String , Object> getStudentByNoWithDname(Integer studno) throws Exception;
	//학번으로 학생정보 조회(담당교수명 포함)
	Map<String , Object> getStudentByNoWithProfName(Integer studno) throws Exception;
	//학번으로 학생정보 조회(학과명, 담당교수명 포함)
	Map<String , Object> getStudentByNoWithDnameAndProfName(Integer studno) throws Exception;
	//학생이름으로 학생 정보 조회
	List<StudentDto> getStudentListByName(String name) throws Exception;
	//특정학과 학생 조회(학과번호로)
	List<StudentDto> getStudentListByDeptno(Integer deptno) throws Exception;
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
	
	
	
	//교수------------------------------------
	//교수 입사
	void enterProfessor(ProfessorDto prof) throws Exception;
	//교수번호로 교수정보 조회
	ProfessorDto getProfessorByProfno(Integer profno) throws Exception;
	//교수명으로 교수정보 조회
	List<ProfessorDto> getProfessorByProfName(String profName) throws Exception;
	//교수번호로 교수정보 조회(학과명 포함)
	Map<String, Object> getProfessorByProfnoWithDname(Integer profno) throws Exception;
	//학생의 담당교수 조회
	ProfessorDto getProfessorByStudno(Integer studno) throws Exception;
	//특정학과 교수 정보조회(학과번호로)
	List<ProfessorDto> getProfessorByDeptno(Integer deptno) throws Exception;
	//특정학과 교수 정보조회(학과명으로)
	List<ProfessorDto> getProfessorByDeptName(String dname) throws Exception;

	
	
	//학과------------------------------------
	//학과신설
	void foundDepartment(DepartmentDto dept) throws Exception;
	//학과번호로 학과조회
	DepartmentDto getDepartmentByDeptno(Integer deptno) throws Exception;
	//학과명으로 학과조회
	DepartmentDto getDepartmentByDname(String dname) throws Exception;
	//학번으로 학과조회
	DepartmentDto getDepartmentByStudNo(Integer studno) throws Exception;
	//건물로 학과조회
	List<DepartmentDto> getDepartmentByBuild(String build) throws Exception;
	
}
