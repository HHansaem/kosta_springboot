package com.kosta.sec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

@RestController
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@GetMapping("/user")
	public ResponseEntity<User> user(Authentication authentication) {
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		User user = principalDetails.getUser();
		System.out.println(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping({"/",""})
	public String index() {
		return "인덱스입니다.";
	}
	
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "매니저입니다";
	}
	
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "어드민입니다";
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

}
