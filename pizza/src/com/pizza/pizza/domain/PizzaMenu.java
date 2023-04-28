package com.pizza.pizza.domain;

public class PizzaMenu {

	private int menuNo;
	private String menuName;
	private Size pizzaSize;
	private int price;
	
	public PizzaMenu() {}

	public PizzaMenu(int menuNo, String menuName, Size pizzaSize, int price) {
		super();
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.pizzaSize = pizzaSize;
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

	public Size getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(Size pizzaSize) {
		this.pizzaSize = pizzaSize;
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
			   ", 사이즈 : " + pizzaSize +
			   ", 가격 : " + price;
	}
	
	
	
}
