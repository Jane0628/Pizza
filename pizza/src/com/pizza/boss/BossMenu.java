package com.pizza.boss;

import com.pizza.common.AppService;

import static com.pizza.view.AppUI.*;

public class BossMenu implements AppService {

	@Override
	public void start() {
		bossMenu();
		int selection = inputInteger();
		
		switch (selection) {
		case 1: // 메뉴 관리하기
			
			break;
		case 2: // 주문 확인하기
			
			break;
		case 3: // 매출 확인하기
			
			break;

		default:
			break;
		}
		
	}
}
