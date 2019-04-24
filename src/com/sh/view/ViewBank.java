package com.sh.view;

import java.util.ArrayList;

import com.sh.member.MemberDTO;
import com.sh.pay.PayDTO;

public class ViewBank {

	public void memberView(MemberDTO memberDTO) {
		System.out.println("ID : "+memberDTO.getId());
		System.out.println("PW : "+memberDTO.getPw());
		System.out.println("Name : "+memberDTO.getName());
		System.out.println("Phone : "+memberDTO.getPhone());
		System.out.println("Email : "+memberDTO.getEmail());
		System.out.println("==============");
	}

	public void message(String message) {
		System.out.println(message);
	}

	public void payView(ArrayList<PayDTO> ar) {
		for(PayDTO payDTO : ar) {
			System.out.println("Account : "+payDTO.getAccount());
			System.out.println("DealDate : "+payDTO.getDealDate());
			System.out.println("Money : "+payDTO.getMoney());
			System.out.println("Kind : "+payDTO.getKind());
			System.out.println("=================");
		}
	}

}
