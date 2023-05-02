package com.pizza.main;

import com.pizza.view.AppUI;


import static com.pizza.view.AppUI.*;

public class Main {

	public static void main(String[] args) {
		
	
		AppController controller = new AppController();
		
		while(true) {
			AppUI.checkBossOrCustomer();
			int check = inputInteger();
			controller.chooseSystem(check);
			
		
		}

	}

}
