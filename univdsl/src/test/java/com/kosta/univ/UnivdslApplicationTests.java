package com.kosta.univ;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.service.UnivService;

@SpringBootTest
class UnivdslApplicationTests {
	@Autowired
	private UnivService univService;

	@Test
	void getStudentListByName() {
		try {
			List<StudentDto> student = univService.getStudentListByName("서재수");
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void getStudentByDeptno() {
		try {
			List<StudentDto> student = univService.getStudentListByDeptno(202);
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void getStudentListByDname() {
		try {
			List<StudentDto> student = univService.getStudentListByDname("기계공학과");
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void getStudentListByDeptNo1OrDeptNo2() {
		try {
			List<StudentDto> student = univService.getStudentListByDeptNo1OrDeptNo2(100, 100);
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void findStudentListByDeptNo1OrDeptNo2() {
		try {
			List<StudentDto> student = univService.getStudentListByDeptNo1OrDeptNo2(100);
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void getStudentListByDname1OrDname2() {
		try {
			List<StudentDto> student = univService.getStudentListByDname1OrDname2("컴퓨터공학부","전자공학과");
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void findStudentListByDname1OrDname2() {
		try {
			List<StudentDto> student = univService.getStudentListByDname1OrDname2("컴퓨터공학부");
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void getProfessorByStudno() {
		try {
			ProfessorDto professor = univService.getProfessorByStudno(9412);
			System.out.println(professor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
