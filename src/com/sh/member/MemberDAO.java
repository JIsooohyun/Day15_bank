package com.sh.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.sh.connect.DBConnector;

public class MemberDAO {
	
	public int insert(MemberDTO memberDTO) throws Exception{
		Connection conn = DBConnector.connect();
		
		String sql = "insert into member values(?, ?, ?, ?, ?)";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getPhone());
		st.setString(5, memberDTO.getEmail());
		
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, conn);
		
		return result;
	}

}
