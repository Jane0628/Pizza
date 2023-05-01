package com.pizza.user.repository;


	import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
	import java.util.List;

	import com.pizza.common.DataBaseConnection;
	import com.pizza.user.domain.User;

	public class UserRepository {
		
		//커넥션 객체 받아오기
		private DataBaseConnection connection = 
				DataBaseConnection.getInstance();
			
		//회원 추가
		public void addUser(User user) {
			System.out.println("repository: " + user);
			String sql = "INSERT INTO pizza_members "
					+ "(member_no, member_name, b_day, address, phone_no)"
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
				
//				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
//				LocalDateTime ldt = LocalDateTime.parse(user.getBirthDay(), dtf);
				
				SimpleDateFormat sdf = new SimpleDateFormat("MMdd");	
				
				pstmt.setString(1, user.getUserName());
			    pstmt.setDate(2, new java.sql.Date(sdf.parse(user.getBirthDay()).getTime()));
				pstmt.setString(3, user.getPhoneNumber());
				pstmt.setString(4, user.getAddress());
				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("회원가입이 정상 처리되었습니다.");
				} else {
					System.out.println("회원 가입에 실패했습니다. 관리자에게 문의하세요.");
				}			
			} catch (Exception e) {
				e.printStackTrace();
			} 	
		}
		
		//회원의 이름으로 정보 검색
		public List<User> findByUserName(String userName) {
			List<User> userList = new ArrayList<>();
			String sql = "SELECT * FROM users WHERE user_name=?";
			
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, userName);
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					User user = new User(
								rs.getInt("member_no"),
								rs.getString("member_name"),
								rs.getString("b_day"),
								rs.getString("address"),
								rs.getString("phone_no")
								
							);
					userList.add(user);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
			return userList;
		}

		public void deleteUser(int delUserNum) {
			String sql = "DELETE FROM users WHERE user_number=?";
			try(Connection conn = connection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
							pstmt.setInt(1,  delUserNum);
					
					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n### 회원정보가 정상 삭제되었습니다.");
		} else {
			System.out.println("\n### 검색한 회원의 회원번호로만 삭제가 가능합니다.");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		

		}}
