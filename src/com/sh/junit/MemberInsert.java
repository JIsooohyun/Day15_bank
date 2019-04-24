package com.sh.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sh.member.MemberDAO;
import com.sh.member.MemberDTO;

public class MemberInsert {

	@Test
	public void test() throws Exception{
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("qqq");
		memberDTO.setPw("qqq");
		memberDTO.setName("q1");
		memberDTO.setPhone("P1111");
		memberDTO.setEmail("E1111");
		
		int result = memberDAO.insert(memberDTO);
		
		assertNotEquals(result, 0);
	}

}
