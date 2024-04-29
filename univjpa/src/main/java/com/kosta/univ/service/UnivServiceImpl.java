package com.kosta.univ.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final DepartmentRepository departmentRepository; 
	
	private final ProfessorRepository professorRepository;
	
	private final StudentRepository studentRepository;

	@Override  //학생 이름으로 학생목록 조회
	public List<Student> studentListByName(String studName) throws Exception {
		return studentRepository.findByName(studName);
	}

	@Override  //제1전공으로 학생목록 조회
	public List<Student> studentListInDept1ByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return odept.get().getStudList1();
		}
		return null;
	}
	
	@Override
	public List<Student> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		return studentRepository.findByDepartment1_Deptno(deptNo);
	}

	@Override  //제2전공으로 학생목록 조회
	public List<Student> studentListInDept2ByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return odept.get().getStudList2();
		}
		return null;
	}
	
	@Override
	public List<Student> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		return studentRepository.findByDepartment2_Deptno(deptNo);
	}

	@Override  //학년으로 학생목록 조회
	public List<Student> studentListByGrade(Integer grade) throws Exception {
		return studentRepository.findByGrade(grade);
	}

	@Override  //담당교수가 없는 학생목록 조회
	public List<Student> studentListByNoProfessor() throws Exception {
		return studentRepository.findByProfessorIsNull();
	}

	@Override  //학번으로 학생 조회
	public Student studentByStudentno(Integer studno) throws Exception {
		Optional<Student> ostud = studentRepository.findById(studno);
		if(ostud.isPresent()) ostud.get();
		return null;
	}

	@Override  //주민번호로 학생 조회
	public Student studentByJumin(String jumin) throws Exception {
		Optional<Student> ostud = studentRepository.findByJumin(jumin);
		if(ostud.isPresent()) ostud.get();
		return null;
	}

}
