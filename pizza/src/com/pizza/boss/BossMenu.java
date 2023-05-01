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
			case 2: // 주문 확인하기
				checkOrder();
				break;
			case 3: // 매출 확인하기
				checkSales();
				break;
				
			default:
				break;
			}
			
			if(service != null) {
				service.start();				
			}
			
		}
			
	}

	
	
	// 메뉴 관리하기
	
	// 주문 확인하기
	private void checkOrder() {
		// TODO Auto-generated method stub
		
	}
	
	
}
