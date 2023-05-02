package com.pizza.boss;

import com.pizza.common.AppService;

import static com.pizza.view.AppUI.*;

public class BossMenu implements AppService {

	private AppService service;
	
	@Override
	public void start() {
		while(true) {
			bossMenu();
			int selection = inputInteger();
			
			switch (selection) {
			case 1: // 메뉴 관리하기
				service = new ManageMenu();
				break;
			case 2: // 매출 확인하기
				break;
				
			default:
				break;
			}
			
			if(service != null) {
				service.start();				
			}
			
		}
			
	}	
	
}
