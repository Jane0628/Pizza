package com.pizza.custommer;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.memberMenu;

import java.util.List;

import com.pizza.common.AppService;
import com.pizza.user.domain.User;
import com.pizza.user.repository.UserRepository;

public class MemberMenu implements AppService {
	private final UserRepository userRepository = new UserRepository();
	
	@Override
	public void start() {
		memberMenu();
		int sel = inputInteger();
		Loop: while(true) {
		switch (sel) {
			case 1:
				showSearchResult();
				break Loop;
			case 2:
				deleteUser();
				break Loop;
			default:
				System.out.print("정확하게 다시 입력해주세요.\n>>> ");
				sel = inputInteger();
		}
		
		}
		
	}

	// 회원 이름으로 검색 
	private List<User> searchUser() {
	System.out.print("\n회원명 : ");
	String name = inputString();
	return userRepository.findByUserName(name);
	}
	

	// 회원 탈퇴
		private void deleteUser() {
		if(showSearchResult() > 0) {
			System.out.print("\n회원명 : ");
			int delUserNum = inputInteger();
			userRepository.deleteUser(delUserNum);
		}
		}
	
	// 검색 결과 출력
	private int showSearchResult() {
	List<User> users = searchUser();
	if(!users.isEmpty()) {
		System.out.println("\n----------------- 회원 조회 결과 --------------------");
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
