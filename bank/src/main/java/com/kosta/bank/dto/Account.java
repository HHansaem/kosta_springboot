package com.kosta.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data  //getter, setter, toString 모두 포함하는 어노테이션
@Getter
@Setter
@ToString
@NoArgsConstructor  //기본 생성자 만듦
@AllArgsConstructor  //모든 생성자 만듦 (이거 사용하려면 반드시 @NoArgsConstructor도 적어야 함)
public class Account {
	private String id;
	private String name;
	private Integer balance;
	private String type;
	private String grade;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
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
