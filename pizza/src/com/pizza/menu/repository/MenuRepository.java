package com.pizza.menu.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.pizza.common.DataBaseConnection;
import com.pizza.common.Sort;
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
	public int updatePrice(Menu menu, int newPrice) {
		String sql = "UPDATE menu SET price = ? "
				   + "WHERE menu_no = ?";
		
		int a = 0;
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setInt(1, newPrice);
			pstmt.setString(2, menu.getMenuNo());
			
			a = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" 오류가 발생했습니다.");
		}
		
		return a;
		
	}
	
	
	// 삭제하기
	public int deleteMenu(Menu menu) {
		String sql = "DELETE FROM menu "
				   + "WHERE menu_no = ?";
		
		int a = 0;
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, menu.getMenuNo());
			
			a = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" 오류가 발생했습니다.");
		}
		
		return a;
		
	}
	
	// 전메뉴 조회하기
	public List<Menu> viewWholeMenu() {
		String sql = "SELECT * FROM main "
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
		
		Collections.sort(menuList, new Sort());
		
		return menuList;
	}
	
	// 메뉴 조회하기
	public List<Menu> viewMenuThroughMenuName(String keyword) {
		String sqlMain = "SELECT * FROM main "
					   + "WHERE menu_name LIKE ? "
		   		       + "ORDER BY menu_no";
		
		String sqlSide = "SELECT * FROM side "
					   + "WHERE menu_name LIKE ? "
			    	   + "ORDER BY menu_no";
		
		List<Menu> menuList = new ArrayList<>();
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmtMain = conn.prepareStatement(sqlMain);
			PreparedStatement pstmtSide = conn.prepareStatement(sqlSide)) {
			
			pstmtMain.setString(1, "%" + keyword + "%");
			pstmtSide.setString(1, "%" + keyword + "%");
				
			ResultSet rsMain = pstmtMain.executeQuery();
			
			while(rsMain.next()) {
				Menu menu = new Menu(rsMain.getString("menu_no"),
									 rsMain.getString("menu_name"),
									 rsMain.getInt("price"));
				menuList.add(menu);
			}
			
			ResultSet rsSide = pstmtSide.executeQuery();
			
			while(rsSide.next()) {
				Menu menu = new Menu(rsSide.getString("menu_no"),
						 			 rsSide.getString("menu_name"),
						 			 rsSide.getInt("price"));
				menuList.add(menu);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menuList;
	}
	
	// 메뉴 번호로 메뉴 조회하기
	public List<Menu> viewMenuThroughMenuNo(String keyword) {
		
		String sql;
		
		if(keyword.contains("S")) {
			sql = "SELECT * FROM side "
		  	    + "WHERE menu_no = ? "
			    + "ORDER BY menu_no";
		} else {
			sql = "SELECT * FROM main "
				+ "WHERE menu_no = ? "
				+ "ORDER BY menu_no";			
		}
		
		List<Menu> menuList = new ArrayList<>();
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
			pstmt.setString(1, sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Menu menu = new Menu(rs.getString("menu_no"),
									 rs.getString("menu_name"),
									 rs.getInt("price"));
				menuList.add(menu);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Collections.sort(menuList, new Sort());
		
		return menuList;
	}

}
