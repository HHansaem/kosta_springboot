package com.kosta.univ.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.repository.UnivRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final DepartmentRepository departmentRepository;
	private final StudentRepository studentRepository;
	private final ProfessorRepository professorRepository;
	private final UnivRepository univRepository;
	
	private final ObjectMapper objectMapper;

	@Override
	public void enterStudent(StudentDto stud) throws Exception {
//		Optional<Student> ostud = studentRepository.findById(stud.getId());
	}

	@Override
	public StudentDto getStudentByNo(Integer studno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStudentByNoWithDname(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentWithDeptNameByStudno(studno);
		Student student = tuple.get(0, Student.class);
		String dname = tuple.get(0, String.class);
		//student의 속성을 key로, value를 값으로 매핑 (student의 속성을 일일이 다 map.put 안해줘도 됨)
		Map<String, Object> map = objectMapper.convertValue(student, Map.class);
		map.put("dname", dname);
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
