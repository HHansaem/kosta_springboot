package com.kosta.board.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.board.dto.Member;
import com.kosta.board.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Member member, Model model) {
		try {
			memberService.join(member);
			model.addAttribute("member", member);
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@ResponseBody  //return해서 주는게 view가 아니라 데이터 (비동기통신에 사용)
	@RequestMapping(value = "/memberDoubleId", method = RequestMethod.POST)
	public String memberDoubleIdCheck(String id) {
		try {
			Boolean check = memberService.checkMemberDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String id, String password, Model model, String autologin, HttpServletRequest request, HttpServletResponse response) {
		try {
			//자동 로그인 -> login success시 cookie check & 저장
			Cookie autoLoginCookie = null;
			Cookie idCookie = null;
			Cookie passwordCookie = null;
			
			if(autologin != null) {
				autoLoginCookie = new Cookie("autologin", autologin);
				idCookie = new Cookie("id", id);
				passwordCookie = new Cookie("password", password);
			} else {  //체크 안 했으면 id랑 password cookie값 지워주기
				autoLoginCookie = new Cookie("autologin", "false");
				idCookie = new Cookie("id", "");
				passwordCookie = new Cookie("password", "");
			}
			response.addCookie(autoLoginCookie);
			response.addCookie(idCookie);
			response.addCookie(passwordCookie);
			//////////////////////////
			
			Member member = memberService.login(id, password);
			member.setPassword("");
			session.setAttribute("user", member.getId());
			model.addAttribute("member", member);
			return "redirect:/boardlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		session.removeAttribute("user");
		return "login";
	}
	
}
