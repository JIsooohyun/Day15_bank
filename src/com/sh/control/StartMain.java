package com.sh.control;

import java.sql.Connection;

public class StartMain {

	public static void main(String[] args) {
		
		BankControl bc = new BankControl();
		try {
			bc.control();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		
		}

	}

}
