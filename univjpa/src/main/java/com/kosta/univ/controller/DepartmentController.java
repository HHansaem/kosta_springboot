package com.kosta.univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.service.UnivService;

@RestController  //이 안에 있는 모든 메서드가 비동기 즉, ResponseBody(뷰를 주고받는 게 아니라 데이터만 요청하고 받음 like ajax)
public class DepartmentController {
	
	@Autowired
	private UnivService univService;
	
	@PostMapping("/regDept")
	public ResponseEntity<String> regDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
			univService.saveDepartment(departmentDto);
			return new ResponseEntity<String>("데이터 정상 전송", HttpStatus.OK);  //(데이터, 전송여부)
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
