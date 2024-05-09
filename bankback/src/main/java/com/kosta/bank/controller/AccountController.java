package com.kosta.bank.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.service.AccountService;

@RestController  //@ResponseBody 대신 쓰는 것
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/checkAccId")
	public ResponseEntity<String> checkAccId(@RequestParam("id") String id) {
		try {
			Boolean isId = accountService.checkAccountDoubleId(id);
			return new ResponseEntity<String>(String.valueOf(isId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountInfo")
	public ResponseEntity<AccountDto> accountInfo(@RequestParam("id") String id) {
		try {
			AccountDto accountDto = accountService.accountInfo(id);
			return new ResponseEntity<AccountDto>(accountDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping("/makeAccount")  //@RequestBody: JSON을 객체로 받아올 때
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto accountDto) {
		try {
			accountService.makeAccount(accountDto);
			return new ResponseEntity<String>("계좌개설 성공", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/allAccountInfo")
	public ResponseEntity<List<AccountDto>> allAccountInfo() {
		try {
			List<AccountDto> accList = accountService.allAccountInfo();
			return new ResponseEntity<List<AccountDto>>(accList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AccountDto>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/deposit")
	public ResponseEntity<Object> deposit(@RequestBody Map<String, Object> param) {
		try {
			accountService.deposit((String)param.get("id"), Integer.parseInt((String)param.get("money")));
			AccountDto accountDto = accountService.accountInfo((String)param.get("id"));
			return new ResponseEntity<Object>(accountDto.getBalance(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody Map<String, Object> param) {
		try {
			accountService.withdraw((String)param.get("id"), Integer.parseInt((String)param.get("money")));
			AccountDto accountDto = accountService.accountInfo((String)param.get("id"));
			return new ResponseEntity<Object>(accountDto.getBalance(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
