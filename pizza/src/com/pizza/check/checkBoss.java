package com.pizza.check;

import com.pizza.common.AppService;
import com.pizza.boss.BossMenu;

import static com.pizza.view.AppUI.*;


public class checkBoss implements AppService {
	
	private AppService service;
	
	@Override
	public void start() {
		checkRealBoss();
		int code = inputInteger();
		
		if(code == 9999) {
			service = new BossMenu();
		} else {
			System.out.println("\n잘못된 코드를 입력하셨습니다.");
			System.out.println("메인 화면으로 돌아갑니다.\n");
			return;
		}

		service.start();
	}
}