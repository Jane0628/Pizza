package com.pizza.custommer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;

public class NonMemService {
	
	private DataBaseConnection connection = DataBaseConnection.getInstance();

	// 회원 가입
	public int addUser(Member user) {
		int a = 0;
		String sql = "INSERT INTO pizza_members "
			   	   + "(member_no, member_name, b_day, phone_no, address)"
				   + "VALUES(pizza_mem_seq.NEXTVAL,?,?,?,?)";

		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getBirthDay());
			pstmt.setString(3, user.getPhoneNumber());
			pstmt.setString(4, user.getAddress());

			a = pstmt.executeUpdate();
			
			if(a == 1) {
				System.out.println("\n 회원가입이 완료되었습니다.");
			} else {
				System.out.println("\n 회원 가입에 실패했습니다. 관리자에게 문의하세요.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return a;
	}
	
}
