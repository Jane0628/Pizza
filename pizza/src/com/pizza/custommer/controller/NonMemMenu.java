package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.menu.repository.MenuRepository.*;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.nonmemMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.AppService;
import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;
import com.pizza.menu.domain.Menu;
import com.pizza.menu.repository.MenuRepository;
import com.pizza.order.domain.Order;


public class NonMemMenu implements AppService {
	
	private DataBaseConnection connection = 
			DataBaseConnection.getInstance();
	
	MenuRepository menuRepository = new MenuRepository();

	@Override
	public void start() {
		if(beforeJoin()) return;
		Loop:while(true) {
			nonmemMenu();
			int sel = inputInteger();

			switch (sel) {
			case 1:
				beforeJoin();
				break Loop;
			case 2:
				return;
			default:
				System.out.println("\n정확하게 다시 입력해주세요.\n>>> ");
				System.out.println();
				sel = inputInteger();
			}
		}
	}
	


	//회원가입 전 혜택 공지
	public boolean beforeJoin() {
		System.out.println("\n--------------------- 회원 가입 시 혜택 ---------------------");
		System.out.println("1. 생일 당일 주문시 30% 할인!!");
		System.out.println("2. 스탬프 10개 모으면 피자 한 판 무료!!");
		System.out.println();
		System.out.println("회원가입을 진행하시겠습니까? (Y/N)");
		System.out.print(">>> ");
		String answer = inputString().toUpperCase();
		Loop: while(true) {
			switch (answer.toUpperCase()) {
			case "Y": case "ㅛ":
				join();
				return true;
			case "N": case "ㅜ":
				System.out.println("\n비회원 메뉴로 돌아갑니다.\n");
				return false;
			default:
				System.out.print("\n정확하게 다시 입력해주세요.\n>>> ");
				answer = inputString().toUpperCase();
			}
		}

	}



	// 회원가입
	public void join() {
		System.out.println("------------------- 회원가입을 시작합니다 --------------------");
		System.out.println("(생일은 가입 후에 변경이 불가능하니 정확히 입력해주세요!!!)");
		System.out.print("이름 : ");
		String name = inputString();

		String birthDay;

		Loop: while(true) {
			System.out.print("\n생일 (MMNN) : ");
			birthDay = inputString();

			if(birthDay.length() != 4) { 										// 길이로 맞게 보내주셨는지 판단하기
				System.out.println("\n생일을 월과 일만 정확하게 입력해주세요.");
			} else if((birthDay.charAt(0) != '0' && birthDay.charAt(0) != '1') 
					|| birthDay.charAt(0) == '1' && Character.getNumericValue(birthDay.charAt(1)) > 2) { // 첫 번째 두 번째 숫자로 올바른 월 확인하기
				System.out.println("\n유효한 월을 입력해주세요. [01 ~ 12]");
			} else if(Character.getNumericValue(birthDay.charAt(2)) > 3 
					|| birthDay.charAt(2) == '3' 
					&& Character.getNumericValue(birthDay.charAt(3)) > 1){	// 세 번째 네번 째 숫자로 올바른 날짜 확인하기
				System.out.println("\n유효한 날짜를 입력해주세요. [01 ~ 31]");
			} else {
				break Loop;
			}
		}

		System.out.println(); 
		System.out.print("전화번호 (XXX-XXXX-XXXX) : "); 
		String phone = inputString();

		while((!((phone.length()==13) && (phone.charAt(3) == '-') && (phone.charAt(8)== '-')))) {
			System.out.println("\n'-'를 포함한 13자리의 형태로 입력해주세요.");
			System.out.print("전화번호 (01X-XXXX-XXXX) : "); 
			phone = inputString();
		}

		

		System.out.println(); 
		System.out.print("집 주소 : "); 
		String address = inputString();
		System.out.println(); 

		Member user = new Member();
		user.setUserName(name);
		user.setBirthDay(birthDay);
		user.setAddress(address);
		user.setPhoneNumber(phone);
		


		addUser(user);//회원번호 왜 0으로 뜨는지 모르겠다

		
		return;
		
		
		
		
	}
	
	//회원 추가
			public void addUser(Member user) {
				System.out.println(user);
				String sql = "INSERT INTO pizza_members "
						+ "(member_no, member_name, b_day, phone_no, address)"
						+ "VALUES(pizza_mem_seq.NEXTVAL,?,?,?,?)";
				
				
				try(Connection conn = connection.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql)) {
					
					pstmt.setString(1, user.getUserName());
				    pstmt.setString(2, user.getBirthDay());
					pstmt.setString(3, user.getPhoneNumber());
					pstmt.setString(4, user.getAddress());
					
					if(pstmt.executeUpdate() == 1) {
						System.out.println("회원가입이 완료되었습니다."); //회원메뉴로 가고싶다
						
					} else {
						System.out.println("회원 가입에 실패했습니다. 관리자에게 문의하세요.");
					}			
				} catch (Exception e) {
					e.printStackTrace();
				} 	
			}

}



