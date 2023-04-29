package com.pizza.custommer;

import com.pizza.common.AppService;

import static com.pizza.view.AppUI.*;

import com.pizza.user.domain.User;

public class nonmemMenu implements AppService {
	
	@Override
	public void start() {
		while(true) {
		nonmemMenu();
		int sel = inputInteger();
			
		switch (sel) {
		case 1:
			beforeJoin();
			break;
		case 2:
			join(); //주문으로 나중에 수정
			break;
		default:
			System.out.println("정확하게 입력해주세요.");
		}
		System.out.println("\n-------------계속 진행하시려면 ENTER를 누르세요--------------");
		inputString();
		}
	}

	//회원가입 전 혜택 공지
	public static void beforeJoin() {
		System.out.println("\n--------------------- 회원 가입시 혜택 ---------------------");
		System.out.println("1. 생일 당일 주문시 30% 할인!!");
		System.out.println("2. 스탬프 10개 모으면 피자 한 판 무료!!");
		System.out.println();
		System.out.println("회원가입을 진행하시겠습니까? (Y/N)");
		System.out.print(">>> ");
		String answer = inputString();
		System.out.println();
	

		switch (answer.toUpperCase()) {
		case "Y":
			join();
			break;
		case "N":
			System.out.println("전 화면으로 돌아갑니다.");
			nonmemMenu();
			break;
		default:
			System.out.println("정확하게 입력해주세요.");
		}

		System.out.println("\n-------------계속 진행하시려면 ENTER를 누르세요--------------");
		inputString();
	}



	//회원가입
	public static void join() {
		System.out.println("------------------- 회원가입을 시작합니다 --------------------");
		System.out.println();
		System.out.println("이름");
		System.out.print(">>> ");
		String name = inputString();
		System.out.println();
		System.out.println("생일 (MMNN)");
		System.out.println("생일은 가입 후에 변경이 불가능하니 신중하게 입력해주세요!!!");
		System.out.print(">>> ");
			String birthDay = inputString();
		if(birthDay.length()!=4) {
			System.out.println("정확하게 입력해주세요.");
			System.out.print(">>> "); //생일로직도 부족한 것 같다
			birthDay = inputString();
		} System.out.println(); 
			System.out.println("전화번호 (01X-XXXX-XXXX)");
			System.out.print(">>> "); 
			String phone = inputString();
		if(phone.length()!=13) {
			System.out.println("정확하게 입력해주세요.");
			System.out.print(">>> "); 
			phone = inputString();  //전화번호 로직 부족한 것 같다
			
		} System.out.println(); 
			System.out.println("집 주소");
			System.out.print(">>> "); 
			String address = inputString();
			System.out.println(); 
			
			User user = new User();
			user.setUserName(name);
			user.setBirthDay(birthDay);
			user.setPhoneNumber(phone);
			user.setAddress(address);
			
//			userRepository.addUser(user); 여기서부터 다시
		
			System.out.println("-------------------- 회원가입 완료 ---------------------");
			System.out.println("\n----------------- 회원 메뉴로 이동합니다 ------------------");
			memberMenu();
	}
		
			
}

