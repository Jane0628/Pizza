package com.pizza.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.pizza.common.DataBaseConnection;
import com.pizza.order.domain.Order;

public class OrderRepository {

		private DataBaseConnection connection 
				= DataBaseConnection.getInstance();

		public void addOrder(Order order) {
			System.out.println(order);
			String sql = "INSERT INTO pizza_order "
					   + "(order_no, member_no, total_price) "
					   + "VALUES(order_seq.NEXTVAL, ?, ?)";
			
			try(Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setInt(1, order.getMemberNo());
				pstmt.setInt(2, order.getTotalPrice());
			
				if(pstmt.executeUpdate() == 1) {
					System.out.println("주문이 성공적으로 이루어졌습니다.");
				} else {
					System.out.println("주문에 실패했습니다.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

