package com.sh.junit;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.sh.pay.PayDAO;
import com.sh.pay.PayDTO;

public class PayInsert {

	@Test
	public void test() throws Exception{
		int result = 0;
		Connection conn = null;
		PayDAO payDAO = new PayDAO();
		PayDTO payDTO = new PayDTO();
		
		payDTO.setAccount("1111-1111");
		payDTO.setDealDate("2019-03-03");
		payDTO.setMoney(300);
		payDTO.setKind(1);
		result =  payDAO.payInsert(payDTO, conn);
		assertNotEquals(0, result);
	}

}
