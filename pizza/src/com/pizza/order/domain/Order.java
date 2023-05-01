package com.pizza.order.domain;

import java.time.LocalDate;
import java.util.List;

import com.pizza.menu.domain.Menu; //??맞는지 모르겠다

public class Order {
	
		private int orderNumber;
		private int userNumber;
		private LocalDate orderDateraw;
		private List<Menu> menuList;  //?? 맞는지 모르겠다
		private int totalPrice;
		
		int m = orderDateraw.getMonthValue();
        int d = orderDateraw.getDayOfMonth();
        
        String mm = String.format("%02d", m);
        String dd = String.format("%02d", d);
        String orderDate = mm+dd;

		
     
		
		public Order() {}

		public Order(int orderNumber, int userNumber, String orderDate, List<Menu> menuList, int totalPrice) {
			super();
			this.orderNumber = orderNumber;
			this.userNumber = userNumber;
			this.orderDate = orderDate;
			this.menuList = menuList;
			this.totalPrice = totalPrice;
		}

		public int getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(int orderNumber) {
			this.orderNumber = orderNumber;
		}

		public int getUserNumber() {
			return userNumber;
		}

		public void setUserNumber(int userNumber) {
			this.userNumber = userNumber;
		}

		public String getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}

		public List<Menu> getMenuList() {
			return menuList;
		}

		public void setMenuList(List<Menu> menuList) {
			this.menuList = menuList;
		}

		public int getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}

		
	}

