package com.kosta.bank.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor  //@Autowired (setter를 통한 주입) 대신에 사용 가능 (=생성자를 통한 주입)
public class AccountController {
	
	private final AccountService accountService;  //@RequiredArgsConstructor 사용하려면 final 붙여야 함
	
	@GetMapping("/makeAccount")
	public String makeAccount() {
		return "makeAccount";
	}
	
	@ResponseBody
	@PostMapping("/accountDoubleId")
	public String accountDoubleId(String id) {
		try {
			Boolean check = accountService.checkAccountDoubleId(id);
			return String.valueOf(check);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	@PostMapping("/makeAccount")
	public ModelAndView makeAccount(Account acc) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.makeAccount(acc);
			mav.addObject("acc", accountService.accountInfo(acc.getId()));
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/accountInfo")
	public String accountInfo() {
		return "accountInfoForm";
	}
	
	@PostMapping("/accountInfo")
	public ModelAndView accountInfo(String id) {
		ModelAndView mav = new ModelAndView();
		try {
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/allAccountInfo")
	public ModelAndView allAccountInfo() {
		ModelAndView mav = new ModelAndView();
		try {
			List<Account> accs = accountService.allAccountInfo();
			mav.addObject("accs", accs);
			mav.setViewName("allAccountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/deposit")
	public String deposit() {
		return "deposit";
	}
	
	@PostMapping("/deposit")
	public ModelAndView deposit(String id, Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.deposit(id, money);
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}

	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@PostMapping("/withdraw")
	public ModelAndView withdraw(String id, Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.withdraw(id, money);
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}
	
	@Transactional
	@PostMapping("/transfer")
	public ModelAndView transfer(String sid, String rid, Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			Account acc = accountService.accountInfo(sid);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
}
