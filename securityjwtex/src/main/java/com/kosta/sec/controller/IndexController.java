package com.kosta.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@GetMapping({"","/"})
	@ResponseBody
	public String index() {
		return "인덱스입니다";
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/joinProc")
	public String joinPorc(User user) {
		System.out.println("회원가입 진행 : " + user);
		String rawPassword = user.getPassword();
		String edcodePassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(edcodePassword);
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails principal) {  //세션에 있는 값 가져옴
		System.out.println(principal.getUser());
		return "유저입니다";
	}

	@Secured("ROLE_MANAGER")  //어노테이션 마다 쓰는 법 다름
	@PreAuthorize("hasRole('ROLE_MANAGER')") 
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "매니저입니다";
	}
	
	//hasRole-> 여러 권한에 대해 or가능
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")  
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "어드민입니다";
	}
	
}
