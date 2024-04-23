package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
	@Id  //primary key column
	private String id;
	@Column
	private String name;
	@Column
	private Integer balance;
	@Column
	private String type;
	@Column
	private String grade;
	
	public void deposit(int money) throws Exception {
		if(money < 0) throw new Exception("입금액 오류"); 
		balance += money;
	}
	
	public void withdraw(int money) throws Exception {
		if(balance < money) throw new Exception("잔액 부족 오류");
		balance -= money;
	}
	
	public Account(String id) {
		this.id = id;
	}
	
	public void transfer(String sid, String rid, int money) throws Exception {
		Account sacc = new Account(sid);
		Account racc = new Account(rid);
		if(sacc.getBalance() < money) throw new Exception("잔액 부족");
		sacc.withdraw(money);
		racc.deposit(money);
	}
}
