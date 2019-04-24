package com.sh.bank;

public class BankDTO {

	private String account;
	private String id;
	private String bankDay;
	private String bankName;
	private int balance;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankDay() {
		return bankDay;
	}
	public void setBankDay(String bankDay) {
		this.bankDay = bankDay;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
