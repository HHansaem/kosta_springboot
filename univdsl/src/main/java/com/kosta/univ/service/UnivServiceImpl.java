package com.kosta.univ.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.repository.UnivRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final DepartmentRepository departmentRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;
	private final UnivRepository univRepository;

	@Override
	public void enterStudent(StudentDto stud) throws Exception {
		
	}

	@Override
	public StudentDto getStudentByNo(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStudentByNoWithDname(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStudentByNoWithProfName(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStudentByNoWithDnameAndProfName(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentByDeptno(Integer deptno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDname(String dname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByGrade(Integer grade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDnameAndGrade(String dname, Integer grade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno1, Integer deptno2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDname1OrDname2(String dname1, String dname2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByDname1OrDname2(String dname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> getStudentListByProfNo(Integer profno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
