package com.sh.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sh.connect.DBConnector;
import com.sh.member.MemberDTO;
import com.sh.pay.PayDTO;

public class BankDAO {

	public int accountDown(String account, PayDTO payDTO) throws Exception{
		Connection conn = DBConnector.connect();

		String sql = "update bank set balance = balance-? where account = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);

		st.setInt(1, payDTO.getMoney());
		
		st.setString(2, account);

		int result = st.executeUpdate();

		DBConnector.disConnect(st, conn);

		return result;

	}
	public int accountUpdate(String account, PayDTO payDTO, Connection conn) throws Exception {
		String sql = "update bank set balance =balance + ? where account = ?";

		PreparedStatement st = conn.prepareStatement(sql);

		st.setInt(1, payDTO.getMoney());
		st.setString(2, account);

		int result = st.executeUpdate();

		st.close();

		return result;

	}

	public PayDTO accountCheck(String account) throws Exception{
		Connection conn = DBConnector.connect();
		PayDTO payDTO = new PayDTO();
		String sql = "select account from bank where account = ?";

		PreparedStatement st = conn.prepareStatement(sql);

		st.setString(1, account);

		ResultSet rs = st.executeQuery();

		if(rs.next()) {

			payDTO.setAccount(rs.getString(1));
		}else {
			payDTO.setAccount("strange");

		}
		DBConnector.disConnect(st, conn, rs);

		return payDTO;

	}

	public BankDTO balanceCheck(String account) throws Exception{
		Connection conn =  DBConnector.connect();
		BankDTO bankDTO = new BankDTO();
		String sql = "select balance from bank where account = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, account);
		
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			bankDTO.setBalance(rs.getInt(1));
		}
		DBConnector.disConnect(st, conn);
		
		return bankDTO;
	}
	public int insertBank(BankDTO bankDTO) throws Exception{
		Connection conn = DBConnector.connect();

		String sql = "insert into bank values(?, ?, ?, ?, ?)";

		PreparedStatement st = conn.prepareStatement(sql);

		st.setString(1, bankDTO.getAccount());
		st.setString(2, bankDTO.getId());
		st.setString(3, bankDTO.getBankDay());
		st.setString(4, bankDTO.getBankName());
		st.setInt(5, bankDTO.getBalance());

		int result = st.executeUpdate();

		DBConnector.disConnect(st, conn);

		return result;
	}

}
