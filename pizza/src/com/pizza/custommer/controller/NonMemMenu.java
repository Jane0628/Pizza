package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.memberBenefit;
import static com.pizza.view.AppUI.nonmemMenu;

import com.pizza.common.AppService;
import com.pizza.custommer.domain.Member;
import com.pizza.custommer.service.NonMemService;


public class NonMemMenu implements AppService {

	private AppService service;
	private final NonMemService nonMemService = new NonMemService();
	
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
				break Loop;
			default:
				System.out.println("\n 정확하게 다시 입력해주세요.");
			}
		}
		
	}

	//회원가입 전 혜택 공지
	public boolean beforeJoin() {
		while(true) {
			memberBenefit();
			String answer = inputString().toUpperCase();

			switch (answer) {
			case "Y": case "ㅛ":
				join();
				return true;

			case "N": case "ㅜ":
				return false;

			default:
				System.out.println("\n 정확하게 다시 입력해주세요.");
			}
			
		}
	}
	

	// 회원가입
	public void join() {
		System.out.println("\n--------------*** 회원가입을 시작합니다 ***--------------");
		System.out.println("(생일은 가입 후에 변경이 불가능하니 정확히 입력해주세요!)");
		System.out.print(" 이름 : ");
		String name = inputString();

		String birthDay;
		while(true) {
			System.out.print(" 생일 (MMNN) : ");
			birthDay = inputString();

			// 길이로 맞게 보내주셨는지 판단하기
			if(birthDay.length() != 4) { 										
				System.out.println("\n 생일을 월과 일만 정확하게 입력해주세요.");
				
			// 첫 번째 두 번째 숫자로 올바른 월 확인하기
			} else if((birthDay.charAt(0) != '0' && birthDay.charAt(0) != '1') 
					|| birthDay.charAt(0) == '1' && Character.getNumericValue(birthDay.charAt(1)) > 2) { 
				System.out.println("\n 유효한 월을 입력해주세요. [01 ~ 12]");
				
			// 세 번째 네 번째 숫자로 올바른 날짜 확인하기
			} else if(Character.getNumericValue(birthDay.charAt(2)) > 3 
					|| birthDay.charAt(2) == '3' 
					&& Character.getNumericValue(birthDay.charAt(3)) > 1){	
				System.out.println("\n 유효한 날짜를 입력해주세요. [01 ~ 31]");
				
			} else {
				break;
			}
		}
		
		String phone;
		while(true) {
			System.out.print(" 전화번호 (XXX-XXXX-XXXX) : "); 
			phone = inputString();
			
			if(phone.length() == 13 && phone.charAt(3) == '-' && phone.charAt(8) == '-') {
				break;
			} else {
				System.out.println("\n '-'를 포함한 13자리의 형태로 입력해주세요.");				
			}
			
		}
		
		System.out.print(" 집 주소 : "); 
		String address = inputString();

		Member user = new Member();
		user.setUserName(name);
		user.setBirthDay(birthDay);
		user.setAddress(address);
		user.setPhoneNumber(phone);
		
		if(nonMemService.addUser(user) == 1) {
			service = new MemberMenu();
			service.start();
		}
		
		return;
	}

}



