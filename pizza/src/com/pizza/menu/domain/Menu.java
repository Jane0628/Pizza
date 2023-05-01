package com.pizza.menu.domain;

public class Menu {

	private int menuNo;
	private String menuName;
	private int price;
	
	public Menu() {}

	public Menu(int menuNo, String menuName, int price) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
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
		return " 메뉴 번호 : " + menuNo +
			   ", 메뉴명 : " + menuName +
			   ", 가격 : " + price;
	}
	
	
	
}
