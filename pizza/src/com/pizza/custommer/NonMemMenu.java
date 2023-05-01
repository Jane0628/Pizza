package com.pizza.custommer;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.nonmemMenu;
import static com.pizza.view.AppUI.memberMenu;

import com.pizza.common.AppService;
import com.pizza.user.domain.User;
import com.pizza.user.repository.UserRepository;



public class NonMemMenu implements AppService {
	private final UserRepository userRepository = new UserRepository();
	private static AppService service;

	@Override
	public void start() {
		beforeJoin();
		Loop:while(true) {
			nonmemMenu();
			int sel = inputInteger();

			switch (sel) {
			case 1:
				beforeJoin();
				break Loop;
			case 2:
				join(); //주문으로 나중에 수정
				break Loop;
			default:
				System.out.println("\n정확하게 다시 입력해주세요.\n>>> ");
				System.out.println();
				sel = inputInteger();
			}
		}
	}

	//회원가입 전 혜택 공지
	public void beforeJoin() {
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
				break Loop;
			case "N": case "ㅜ":
				System.out.println("\n비회원 메뉴로 돌아갑니다.\n");
				return;
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
			System.out.print("생일 (MMNN) : ");
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

		//전화번호 중복체크..?

		System.out.println(); 
		System.out.print("집 주소 : "); 
		String address = inputString();
		System.out.println(); 

		User user = new User();
		user.setUserName(name);
		user.setBirthDay(birthDay);
		user.setPhoneNumber(phone);
		user.setAddress(address);


		userRepository.addUser(user);

		System.out.println("감사합니다, 회원가입이 완료되었습니다.");
		System.out.println("회원 메뉴로 이동합니다.\n");
		memberMenu();
		int sel = inputInteger();
		
		
		
	}

}



