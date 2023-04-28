package com.pizza.main;

import com.pizza.check.checkBoss;
import com.pizza.common.AppService;
import com.pizza.view.AppUI;

public class AppController {

	private AppService service;
	
	// 사장인지 고객인지 구분하기
	public void chooseSystem(int check) {
		
		switch (check) {
		case 1:
			service = new checkBoss();
			break;
		case 2:
			AppUI.customerMenu();
			break;
			
		default:
			System.out.println("올바른 값으로 입력해주시기 바랍니다.");
		}
		
		service.start();
		
	}
	
	// 진짜 사장이 맞는지 구분하기
	

}
