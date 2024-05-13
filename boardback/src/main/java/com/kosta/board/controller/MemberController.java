package com.kosta.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto){
		try {
			memberService.join(memberDto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> param) {
		try {
			MemberDto memberDto = memberService.login(param.get("id"), param.get("password"));
			memberDto.setPassword("");
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<MemberDto>(HttpStatus.BAD_GATEWAY);
		}
	}
}
