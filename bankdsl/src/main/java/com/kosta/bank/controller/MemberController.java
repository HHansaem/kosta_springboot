package com.kosta.bank.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@ResponseBody
	@PostMapping("/memberDoubleId")
	public String accountDoubleId(String id) {
		try {
			Boolean check = memberService.checkMemberDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto, Model model) {
		try {
			memberService.join(memberDto);
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", "회원가입 실패");
			return "error";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
		try {
			memberService.login(id, password);
			session.setAttribute("user", id);
			return "makeAccount";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("user");
		return "login";
	}
}
