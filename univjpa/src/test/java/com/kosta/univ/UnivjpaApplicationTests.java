package com.kosta.univ;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessorRepository;
import com.kosta.univ.repository.StudentRepository;

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

}
