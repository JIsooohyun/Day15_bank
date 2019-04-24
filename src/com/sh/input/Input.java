package com.sh.input;


import java.util.Scanner;

import com.sh.bank.BankDTO;
import com.sh.member.MemberDTO;
import com.sh.pay.PayDTO;

public class Input {
	
	private Scanner sc;
	
	public Input() {
		sc = new Scanner(System.in);
	}
	
	public PayDTO payInput(String account) throws Exception{
		PayDTO payDTO = new PayDTO();
		
		payDTO.setAccount(account);
		System.out.println("날짜 입력 : ");
		payDTO.setDealDate(sc.next());
		System.out.println("금액 입력 : ");
		payDTO.setMoney(sc.nextInt());
		payDTO.setKind(1);
		
		return payDTO;
		
	}
 
	public PayDTO payOutput(String account) throws Exception{
		PayDTO payDTO = new PayDTO();
		payDTO.setAccount(account);
		System.out.println("날짜 입력 : ");
		payDTO.setDealDate(sc.next());
		System.out.println("금액입력 : ");
		payDTO.setMoney(sc.nextInt());
		payDTO.setKind(2);
		
		return payDTO;
	}
	public BankDTO bankInput() throws Exception{
		BankDTO bankDTO  = new BankDTO();
		System.out.println("계좌 입력  : ");
		bankDTO.setAccount(sc.next());
		System.out.println("ID 입력 : ");
		bankDTO.setId(sc.next());
		System.out.println("날짜 입력 : ");
		bankDTO.setBankDay(sc.next());
		System.out.println("계좌이름 입력 : ");
		bankDTO.setBankName(sc.next());
		System.out.println("잔고 입력 : ");
		bankDTO.setBalance(sc.nextInt());
		
		return bankDTO;
		
	}
	
	public MemberDTO memberInput() throws Exception{
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("ID 입력 : ");
		memberDTO.setId(sc.next());
		System.out.println("PW 입력 : ");
		memberDTO.setPw(sc.next());
		System.out.println("Name 입력 : ");
		memberDTO.setName(sc.next());
		System.out.println("Phone 입력 : ");
		memberDTO.setPhone(sc.next());
		System.out.println("Email 입력 : ");
		memberDTO.setEmail(sc.next());
		
		return memberDTO;
	}
	
	
}
