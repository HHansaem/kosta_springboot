package com.kosta.univ.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.ConvertMapper;
import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
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
	public List<StudentDto> studentListByName(String studName) throws Exception {
		List<Student> studList = studentRepository.findByName(studName);
		return ConvertMapper.studListToStudDtoList(studList);
	}

	@Override  //제1전공으로 학생목록 조회
	public List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return ConvertMapper.studListToStudDtoList(odept.get().getStudList1());
		}
		return null;
	}
	
	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		List<Student> studList = studentRepository.findByDepartment1_Deptno(deptNo); 
		return ConvertMapper.studListToStudDtoList(studList);
	}

	@Override  //제2전공으로 학생목록 조회
	public List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			List<Student> studList = odept.get().getStudList2();
			return ConvertMapper.studListToStudDtoList(studList);
		}
		return null;
	}
	
	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		List<Student> studList = studentRepository.findByDepartment2_Deptno(deptNo);
		return ConvertMapper.studListToStudDtoList(studList);
	}

	@Override  //학년으로 학생목록 조회
	public List<StudentDto> studentListByGrade(Integer grade) throws Exception {
		List<Student> studList = studentRepository.findByGrade(grade);
		return ConvertMapper.studListToStudDtoList(studList);
	}

	@Override  //담당교수가 없는 학생목록 조회
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		List<Student> studList = studentRepository.findByProfessorIsNull();
		return ConvertMapper.studListToStudDtoList(studList);
	}

	@Override  //학번으로 학생 조회
	public StudentDto studentByStudentno(Integer studno) throws Exception {
		Optional<Student> ostud = studentRepository.findById(studno);
		if(ostud.isPresent()) {
			return ostud.get().toDto();
		}
		return null;
	}

	@Override  //주민번호로 학생 조회
	public StudentDto studentByJumin(String jumin) throws Exception {
		Optional<Student> ostud = studentRepository.findByJumin(jumin);
		if(ostud.isPresent()) {
			return ostud.get().toDto();
		}
		return null;
	}

	
	
	//교수
	@Override  //교수번호로 담당 학생목록 조회
	public List<StudentDto> studentListByProfNo(Integer profNo) throws Exception {
		Optional<Professor> oprof = professorRepository.findById(profNo);
		if(oprof.isPresent()) {
			List<Student> studList = oprof.get().getStudList();
			return ConvertMapper.studListToStudDtoList(studList);
		}
		return null;
	}

	@Override  //교수번호로 교수 조회
	public ProfessorDto professorByProfNo(Integer profNo) throws Exception {
		Optional<Professor> oprof = professorRepository.findById(profNo);
		if(oprof.isPresent()) {
			return oprof.get().toDto();
		}
		return null;
	}

	@Override  //교수이름으로 교수목록 조회
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
	    List<Professor> profList = professorRepository.findByName(profName);
	    return ConvertMapper.profListToProfDtoList(profList);
	}

	@Override  //학과번호로 교수목록 조회
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptNo);
	    if(odept.isPresent()) {
	       List<Professor> profList = odept.get().getProfList();
	       return ConvertMapper.profListToProfDtoList(profList);
	    }
	    return null;

	}

	@Override  //학과이름으로 교수목록 조회
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
	    if(odept.isPresent()) {
	       List<Professor> profList = odept.get().getProfList();
	       return ConvertMapper.profListToProfDtoList(profList);
	    }
	    return null;
	}
	
	@Override  //직급으로 교수목록 조회
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
	    List<Professor> profList = professorRepository.findByPosition(position);
	    return ConvertMapper.profListToProfDtoList(profList);
	}

	
	
	//학과
	@Override  //학과번호로 학과 조회
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptNo);
		if(odept.isPresent()) {
			return odept.get().toDto();
		}
		return null; 
	}

	@Override  //학과이름으로 학과 조회
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(deptName);
		if(odept.isPresent()) {
			return odept.get().toDto();
		}
		return null;
	}

	@Override  //학과(part)로 학과 목록 조회
	public List<DepartmentDto> departmentListByPart(String part) throws Exception {
	    List<Department> deptList = departmentRepository.findByPart(part);
	    return ConvertMapper.deptListToDeptDtoList(deptList);
	}

	@Override  //위치하는 건물로 학과목록 조회
	public List<DepartmentDto> departmentListByBuild(String build) throws Exception {
	    List<Department> deptList = departmentRepository.findByBuild(build);
	    return ConvertMapper.deptListToDeptDtoList(deptList);
	}

	@Override
	public void saveDepartment(DepartmentDto deptDto) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptDto.getDeptno());
	    if (odept!=null) throw new Exception("등록된 학과번호입니다.");
	    departmentRepository.save(deptDto.toEntity());
	}

	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		Optional<Student> ostud = studentRepository.findById(studDto.getStudno());
		if(ostud != null) throw new Exception("등록된 학생번호입니다");
		studentRepository.save(studDto.toEntity());
	}

	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		Optional<Professor> oprof = professorRepository.findById(profDto.getProfno());
	    if(oprof!=null)  throw new Exception("등록된 교수번호입니다.");
	    professorRepository.save(profDto.toEntity());

	}

}
