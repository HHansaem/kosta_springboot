package com.kosta.univ;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.service.UnivService;

@SpringBootTest
class UnivjpaApplicationTests {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	void insertDepartment() {
		Department department = Department.builder()
					.deptno(201)
				    .dname("전자공학과")
				    .part("메카트로닉학부")
				    .build("전자제어관")
				    .build();
										  
		departmentRepository.save(department);
	
	}

	@Test
	void insertProfessor() {
		Professor professor = Professor.builder()
					.profno(1001)
					.name("조인형")
					.id("captain")
					.position("정교수")
					.pay(550)
					.hiredate(Date.valueOf("1990-06-23"))
					.bonus(100)
					.department(Department.builder().deptno(100).build())
					.email("captain@abc@com")
					.hpage("http://www.abc.net")
					.build();
		
		professorRepository.save(professor);
	}

	@Test
	void insertStudent() {
		Student student = Student.builder()
							.studno(9714)
							.name("서재수")
							.grade(4)
							.id("jaesu")
							.jumin("9502241128467")
							.birthday(Date.valueOf("1995-02-24"))
							.tel("051)426-1700")
							.height(172)
							.weight(64)
							.professor(Professor.builder().profno(1001).build())
							.department1(Department.builder().deptno(100).build())
							.build();
				
		studentRepository.save(student);
	}
	
	//제1전공이 '컴퓨터공학부'인 학생을 조회하시오
	@Test
	void selectStudentListByDname1() {
		Optional<Department> odept = departmentRepository.findByDname("컴퓨터공학부");
		if(odept.isPresent()) {
			System.out.println(odept.get());
			System.out.println(odept.get().getStudList1());
		}
	}
	
	//제2전공이 '전자공학과'인 학생을 조회하시오
	@Test
	void selectStudentListByDname2() {
		Optional<Department> odept = departmentRepository.findByDname("전자공학과");
		if(odept.isPresent()) {
			System.out.println(odept.get());
			System.out.println(odept.get().getStudList2());
		}
	}
	
	//조인형 교수 정보 & 이 교수를 담당교수로 하는 학생 목록 조회
	@Test
	@Transactional
	void selectStudentListByProfName() {
		List<Professor> profList = professorRepository.findByName("조인형");
		for(Professor professor : profList) {
			System.out.println(professor);
			System.out.println(professor.getStudList());
		}
	}
	
	@Autowired
	private UnivService univService;
	
	//학생
	@Test
	void studentListByName() {
		try {
			List<StudentDto> studList = univService.studentListByName("서재수");
			System.out.println(studList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void studentListInDept1ByDeptName() {
		try {
			List<StudentDto> studList = univService.studentListInDept1ByDeptName("컴퓨터공학부");
			System.out.println(studList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void studentListInDept2ByDeptNo() {
		try {
			List<StudentDto> studList = univService.studentListInDept2ByDeptNo(201);
			System.out.println(studList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void studentListByNoProfessor() {
		try {
			List<StudentDto> studList = univService.studentListByNoProfessor();
			System.out.println(studList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void studentByStudentno() {
		try {
			StudentDto stud = univService.studentByStudentno(9413);
			System.out.println(stud);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void studentByJumin() {
		try {
			StudentDto stud = univService.studentByJumin("7808091786954");
			System.out.println(stud);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//교수
	@Test
	@Transactional
	void studentListByProfNo() {
		try {
			List<StudentDto> studList = univService.studentListByProfNo(1001);
			System.out.println(studList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void professorByProfNo() {
		try {
			System.out.println(univService.professorByProfNo(1001));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void professorListByProfName() {
		try {
			System.out.println(univService.professorListByProfName("조인형"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void professorListByDeptNo() {
		try {
			List<ProfessorDto> profList = univService.professorListByDeptNo(100);
			System.out.println(profList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void professorListByDeptName() {
		try {
			List<ProfessorDto> profList = univService.professorListByDeptName("컴퓨터공학부");
			System.out.println(profList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void professorListByPosition() {
		try {
			List<ProfessorDto> profList = univService.professorListByPosition("정교수");
			System.out.println(profList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//학과
	@Test
	void departmentByDeptNo() {
		try {
			System.out.println(univService.departmentByDeptNo(301));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void departmentByDeptName() {
		try {
			System.out.println(univService.departmentByDeptName("전기전자학과"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void departmentListByPart() {
		try {
			List<DepartmentDto> deptList = univService.departmentListByPart("공과대학");
			System.out.println(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void departmentListByBuild() {
		try {
			List<DepartmentDto> deptList = univService.departmentListByBuild("전자제어관");
			System.out.println(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
