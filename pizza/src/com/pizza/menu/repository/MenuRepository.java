package com.pizza.menu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.DataBaseConnection;
import com.pizza.menu.domain.Menu;

public class MenuRepository {
	
	private DataBaseConnection connection = DataBaseConnection.getInstance();
	
	// 메뉴 추가하기
	public void addMenu(Menu menu, String side) {
		String sql;
		
		switch (side) {
		case "Y": case "ㅛ": {
			sql = "INSERT INTO menu "
				+ "VALUES('S' || side_seq.NEXTVAL, ?, ?)";
			break;
		}
		
		default:
			sql = "INSERT INTO menu "
				+ "VALUES(main_seq.NEXTVAL, ?, ?)";			
		}
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getPrice());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("\n메뉴 추가가 완료되었습니다. :)");
			} else {
				System.out.println("\n메뉴 추가를 실패하셨습니다. :(");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// 가격 수정하기
	public void updatePrice(String menu) {
		
	}
	
	// 전체 조회하기
	public List<Menu> viewTable(String keyword) {
		String sql = "SELECT * FROM menu "
				   + "WHERE menu_name LIKE '%" + keyword + "%' "
			   	   + "ORDER BY menu_no";
		
		List<Menu> menuList = new ArrayList<>();
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
				
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("menu_no"),
									 rs.getString("menu_name"),
									 rs.getInt("price"));
				menuList.add(menu);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}
	
	// 메뉴 번호로 메뉴 조회하기
	public List<Menu> viewMenu(String keyword) {
		String sql = "SELECT * FROM menu "
				   + "WHERE menu_no LIKE '%" + keyword + "%' "
			   	   + "ORDER BY menu_no";
		
		List<Menu> menuList = new ArrayList<>();
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
				
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("menu_no"),
									 rs.getString("menu_name"),
									 rs.getInt("price"));
				menuList.add(menu);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}

}
