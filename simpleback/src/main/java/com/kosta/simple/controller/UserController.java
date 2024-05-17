package com.kosta.simple.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.simple.dto.UserDto;

@RestController  //view는 react가 책임지니, data를 넘겨줌 (ResponseBody랑 같음)
public class UserController {
	
	@PostMapping("/regUser")  //@RequestBody: JSON을 객체로 받을 때
	public ResponseEntity<String> regUser(@RequestBody Map<String, String> param) {
		System.out.println(param);
		System.out.println(param.get("name"));
		System.out.println(param.get("tel"));
		System.out.println(param.get("email"));
		System.out.println(param.get("address"));
		return new ResponseEntity<String>("사용자 등록 성공", HttpStatus.OK);
	}
	
	@PostMapping("/regUser1")
	public ResponseEntity<String> regUser1(@RequestBody UserDto userDto) {
		System.out.println(userDto);
		return new ResponseEntity<String>("사용자 등록 성공", HttpStatus.OK);
	}
	
	@GetMapping("/userInfo")
	public ResponseEntity<UserDto> userInfo() {
 		UserDto userDto = UserDto.builder().name("류선재").tel("010-3434-2023")
 								.email("ryu@kosta.com").address("경기도 부천시").build();
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@PostMapping("/userModify")
	public ResponseEntity<String> userModify(@ModelAttribute UserDto userDto){
		System.out.println(userDto);
		return new ResponseEntity<String>("사용자 변경 성공", HttpStatus.OK);
	}

	@PostMapping("/userModify1")  //required=false쓰려면 "name"앞에 name= 도 써줘야 함
	public ResponseEntity<String> userModify1(@RequestParam("name") String name, 
											@RequestParam("tel") String tel,
											@RequestParam("email") String email,
											@RequestParam("address") String address){
		System.out.println(name);
		System.out.println(tel);
		System.out.println(email);
		System.out.println(address);
		return new ResponseEntity<String>("사용자 변경 성공", HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<UserDto> getUser(@RequestParam("name") String name) {
		System.out.println(name);
		UserDto userDto = UserDto.builder().name(name).tel("010-3333-5555")
								.email("hong@kosta.com").address("서울시 금천구 가산동").build();
		return new ResponseEntity<UserDto>(userDto , HttpStatus.OK);
	}
	
	//@PathVariable: path의 값을 가져오라 {name}의 이름이랑 변수명 같아야 함
	//한글 깨질 때는 produces 써줌
	@GetMapping(value="/getUser1/{name}", produces = "application/json;charset=utf-8")  
	public ResponseEntity<UserDto> getUser1(@PathVariable String name) {
		System.out.println(name);
		UserDto userDto = UserDto.builder().name(name).tel("010-3333-5555")
								.email("hong@kosta.com").address("서울시 금천구 가산동").build();
		return new ResponseEntity<UserDto>(userDto , HttpStatus.OK);
	}
	
}
