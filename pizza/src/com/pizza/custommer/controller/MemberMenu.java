package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.memberMenu;

import java.util.List;

import com.pizza.common.AppService;
import com.pizza.custommer.domain.Member;
import com.pizza.custommer.repository.MemberRepository;
import com.pizza.order.domain.Order;
import com.pizza.order.repository.OrderRepository;

public class MemberMenu implements AppService {

	private final MemberRepository userRepository = new MemberRepository();
	private final OrderRepository orderRepository = new OrderRepository();


	@Override
	public void start() {
		memberMenu();

		while(true) {
			int sel = inputInteger();

			switch (sel) {
			case 1:
				changeUser();
				return;

			case 2:
				deleteUser();
				return;

			case 3:
				order();
				return;

			default:
				System.out.print("정확하게 다시 입력해주세요.\n>>> ");
			}

		}	

	}

	
	/*
	 	나영님! 약간 헷갈리시는 부분이 있는데 저한테 계속 물어보기 불편해하시는 것 같아서 주석으로 남겨용 ㅎㅎ
	 	
	 	저희 맨 처음에 앱에 들어올 때 회원인지 비회원인지 묻고, 회원일 경우 로그인을 진행하잖아요!
	 	그 때 그 정보를 토대로 이 사람이 누구인지를 기억해두면 좋을 것 같아요. 아마 전역변수로 설정하거나 매개값으로 넘겨주면 되지 않을까.. 싶구요!
	 	
	 	회원 번호가 null일 경우에는 비회원이라는 뜻이니까 비회원 테이블에서 조회를 진행하게끔 하면 될 것 같아요.
	 	
	 	주문을 진행할 때에는 앞의 정보를 토대로 회원의 정보를 따로 받아내지는 않으셔도 될 것 같구요.
	 	
	 	일단 전체 메뉴를 조회한 다음 (이거 메서드 제가 만들고 있습니다!) 메뉴 번호를 입력해서 어떤 것을 먹을 건지를 물어보면 될 것 같구요.
	 	
	 	그 번호들을 리스트에 담아서 주문 테이블에 넣으면 좋을 것 같아용. 만약에 타입 때문에 리스트가 안 담아진다면.. 흠.. 생각을 좀 더 해봐야할 것 같습니다.
	 	
	 	일단은 요 정도 정리해봤구용 차차 더 말씀드릴게요!
	 	
	 	오늘도 홧팅이구 감사합니당 :>
	 */
	
	// 주문
	private void order() {
		if(showSearchResult() > 0) {
			System.out.print("회원님의 번호를 입력해주세요.\n>>> ");
			int loginUserNum = inputInteger();
			System.out.print("\n>>> ");


			Order order = new Order();
			order.setMemberNo(loginUserNum);



			orderRepository.addOrder(order);

		}


	}

	// 회원 이름으로 검색 
	private List<Member> searchUser() {
		System.out.print("\n회원명 : ");
		String name = inputString();
		return userRepository.findByUserName(name);
	}

	// 회원 정보 수정
	private void changeUser() {
		if(showSearchResult() > 0) {
			System.out.print("회원님의 번호를 입력해주세요.\n>>> ");
			int cUserNum = inputInteger();
			userRepository.changeUser(cUserNum);

		}

	}


	// 회원 탈퇴
	private void deleteUser() {
		if(showSearchResult() > 0) {
			System.out.print("\n회원님의 번호를 입력해주세요.\n>>> ");
			int delUserNum = inputInteger();
			userRepository.deleteUser(delUserNum);
		}
	}

	// 검색 결과 출력
	private int showSearchResult() {
		List<Member> users = searchUser();
		if(!users.isEmpty()) {
			System.out.println("\n---------------------- 회원 조회 결과 --------------------------");
			for(Member user : users) {
				System.out.println(user);
			}
		} else {
			System.out.println("\n조회 결과가 없습니다.");
			System.out.println("처음 화면으로 이동합니다.\n");
		}

		return users.size();
	}
}
