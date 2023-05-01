package com.pizza.boss;

import com.pizza.common.AppService;
import com.pizza.menu.domain.Menu;
import com.pizza.menu.repository.MenuRepository;

import static com.pizza.view.AppUI.*;

public class ManageMenu implements AppService{
	
	private final MenuRepository menuRepository = new MenuRepository();

	@Override
	public void start() {
		while(true) {
			manageMenu();
			int selection = inputInteger();
			
			switch (selection) {
			case 1: {
				add();
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			default:
				System.out.println("올바른 메뉴 번호를 입력해주세요.");
			}
			
		}
		
	}

	
	// 메뉴 추가하기
	private void add() {
		System.out.println("\n------------------*** 메뉴 추가하기 ***------------------");
		System.out.println("(수정은 가격만 진행할 수 있으니 신중하게 기입해 주세요.)");
		
		System.out.print("메뉴명 : ");
		String menuName = inputString();
		
		System.out.print("가격 : ");
		int price = inputInteger();
		
		String side;
		while(true) {
			System.out.println("사이드 메뉴인가요? [Y / N]");
			System.out.print(">>> ");
			side = inputString().toUpperCase();
			
			if(side.equals("Y") || side.equals("N") || side.equals("ㅛ") || side.equals("ㅜ")) {
				break;
			} else {
				System.out.println("\n올바른 값으로 입력해주세요.\n");
			}
		}
		
		Menu newMenu = new Menu();
		newMenu.setMenuName(menuName);
		newMenu.setPrice(price);
		
		menuRepository.addMenu(newMenu, side);
	}
}
