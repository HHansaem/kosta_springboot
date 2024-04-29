package com.kosta.univ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.service.UnivService;

@RestController  //이 안에 있는 모든 메서드가 비동기 즉, ResponseBody(뷰를 주고받는 게 아니라 데이터만 요청하고 받음 like ajax)
public class UnivController {
	
	@Autowired
	private UnivService univService;
	
	@PostMapping("/regDept")  //파라미터가 RequestBody : JSON으로 보내야 함
	public ResponseEntity<String> regDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
			univService.saveDepartment(departmentDto);
			return new ResponseEntity<String>("학과 정상 등록", HttpStatus.OK);  //(데이터, 전송여부)
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/regStud")  //파라미터가 객체 : Form Data로 보내야 함
	public ResponseEntity<String> regStud(StudentDto studDto) {
		try {
			univService.saveStudent(studDto);
			return new ResponseEntity<String>("학생 정상 등록", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/regProf")
	public ResponseEntity<String> regProf(@RequestBody ProfessorDto profDto) {
		try {
			univService.saveProfessor(profDto);
			return new ResponseEntity<String>("교수 정상 등록", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/studInfo")
	public ResponseEntity<List<StudentDto>> studentInfo(@RequestParam("name") String name) {
		try {
			List<StudentDto> studDtoList = univService.studentListByName(name);
			return new ResponseEntity<List<StudentDto>>(studDtoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/studInfoByNo")
	public ResponseEntity<StudentDto> studentInfoByNo(@RequestParam("studno") Integer studno) {
		try {
			StudentDto studDto = univService.studentByStudentno(studno);
			return new ResponseEntity<StudentDto>(studDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
}
