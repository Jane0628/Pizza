package com.pizza.main;

import com.pizza.check.CheckBoss;
import com.pizza.check.CheckMember;
import com.pizza.common.AppService;


public class AppController {

	private AppService service;
	
	// 사장인지 고객인지 구분하기
	public void chooseSystem(int check) {
		
		switch (check) {
		case 1:
			service = new CheckBoss();
			break;
		case 2:
			service = new CheckMember();
			break;
			
		default:
			System.out.println("올바른 값으로 입력해주시기 바랍니다.");
		}
		
		service.start();
		
	}
	
}
		


