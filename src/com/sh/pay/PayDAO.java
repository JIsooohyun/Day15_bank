package com.sh.pay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sh.connect.DBConnector;

public class PayDAO {

	public ArrayList<PayDTO> paySelectList(String account, int num) throws Exception{
		Connection conn = DBConnector.connect();
		PayDTO payDTO = null;
		ArrayList<PayDTO> ar = new ArrayList<PayDTO>();
		String sql = "select Account, DealDate, Money, Kind from pay where account = ? and kind=? order by DealDate desc";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, account);
		st.setInt(2, num);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			payDTO = new PayDTO();
			payDTO.setAccount(rs.getString(1));
			payDTO.setDealDate(rs.getString(2));
			payDTO.setMoney(rs.getInt(3));
			payDTO.setKind(rs.getInt(4));
			ar.add(payDTO);
		}
		DBConnector.disConnect(st, conn, rs);
		
		return ar;
	}
	
	
	
	public int payInsert(PayDTO payDTO, Connection conn) throws Exception {
		
		
		String sql = "insert into pay values(num_seq.nextval, ?, ?, ?, ?)";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, payDTO.getAccount());
		st.setString(2, payDTO.getDealDate());
		st.setInt(3, payDTO.getMoney());
		st.setInt(4, payDTO.getKind());
		
		int result = st.executeUpdate();
		
		st.close();
		
		return result;
		
	}
}
