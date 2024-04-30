package com.kosta.bank.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.BankRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private BankRepository bankRepository;

	@Override
	public void makeAccount(AccountDto acc) throws Exception {
		Account eacc = bankRepository.findAccountById(acc.getId());
		if(eacc != null) throw new Exception("계좌번호 중복 오류");
		bankRepository.insertAccount(acc.toAccount());
	}

	@Override
	public AccountDto accountInfo(String id) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		return acc.toAccountDto();
	}

	@Override
	public void deposit(String id, Integer money) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.deposit(money);
		bankRepository.updateBalance(id, acc.getBalance());
	}

	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		if(acc == null) throw new Exception("계좌번호 오류");
		acc.withdraw(money);
		bankRepository.updateBalance(id, acc.getBalance());
	}

	@Override
	public List<AccountDto> allAccountInfo() throws Exception {
		List<Account> accList = bankRepository.findAllAccount(); 
//		List<AccountDto> accList = new ArrayList<>();
//		for(Account acc : accountRepository.findAll()) {
//			accList.add(acc.toAccountDto());
//		}
//		return accList;
		//위 코드를 대신해서 아래의 코드처럼 적어줌
		if(accList == null) return null;
		return accList.stream()
					.map(Account::toAccountDto)
					.collect(Collectors.toList());
	}

	@Override
	public void transfer(String sid, String rid, Integer money) throws Exception {
		Account sacc = bankRepository.findAccountById(sid);
		if(sacc == null) throw new Exception("보내는 계좌 오류");
		Account racc = bankRepository.findAccountById(rid);
		if(racc == null) throw new Exception("받는 계좌 오류");
		
		sacc.withdraw(money);
		racc.deposit(money);
		bankRepository.transfer(sid, rid, sacc.getBalance(), racc.getBalance());
	}

	@Override
	public Boolean checkAccountDoubleId(String id) throws Exception {
		Account acc = bankRepository.findAccountById(id);
		return acc != null;
	}

}
