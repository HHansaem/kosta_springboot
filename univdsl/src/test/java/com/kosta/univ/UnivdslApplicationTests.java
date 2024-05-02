package com.kosta.univ;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.service.UnivService;

@SpringBootTest
class UnivdslApplicationTests {
	
	@Autowired
	private UnivService univService;

	@Test
	void getStudentByName() {
		List<StudentDto> student;
		try {
			student = univService.getStudentByName("서재수");
			System.out.println(student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
