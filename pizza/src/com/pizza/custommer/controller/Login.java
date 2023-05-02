package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.AppService;
import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;

import static com.pizza.view.AppUI.inputInteger;

import com.pizza.order.domain.Order;

public class Login implements AppService {
	
	private DataBaseConnection connection 
	= DataBaseConnection.getInstance();

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
	
	
	public void login() {
			System.out.print("회원님의 성함을 입력해주세요.\n>>> ");
			String userName = inputString();
			findByUserName(userName);
			if(userList.isEmpty) {
				System.out.print("전화번호 뒷자리를 입력해주세요.\n>>> ");
				String phoneNumber4 = inputString();
				if(전화번호 뒷자리 맞으면) {
					System.out.println(memberName + "회원님 반갑습니다!");
				} else {
					System.out.println("비밀번호가 틀립니다.\n다시 입력해주세요.");
			} else {System.out.println("회원이 아닙니다.")
			}
			}}
			
			
			
			
			// 회원 이름으로 검색 
			private List<Member> searchUser() {
				System.out.print("\n회원명 : ");
				String name = inputString();
				return userRepository.findByUserName(name);
			}

			// 회원 정보 수정
			private void changeUser() {
				if(showSearchResult() > 0) {
					System.out.print("회원님의 번호를 입력해주세요.\n>>> ");
					int cUserNum = inputInteger();
					userRepository.changeUser(cUserNum);

				}

			}


			// 회원 탈퇴
			private void deleteUser() {
				if(showSearchResult() > 0) {
					System.out.print("\n회원님의 번호를 입력해주세요.\n>>> ");
					int delUserNum = inputInteger();
					userRepository.deleteUser(delUserNum);
				}
			}

			// 검색 결과 출력
			private int showSearchResult() {
				List<Member> users = searchUser();

				return users.size();
			}


			@Override
			public void start() {
				// TODO Auto-generated method stub
				
			}
		}
			
