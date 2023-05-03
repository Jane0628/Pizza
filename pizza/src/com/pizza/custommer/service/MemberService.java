package com.pizza.custommer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;

public class MemberService {

	private DataBaseConnection connection = DataBaseConnection.getInstance();

	//회원의 이름으로 정보 검색
	public List<Member> findByUserName(String userName) {
		List<Member> userList = new ArrayList<>();
		String sql = "SELECT * FROM pizza_members WHERE member_name=?";

		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, userName);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				Member user = new Member(rs.getInt("member_no"),
										 rs.getString("member_name"),
										 rs.getString("b_day"),
										 rs.getString("phone_no"),
										 rs.getString("address"));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return userList;
	}

}
