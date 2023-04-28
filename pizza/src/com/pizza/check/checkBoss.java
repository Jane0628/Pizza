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
		
		
	}
}
