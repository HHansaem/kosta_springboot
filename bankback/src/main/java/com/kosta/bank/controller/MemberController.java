package com.kosta.bank.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/checkMemId")
	public ResponseEntity<String> checkMemId(@RequestParam("id") String id) {
		try {
			Boolean isMemId = memberService.checkMemberDoubleId(id);
			return new ResponseEntity<String>(String.valueOf(isMemId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		try {
			memberService.join(memberDto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, Object> param) {
		try {
			memberService.login((String)param.get("id"), (String)param.get("password"));
//			session.setAttribute("user", param.get("id"));
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
