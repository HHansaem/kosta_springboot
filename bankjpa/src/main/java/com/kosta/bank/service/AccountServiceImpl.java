package com.kosta.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public void makeAccount(AccountDto acc) throws Exception {
		Optional<Account> oacc = accountRepository.findById(acc.getId());
		if(oacc.isPresent()) throw new Exception("계좌번호 중복 오류");
		//★ Account(entity)에 입력한 key값이 존재하면 update, 존재하지 않으면 update 
		accountRepository.save(acc.toAccount());
	}

	@Override
	public AccountDto accountInfo(String id) throws Exception {
		Optional<Account> oacc = accountRepository.findById(id);  //findById는 return 타입이 Oprional타입
		if(oacc.isEmpty()) throw new Exception("계좌조회 실패");  //isPresent()랑 반대역할 (아무거나 써도 됨)
		return oacc.get().toAccountDto();
	}

	@Override
	public void deposit(String id, Integer money) throws Exception {
		Optional<Account> oacc = accountRepository.findById(id);
		if(oacc.isEmpty()) throw new Exception("계좌번호 오류");
		Account acc = oacc.get();
		acc.deposit(money);
		accountRepository.save(acc);  //id는 이미 존재하니까 balance만 update
	}

	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Optional<Account> oacc = accountRepository.findById(id);
		if(oacc.isEmpty()) throw new Exception("계좌번호 오류");
		Account acc = oacc.get();
		acc.withdraw(money);
		accountRepository.save(acc);  //balance만 update
	}

	@Override
	public List<AccountDto> allAccountInfo() throws Exception {
		List<AccountDto> list = new ArrayList<>();
		for(Account acc : accountRepository.findAll()) {
			list.add(acc.toAccountDto());
		}
		return list;
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Optional<Account> osacc = accountRepository.findById(sid);
		if(osacc.isEmpty()) throw new Exception("보내는 계좌번호 오류");
		Optional<Account> oracc = accountRepository.findById(rid);
		if(oracc.isEmpty()) throw new Exception("받는 계좌번호 오류");
		
		Account sacc = osacc.get();
		Account racc = oracc.get();
		sacc.withdraw(money);
		accountRepository.save(sacc);
		racc.deposit(money);
		accountRepository.save(racc);
	}

	@Override
	public Boolean checkAccountDoubleId(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
