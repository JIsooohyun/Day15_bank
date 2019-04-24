package com.sh.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sh.bank.BankDAO;
import com.sh.bank.BankDTO;
import com.sh.member.MemberDTO;

public class BankInsert {

	@Test
	public void test() throws Exception{
		BankDAO bankDAO = new BankDAO();
		BankDTO bankDTO = new BankDTO();
		MemberDTO memberDTO = new MemberDTO();
		
		bankDTO.setAccount("2111-1111");
		bankDTO.setId("eee");
		bankDTO.setBankDay("2019-03-03");
		bankDTO.setBankName("wwwBank");
		bankDTO.setBalance(3000);
		
		
		int result = bankDAO.insertBank(bankDTO);
		
		assertNotEquals(0, result);
	}

}
