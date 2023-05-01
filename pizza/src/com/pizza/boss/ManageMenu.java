package com.pizza.boss;

import com.pizza.common.AppService;

import static com.pizza.view.AppUI.*;

public class ManageMenu implements AppService{

	@Override
	public void start() {
		while(true) {
			manageMenu();
			int selection = inputInteger();
			
			switch (selection) {
			case 1: {
				addMenu();
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

	private void addMenu() {
		System.out.println("\n------------------*** 메뉴 관리하기 ***------------------");
		System.out.print("메뉴명 : ");
		String menuName = inputString();
		
		System.out.print("가격 : ");
		int price = inputInteger();
		
		System.out.println();
	}
}
