package com.pizza.menu.domain;

public class Menu {

	private String menuNo;
	private String menuName;
	private int price;
	
	public Menu() {}

	public Menu(String menuNo, String menuName, int price) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		if(menuName.length() >= 12) {
			return " # " + menuNo +
				   ".\t메뉴명 : " + menuName +
				   "\t 가격 : " + price + "원";
		} else {
			return " # " + menuNo +
				   ".\t메뉴명 : " + menuName +
				   "\t\t 가격 : " + price + "원";
		}
		
	}	
	
}
