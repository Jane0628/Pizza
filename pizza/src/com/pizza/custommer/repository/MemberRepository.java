package com.pizza.custommer.repository;


import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;
import com.pizza.view.AppUI;

	public class MemberRepository {
		

		
		//커넥션 객체 받아오기
		private DataBaseConnection connection = 
				DataBaseConnection.getInstance();
			
		//회원 추가
		public void addUser(Member user) {
			System.out.println(user);
			String sql = "INSERT INTO pizza_members "
					+ "(member_no, member_name, b_day, phone_no, address)"
					+ "VALUES(pizza_mem_seq.NEXTVAL,?,?,?,?)";
			
			/*
			 # try with Resources
			 - 자바 1.8 이후에 사용할 수 있는 문법.
			 - try문에 자원 객체를 전달하면, try가 끝날 경우 
			   자동으로 자원을 종료해주는 기능. (finally에서 따로 close() 안해도 됨)
			 - 자원을 종료하려는 객체가 AutoCloseable 인터페이스의 구현체여야 합니다.
			 */
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, user.getUserName());
			    pstmt.setString(2, user.getBirthDay());
				pstmt.setString(3, user.getPhoneNumber());
				pstmt.setString(4, user.getAddress());
				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("회원가입이 완료되었습니다."); //회원메뉴로 가고싶다
					
				} else {
					System.out.println("회원 가입에 실패했습니다. 관리자에게 문의하세요.");
				}			
			} catch (Exception e) {
				e.printStackTrace();
			} 	
		}
		
		//회원의 이름으로 정보 검색
		public List<Member> findByUserName(String userName) {
			List<Member> userList = new ArrayList<>();
			String sql = "SELECT * FROM pizza_members WHERE member_name=?";
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userName);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Member user = new Member(
								rs.getInt("member_no"),
								rs.getString("member_name"),
								rs.getString("b_day"),
								rs.getString("phone_no"),
								rs.getString("address")
								
							);
					userList.add(user);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return userList;
		}
		
		//회원번호로 정보 수정하기
		public void changeUser(int cUserNum) {
			System.out.println("\n수정하실 정보를 선택해주세요.\n1.전화번호 2.집 주소");
			int cInfoNum=inputInteger();
			if(cInfoNum == 1) {
				System.out.println("새로운 전화번호를 입력해주세요.");
				String nPN=inputString();
			String sql = "UPDATE pizza_members SET phone_no=? Where member_no=?";
			try (Connection conn = connection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, nPN);
					pstmt.setInt(2, cUserNum);
					
					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n전화번호가 정상적으로 수정되었습니다.");
						return; //회원메뉴로 가고싶다
						
					} else {System.out.println("\n검색된 회원번호로만 정보 수정이 가능합니다.");
					}
;				}
					catch (SQLException e) {
						e.printStackTrace();
			}
		} else if(cInfoNum == 2) {
			System.out.println("새로운 집 주소를 입력해주세요.");
			String nAddress = inputString();
			String sql = "UPDATE pizza_members SET address=? Where member_no=?";
			try (Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
						pstmt.setString(1, nAddress);
						pstmt.setInt(2, cUserNum);
						
						if(pstmt.executeUpdate() == 1) {
							System.out.println("\n집 주소가 정상적으로 수정되었습니다.");
						} else {System.out.println("\n검색된 회원번호로만 정보 수정이 가능합니다.");
						}
						System.out.println("처음 메뉴로 돌아갑니다.\n");///????회원메뉴로 돌아가고 싶다
						return;
			}
						catch (SQLException e) {
							e.printStackTrace();
				}
		}
		}
		
		
		//회원탈퇴
		public void deleteUser(int delUserNum) {
			String sql = "DELETE FROM pizza_members WHERE member_no=?";
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
							pstmt.setInt(1,  delUserNum);
					
					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n회원정보가 정상적으로 삭제되었습니다.");
						System.out.println("처음으로 돌아갑니다.\n");
		} else {
			System.out.println("\n검색된 회원번호로만 삭제가 가능합니다.");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		

		}}
