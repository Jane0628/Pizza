package com.pizza.custommer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	

	// 회원 전화번호 변경
	public void changePhoneNumber(String nPN, Member loginMember) {
		String sql = "UPDATE pizza_members "
				   + "SET phone_no = ? "
				   + "WHERE member_name = ?";
		
		try (Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, nPN);
			pstmt.setString(2, loginMember.getUserName());

			if(pstmt.executeUpdate() == 1) {
				System.out.println("\n 전화번호가 정상적으로 수정되었습니다. :)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 회원 집주소 변경
	public void changeAddress(String nAddress, Member loginMember) {
		String sql = "UPDATE pizza_members "
				   + "SET address = ? "
				   + "WHERE member_name = ?";
		
		try (Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, nAddress);
			pstmt.setString(2, loginMember.getUserName());

			if(pstmt.executeUpdate() == 1) {
				System.out.println("\n 집주소가 정상적으로 수정되었습니다. :)");
			} 

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMember(Member loginMember) {
		String sql = "DELETE FROM pizza_members "
				   + "WHERE member_name = ?";
		
		try(Connection conn = connection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, loginMember.getUserName());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("\n 회원 정보가 정상적으로 삭제되었습니다.");
				return;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
