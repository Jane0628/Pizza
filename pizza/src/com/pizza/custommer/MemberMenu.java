package com.pizza.custommer;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.memberMenu;

import java.time.LocalDate;
import java.util.List;
import com.pizza.common.AppService;
import com.pizza.order.repository.OrderRepository;
import com.pizza.order.domain.Order.*;
import com.pizza.user.domain.User;
import com.pizza.user.repository.UserRepository;

public class MemberMenu implements AppService {
	private final UserRepository userRepository = new UserRepository();
	private final OrderRepository orderRepository = new OrderRepository();

	
	@Override
	public void start() {
		memberMenu();
		int sel = inputInteger();
		Loop: while(true) {
		switch (sel) {
			case 1:
				changeUser();
				
				break Loop;
			case 2:
				deleteUser();
				break Loop;
			case 3:
				order();
				break Loop;
			default:
				System.out.print("정확하게 다시 입력해주세요.\n>>> ");
				
		}
		
		}
		
	}
	
	//주문
	private void order() {
			if(showSearchResult() > 0) {
				System.out.print("회원님의 번호를 입력해주세요.\n>>> ");
				int loginUserNum = inputInteger();
				System.out.print("\n>>> ");
				
				
				Order order = new Order();
				order.setOrderNumber(orderNumber);
				order.setOrderDate();
				order.setUserNumber(loginUserNum);
				order.setMenuList(menuList);
				order.setTotalPrice(totalPrice);
				


				orderRepository.addOrder(order);
				

		
		
	}

	// 회원 이름으로 검색 
	private List<User> searchUser() {
	System.out.print("\n회원명 : ");
	String name = inputString();
	return userRepository.findByUserName(name);
	}
	
	//회원 정보 수정
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
	List<User> users = searchUser();
	if(!users.isEmpty()) {
		System.out.println("\n---------------------- 회원 조회 결과 --------------------------");
		for(User user : users) {
			System.out.println(user);
		}
	} else {
		System.out.println("\n조회 결과가 없습니다.");
		System.out.println("처음 화면으로 이동합니다.\n");
		
		
	}
		
	return users.size();
	}
}
