package com.kosta.bank.dto;

import com.kosta.bank.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {  //프론트가 가져오는 데이터
	private String id;
	private String name;
	private Integer balance;
	private String type;
	private String grade;
	
	public Account toAccount() {
		return new Account(id, name, balance, type, grade);
	}
}
