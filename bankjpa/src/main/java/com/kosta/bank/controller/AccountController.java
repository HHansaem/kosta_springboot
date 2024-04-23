package com.kosta.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
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
	public String makeAccount(@ModelAttribute("acc") AccountDto acc, Model model) {
		//@ModelAttribute : acc객체를 view로 넘겨줌 (model로 안 넘겨줘도 이렇게 넘겨줄 수 있음)
		try {
			accountService.makeAccount(acc);
			return "accountInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	@GetMapping("/accountInfo")
	public String accountInfo() {
		return "accountInfoForm";
	}
	
	@PostMapping("/accountInfo")
	public ModelAndView accountInfo(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		try {
			AccountDto acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountInfo");
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
	public ModelAndView deposit(@RequestParam("id") String id, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.deposit(id, money);
			mav.addObject("acc", accountService.accountInfo(id));
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
	public ModelAndView withdraw(@RequestParam("id") String id, @RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.withdraw(id, money);
			mav.addObject("acc", accountService.accountInfo(id));
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
			List<AccountDto> accs = accountService.allAccountInfo();
			mav.addObject("accs", accs);
			mav.setViewName("allAccountInfo");
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

	@PostMapping("/transfer")
	public ModelAndView transfer(@RequestParam("sid") String sid, @RequestParam("rid") String rid, 
									@RequestParam("money") Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.transfer(sid, rid, money);
			mav.addObject("acc", accountService.accountInfo(sid));
			mav.setViewName("accountInfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("transfer");
		}
		return mav;
	}
	
}
