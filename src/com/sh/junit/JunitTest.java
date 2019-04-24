package com.sh.junit;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.sh.connect.DBConnector;

public class JunitTest {

	@Test
	public void test() throws Exception{
		Connection conn = DBConnector.connect();
		assertNotNull(conn);
	}

}
