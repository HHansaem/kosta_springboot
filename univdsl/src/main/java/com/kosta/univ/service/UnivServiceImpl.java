package com.kosta.univ.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
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
	private final ModelMapper modelMapper;

	@Override  //학생입학
	public void enterStudent(StudentDto stud) throws Exception {
		Optional<Student> ostud = studentRepository.findById(stud.getStudno());
		if(ostud.isPresent()) throw new Exception("학생번호 중복 오류");
		studentRepository.save(modelMapper.map(stud, Student.class));  //StudentDto => Student
	}

	@Override  //학번으로 학생정보 조회
	public StudentDto getStudentByNo(Integer studno) throws Exception {
		Optional<Student> ostud = studentRepository.findById(studno);
		if(ostud.isEmpty()) throw new Exception("학생번호 오류");
		return modelMapper.map(ostud.get(), StudentDto.class);  //Student => StudentDto
	}

	@Override  //학번으로 학생정보 조회(학과명 포함)
	public Map<String, Object> getStudentByNoWithDname(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentWithDeptNameByStudno(studno);
		Student student = tuple.get(0, Student.class);
		String dname = tuple.get(0, String.class);
		//student의 속성을 key로, value를 값으로 매핑 (student의 속성을 일일이 다 map.put 안해줘도 됨)
		Map<String, Object> map = objectMapper.convertValue(student, Map.class);
		map.put("dname", dname);
		return map;
	}

	@Override  //학번으로 학생정보 조회(담당교수명 포함)
	public Map<String, Object> getStudentByNoWithProfName(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentWithProfNameByStudno(studno);
		Student student = tuple.get(0, Student.class);
		String name = tuple.get(0, String.class);
		Map<String, Object> map = objectMapper.convertValue(student, Map.class);
		map.put("name", name);
		return map;
	}

	@Override  //학번으로 학생정보 조회(학과명, 담당교수명 포함)
	public Map<String, Object> getStudentByNoWithDnameAndProfName(Integer studno) throws Exception {
		Tuple tuple = univRepository.findStudentWithDnameAndProfNameByStudno(studno);
		Student student = tuple.get(0, Student.class);
		String dname = tuple.get(0, String.class);
		String name = tuple.get(0, String.class);
		Map<String, Object> map = objectMapper.convertValue(student, Map.class);
		map.put("dname", dname);
		map.put("name", name);
		return map;
	}

	@Override  //학생이름으로 학생 정보 조회
	public List<StudentDto> getStudentListByName(String name) throws Exception {
		List<Student> studList = studentRepository.findByName(name);
		return studList.stream()
						.map(stud->modelMapper.map(stud, StudentDto.class))
						.collect(Collectors.toList());
	}

	@Override  //특정학과 학생 조회(학과번호로)
	public List<StudentDto> getStudentListByDeptno(Integer deptno) throws Exception {
		List<Student> studList = studentRepository.findByDeptno1(deptno);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //특정학과 학생 조회(학과명으로)
	public List<StudentDto> getStudentListByDname(String dname) throws Exception {
		List<Student> studList = univRepository.findStudentListByDname(dname);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //특정학년 학생 조회
	public List<StudentDto> getStudentListByGrade(Integer grade) throws Exception {
		List<Student> studList = studentRepository.findByGrade(grade);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //특정학과, 특정학년 학생 조회
	public List<StudentDto> getStudentListByDnameAndGrade(String dname, Integer grade) throws Exception {
		List<Student> studList = univRepository.findStudentListByDnameAndGrade(dname, grade);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //주전공이 deptno1이거나 부전공이 deptno2인 학생 조회
	public List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno1, Integer deptno2) throws Exception {
		List<Student> studList = studentRepository.findByDeptno1OrDeptno2(deptno1, deptno2);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //주전공이나 부전공이 특정학과인 학생 조회(학과번호로)
	public List<StudentDto> getStudentListByDeptNo1OrDeptNo2(Integer deptno) throws Exception {
		List<Student> studList = univRepository.findStudentListByDeptNo1OrDeptNo2(deptno);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //주전공이 dname1이거나 부전공이 dname2인 학생 조회
	public List<StudentDto> getStudentListByDname1OrDname2(String dname1, String dname2) throws Exception {
		List<Student> studList = univRepository.findStudentListByDname1OrDname2(dname1, dname2);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //주전공이나 부전공이 특정학과인 학생 조회(학과명으로)
	public List<StudentDto> getStudentListByDname1OrDname2(String dname) throws Exception {
		List<Student> studList = univRepository.findStudentListByDname1OrDname2(dname);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //특정 교수가 담당하는 학생목록 조회
	public List<StudentDto> getStudentListByProfNo(Integer profno) throws Exception {
		List<Student> studList = studentRepository.findByProfno(profno);
		return studList.stream()
				.map(stud->modelMapper.map(stud, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override  //교수 입사
	public void enterProfessor(ProfessorDto prof) throws Exception {
		Optional<Professor> professor = professorRepository.findById(prof.getDeptno());
		if(professor.isPresent()) throw new Exception("교수번호 중복 오류");
		professorRepository.save(modelMapper.map(prof, Professor.class));
	}

	@Override  //교수번호로 교수정보 조회
	public ProfessorDto getProfessorByProfno(Integer profno) throws Exception {
		Optional<Professor> professor = professorRepository.findById(profno);
		if(professor.isEmpty()) throw new Exception("교수정보 없음");
		return modelMapper.map(professor.get(), ProfessorDto.class);
	}

	@Override  //교수명으로 교수정보 조회
	public List<ProfessorDto> getProfessorByProfName(String profName) throws Exception {
		List<Professor> profList = professorRepository.findByName(profName);
		return profList.stream()
					.map(prof->modelMapper.map(prof, ProfessorDto.class))
					.collect(Collectors.toList());
	}

	@Override  //교수번호로 교수정보 조회(학과명 포함)
	public Map<String, Object> getProfessorByProfnoWithDname(Integer profno) throws Exception {
		Tuple tuple = univRepository.findProfessorByProfnoWithDname(profno);
		Professor professor = tuple.get(0, Professor.class);
		String dname = tuple.get(0, String.class);
		Map<String, Object> map = objectMapper.convertValue(professor, Map.class);
		map.put("dname", dname);
		return map;
	}

	@Override  //학생의 담당교수 조회
	public ProfessorDto getProfessorByStudno(Integer studno) throws Exception {
		Professor professor = univRepository.findProfessorByStudno(studno);
		return modelMapper.map(professor, ProfessorDto.class);
	}

	@Override  //특정학과 교수 정보조회(학과번호로)
	public List<ProfessorDto> getProfessorByDeptno(Integer deptno) throws Exception {
		List<Professor> profList = professorRepository.findByDeptno(deptno);
		return profList.stream()
					.map(prof->modelMapper.map(prof, ProfessorDto.class))
					.collect(Collectors.toList());
	}

	@Override  //특정학과 교수 정보조회(학과명으로)
	public List<ProfessorDto> getProfessorByDeptName(String dname) throws Exception {
		List<Professor> profList = univRepository.findProfessorByDeptName(dname);
		return profList.stream()
				.map(prof->modelMapper.map(prof, ProfessorDto.class))
				.collect(Collectors.toList());
	}

	@Override  //학과신설
	public void foundDepartment(DepartmentDto dept) throws Exception {
		Optional<Department> odept = departmentRepository.findById(dept.getDeptno());
		if(odept.isPresent()) throw new Exception("학과번호 중복 오류");
		departmentRepository.save(modelMapper.map(dept, Department.class));
	}

	@Override  //학과번호로 학과조회
	public DepartmentDto getDepartmentByDeptno(Integer deptno) throws Exception {
		Optional<Department> odept = departmentRepository.findById(deptno);
		if(odept.isPresent()) throw new Exception("학과번호 오류");
		return modelMapper.map(odept.get(), DepartmentDto.class);
	}

	@Override  //학과명으로 학과조회
	public DepartmentDto getDepartmentByDname(String dname) throws Exception {
		Optional<Department> odept = departmentRepository.findByDname(dname);
		if(odept.isEmpty()) throw new Exception("학과명 오류");
		return modelMapper.map(odept.get(), DepartmentDto.class);
	}

	@Override  //학번으로 학과조회
	public DepartmentDto getDepartmentByStudNo(Integer studno) throws Exception {
		Department department = univRepository.findDepartmentByStudNo(studno);
		return modelMapper.map(department, DepartmentDto.class);
	}

	@Override  //건물로 학과조회
	public List<DepartmentDto> getDepartmentByBuild(String build) throws Exception {
		List<Department> deptList = departmentRepository.findByBuild(build);
		return deptList.stream()
				.map(dept->modelMapper.map(dept, DepartmentDto.class))
				.collect(Collectors.toList());
	}

}
