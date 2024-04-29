package com.kosta.univ.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
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

	//학생
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
		if(ostud.isPresent()) return ostud.get();
		return null;
	}

	@Override  //주민번호로 학생 조회
	public Student studentByJumin(String jumin) throws Exception {
		Optional<Student> ostud = studentRepository.findByJumin(jumin);
		if(ostud.isPresent()) return ostud.get();
		return null;
	}

	
	
	//교수
	@Override  //교수번호로 담당 학생목록 조회
	public List<Student> studentListByProfNo(Integer profNo) throws Exception {
		Optional<Professor> oprof = professorRepository.findById(profNo);
		if(oprof.isPresent()) {
			return oprof.get().getStudList();
		}
		return null; 
	}

	@Override  //교수번호로 교수 조회
	public Professor professorByProfNo(Integer profNo) throws Exception {
		Optional<Professor> oprof = professorRepository.findById(profNo);
		if(oprof.isPresent()) {
			return oprof.get();
		}
		return null;
	}

	@Override  //교수이름으로 교수목록 조회
	public List<Professor> professorListByProfName(String profName) throws Exception {
		return professorRepository.findByName(profName);
	}

	@Override  //학과번호로 교수목록 조회
	public List<Professor> professorListByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptNo);
		if(odept.isPresent()) {
			return odept.get().getProfList();
		}
		return null;
	}

	@Override  //학과이름으로 교수목록 조회
	public List<Professor> professorListByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return odept.get().getProfList();
		}
		return null;
	}
	
	@Override  //직급으로 교수목록 조회
	public List<Professor> professorListByPosition(String position) throws Exception {
		return professorRepository.findByPosition(position);
	}

	
	
	//학과
	@Override  //학과번호로 학과 조회
	public Department departmentByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptNo);
		if(odept.isPresent()) {
			return odept.get();
		}
		return null; 
	}

	@Override  //학과이름으로 학과 조회
	public Department departmentByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return odept.get();
		}
		return null;
	}

	@Override  //학과(part)로 학과 목록 조회
	public List<Department> departmentListByPart(Integer part) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override  //위치하는 건물로 학과목록 조회
	public List<Department> departmentListByBuild(String build) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
