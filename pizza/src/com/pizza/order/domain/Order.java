package com.pizza.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.pizza.menu.domain.Menu;

public class Order {
	
	private int orderNo;
	private LocalDateTime orderDate;
	private int memberNo;
	private List<Menu> menuList;
	private int totalPrice;

	public Order() {}

	public Order(int orderNo, LocalDateTime orderDate, int memberNo, List<Menu> menuList, int totalPrice) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.memberNo = memberNo;
		this.menuList = menuList;
		this.totalPrice = totalPrice;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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

