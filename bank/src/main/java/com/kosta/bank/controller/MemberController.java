package com.kosta.bank.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Member;
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
	public String memberDoubleId(String id) {
		try {
			Boolean check = memberService.checkMemberDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@PostMapping("/join")
	public ModelAndView join(Member member) {
		ModelAndView mav = new ModelAndView();
		try {
			memberService.join(member);
			mav.addObject("member", member);
			mav.setViewName("login");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public ModelAndView login(String id, String password, String autologin, 
								HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			// 자동로그인
			Cookie autoLoginCookie = null;
			Cookie idCookie = null;
			Cookie passwordCookie = null;
			
			if(autologin != null) {
				autoLoginCookie = new Cookie("autologin", autologin);
				idCookie = new Cookie("id", id);
				passwordCookie = new Cookie("password", password);
			} else {
				autoLoginCookie = new Cookie("autologin", "false");
				idCookie = new Cookie("id", "");
				passwordCookie = new Cookie("password", "");
			}
			response.addCookie(autoLoginCookie);
			response.addCookie(idCookie);
			response.addCookie(passwordCookie);
			///////////////////
			
			Member member = memberService.login(id, password);
			member.setPassword("");
			session.setAttribute("user", member.getId());
			mav.addObject("member", member);
			mav.setViewName("makeAccount");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("user");
		return "login";
	}
	
}
