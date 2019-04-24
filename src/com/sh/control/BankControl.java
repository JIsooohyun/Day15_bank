package com.sh.control;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.sh.bank.BankDAO;
import com.sh.bank.BankDTO;
import com.sh.connect.DBConnector;
import com.sh.input.Input;
import com.sh.member.MemberDAO;
import com.sh.member.MemberDTO;
import com.sh.pay.PayDAO;
import com.sh.pay.PayDTO;
import com.sh.view.ViewBank;

public class BankControl {
	private Scanner sc;
	private Input input;
	private ViewBank viewBank;
	private MemberDTO memberDTO;
	private MemberDAO memberDAO;
	private BankDTO bankDTO;
	private BankDAO bankDAO;
	private PayDTO payDTO;
	private PayDAO payDAO;

	public BankControl() {
		sc = new Scanner(System.in);
		input = new Input();
		viewBank = new ViewBank();
		memberDAO = new MemberDAO();
		memberDTO = new MemberDTO();
		bankDTO = new BankDTO();
		bankDAO = new BankDAO();
		payDAO = new PayDAO();
		payDTO = new PayDTO();
	}

	public String check() throws Exception{
		String account = null;
		while(true) {
			System.out.println("계좌번호를 입력 : ");
			account = sc.next();
			payDTO = bankDAO.accountCheck(account);

			if(payDTO.getAccount().equals("strange")) {
				System.out.println("다시 입력하세요");


			} else {
				account = payDTO.getAccount();
				break;
			}
		}
		return account;
	}

	public void control() throws Exception{
		Connection conn = null;
		ArrayList<PayDTO> ar = new ArrayList<PayDTO>();
		int select = 0;
		String account = null;
		boolean check = true;
		while(check) {
			System.out.println("1. 회원 가입");
			System.out.println("2. 계좌 생성");
			System.out.println("3. 입금 하기");
			System.out.println("4. 입금 조회");
			System.out.println("5. 출금 하기");
			System.out.println("6. 출금 조회");
			System.out.println("7. 종       료");
			System.out.println("===========");
			select = sc.nextInt();

			switch(select) {
			case 1:
				memberDTO = input.memberInput();
				select = memberDAO.insert(memberDTO);
				if(select > 0) {
					viewBank.message("--가입 성공--");
				}else {
					viewBank.message("--가입 실패--");
				}
				break;
			case 2:

				bankDTO = input.bankInput();
				select = bankDAO.insertBank(bankDTO);

				if(select > 0) {
					viewBank.message("--계좌 생성 성공--");
				}else {
					viewBank.message("계좌 생성 실패--");
				}
				break;
			case 3: //입금하기
				try {
					conn = DBConnector.connect();
					conn.setAutoCommit(false);

					account = this.check();
					payDTO = input.payInput(account);
					select = payDAO.payInsert(payDTO, conn);
					
					if(select <1) {
						throw new Exception();
					}
					
					select = payDTO.getKind();
					if(select == 1) {  //만약에 결과가 0이 나왔을 경우나 에러가 생겼을 경우 
						// payDAO에서 값이 들어간 경우를 rollback 해줘야 하니까
						// 여기서 commit과 rollback을 해준다. 
						bankDAO.accountUpdate(account, payDTO, conn);
					}else {
						throw new Exception();
					}
					conn.commit();
				}catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
				}finally {
					
					conn.setAutoCommit(true);
					conn.close();
				}
				break;
			case 4: // 입금조회
				System.out.println("입금을 조회할 계좌번호를 입력해주세요");
				account = this.check();

				ar = payDAO.paySelectList(account, 1);
				viewBank.payView(ar);
				break;
			case 5:  //출금하기
				try {
					conn = DBConnector.connect();
					conn.setAutoCommit(false);

					account = this.check();
					bankDTO = bankDAO.balanceCheck(account);
					int balance = bankDTO.getBalance();

					payDTO=input.payOutput(account);
					int money = payDTO.getMoney();

					if(balance>=money) {
						select = payDAO.payInsert(payDTO, conn);
						if(select <1) {
							throw new Exception();
						}
						select = payDTO.getKind();
						if(select == 2) {
							bankDAO.accountDown(account, payDTO);
						}else if(select <1) {
							throw new Exception();
						}
					}else {
						System.out.println("잔고가 부족합니다.");
					}
					conn.commit();
				}catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
				}finally {
					conn.setAutoCommit(true);
					conn.close();
				}
				break;
			case 6: //입금하기
				System.out.println("출금을 조회할 계좌번호를 입력해주세요");
				account = this.check();

				ar = payDAO.paySelectList(account, 2);
				viewBank.payView(ar);

				break;
			default :
				System.out.println("종료합니다.");
				check = ! check;
			}
		}
	}
}
