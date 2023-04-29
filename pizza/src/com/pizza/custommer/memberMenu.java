package com.pizza.custommer;

import com.pizza.common.AppService;
import static com.pizza.view.AppUI.*;

import java.util.List;

public class memberMenu implements AppService {

	@Override
	public void start() {
		memberMenu();
		int sel = inputInteger();
		
//		switch (sel) {
//		break;
//		case 1:
//			;
//			break;
//		case 2:
//			//주문
//			break;
//		default:
//			System.out.println("정확하게 입력해주세요.");
//		}
//		System.out.println("\n====== 계속 진행하시려면 ENTER를 누르세요 ======");
//		inputString();
//	}
}


//
////회원 이름으로 검색 비즈니스 로직
//private List<User> searchUser() {
//	System.out.println("\n### 조회할 회원의 이름을 입력하세요.");
//	System.out.println(">>> ");
//	String name = inputString();
//	return userRepository.findByUserName(name);
//}
//
//
//private int showSearchResult() {
//	List<User> users = searchUser();
//	
//	if(!users.isEmpty()) {
//		System.out.println("\n======================= 회원 조회 결과 =======================");
//		for(User user : users) {
//			System.out.println(user);
//		}
//	} else {
//		System.out.println("\n### 조회 결과가 없습니다.");
//	}
//	
//	return users.size();
//}
//
////회원 탈퇴 비즈니스 로직
//private void deleteUser() {
//	
//	//삭제할 회원의 이름을 입력받아서 삭제 대상 회원만 가지고 오자.	
//	if(showSearchResult() > 0) {
//		System.out.println("\n### 탈퇴할 회원의 번호를 입력하세요.");
//		System.out.print(">>> ");
//		int delUserNum = inputInteger();
//		userRepository.deleteUser(delUserNum);
//	} 
//	
//	
//}
//
//
//}
//
//}