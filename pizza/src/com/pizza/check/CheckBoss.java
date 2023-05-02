package com.pizza.check;

import static com.pizza.view.AppUI.checkRealBoss;
import static com.pizza.view.AppUI.inputInteger;

import com.pizza.common.AppService;
import com.pizza.menu.service.MenuService;


public class CheckBoss implements AppService {
	
	private AppService service;
	
	@Override
	public void start() {
		checkRealBoss();
		int code = inputInteger();
		
		if(code == 9999) {
			service = new MenuService();
		} else {
			System.out.println("\n잘못된 코드를 입력하셨습니다.");
			System.out.println("메인 화면으로 돌아갑니다.\n");
			return;
		}

		service.start();
	}
}