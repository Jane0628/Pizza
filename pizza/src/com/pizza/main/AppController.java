package com.pizza.main;

import com.pizza.check.checkBoss;
import com.pizza.check.checkMember;
import com.pizza.common.AppService;


public class AppController {

	private AppService service;
	
	// 사장인지 고객인지 구분하기
	public void chooseSystem(int check) {
		
		switch (check) {
		case 1:
			service = new checkBoss();
			break;
		case 2:
			service = new checkMember();
			break;
			
		default:
			System.out.println("올바른 값으로 입력해주시기 바랍니다.");
		}
		
		service.start();
		
	}
	
}
		


