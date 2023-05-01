package com.pizza.menu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pizza.common.DataBaseConnection;
import com.pizza.menu.domain.Menu;

public class MenuRepository {
	
	private DataBaseConnection connection = DataBaseConnection.getInstance();
	
	public void addMenu(Menu menu, String side) {
		String sql;
		
		switch (side) {
		case "Y": case "ㅛ": {
			sql = "INSERT INTO menu "
				+ "VALUES('S' || menu_seq.NEXTVAL, ?, ?)";
			break;
		}
		
		default:
			sql = "INSERT INTO menu "
					+ "VALUES(menu_seq.NEXTVAL, ?, ?)";			
		}
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getPrice());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("메뉴 추가가 완료되었습니다. :)");
			} else {
				System.out.println("메뉴 추가를 실패하셨습니다. :(");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
