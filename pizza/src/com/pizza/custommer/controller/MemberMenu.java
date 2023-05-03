package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputInteger;
import static com.pizza.view.AppUI.inputString;
import static com.pizza.view.AppUI.memberMenu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pizza.common.AppService;
import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;

public class MemberMenu implements AppService {


	private DataBaseConnection connection = 
			DataBaseConnection.getInstance();

	private Member loginMember = null;


	@Override
	public void start() {

		while(true) {
			memberMenu();
			int sel = inputInteger();

			switch (sel) {
			case 1:
				login();
				break;

			case 2:
				changeUser();
				break;

			case 3:
				deleteUser();
				return;

			case 4:
				//				order();
				break;

			default:
				System.out.print("정확하게 다시 입력해주세요.\n>>> ");
			}

		}	

	}


		// 주문
		private void order() {
			viewWholeMenu();
			
	
//				Order order = new Order();
//				order.setMemberNo(loginUserNum);
//	
//	
//	
//				orderRepository.addOrder(order);
//	
//			}




	//이름, 전화번호 뒷자리 4개로 로그인
	private void login() {
		System.out.print("\n회원님의 성함을 입력해주세요.\n>>> ");
		String userName = inputString();
		List<Member> memberList = findByUserName(userName);
		if(memberList.size() > 0) {
			System.out.print("\n전화번호 맨뒷자리 4개를 입력해주세요.\n>>> ");
			String fourPN = inputString();
			boolean flag = false;
			for(Member m : memberList) {
				//010-1234-5678
				String phoneNum = m.getPhoneNumber();
				phoneNum = phoneNum.substring(phoneNum.length()-4);
				if(phoneNum.equals(fourPN)) {
					//일치하는 회원 확인
					loginMember = m;
					flag = true;
					break;
				} 
			}
			if(!flag) {
				System.out.println("전화번호가 일치하는 회원이 없습니다.");
			}
		}
		System.out.println("현재 로그인 중인 회원 정보: " + loginMember);

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
	public void changeUser() {
		if(loginMember != null) {
			System.out.println("\n수정하실 정보를 선택해주세요.\n1.전화번호 2.집 주소");
			int cInfoNum=inputInteger();
			if(cInfoNum == 1) {
				System.out.println("새로운 전화번호를 입력해주세요.");
				String nPN=inputString();

				while((!((nPN.length()==13) && (nPN.charAt(3) == '-') && (nPN.charAt(8)== '-')))) {
					System.out.println("\n'-'를 포함한 13자리의 형태로 입력해주세요.");
					System.out.print("전화번호 (XXX-XXXX-XXXX) : "); 
					nPN = inputString();
				}

				String sql = "UPDATE pizza_members SET phone_no=? Where member_name=?";
				try (Connection conn = connection.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, nPN);
					pstmt.setString(2, loginMember.getUserName());

					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n전화번호가 정상적으로 수정되었습니다.");

					}
					;				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			} else if(cInfoNum == 2) {
				System.out.println("새로운 집 주소를 입력해주세요.");
				String nAddress = inputString();
				String sql = "UPDATE pizza_members SET address=? Where member_name=?";
				try (Connection conn = connection.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, nAddress);
					pstmt.setString(2, loginMember.getUserName());

					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n집 주소가 정상적으로 수정되었습니다.");
					} 

				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("로그인부터 진행해주세요.");
		}
	}


	//회원탈퇴
	public void deleteUser() {
		if(loginMember != null) {
			System.out.println("정말로 탈퇴하시겠습니까? (Y/N)");
			String answer = inputString().toUpperCase();
			switch (answer.toUpperCase()) {
			case "Y": case "ㅛ":

				String sql = "DELETE FROM pizza_members WHERE member_name=?";
				try(Connection conn = connection.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, loginMember.getUserName());

					if(pstmt.executeUpdate() == 1) {
						System.out.println("\n회원정보가 정상적으로 삭제되었습니다.");
						//비회원 화면으로 가고싶다
						return;
					} 
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;

			case "N": case "ㅜ":
				System.out.println("\n회원 메뉴로 돌아갑니다.\n");
				break;

			default:
				System.out.print("\n정확하게 다시 입력해주세요.\n>>> ");
				answer = inputString().toUpperCase();


			}} else {
				System.out.println("로그인부터 진행해주세요.");
			}
		
	}
}





//	// 검색 결과 출력
//	private int showSearchResult() {
//		List<Member> users;
//		if(!users.isEmpty()) {
//			System.out.println("\n---------------------- 회원 조회 결과 --------------------------");
//			for(Member user : users) {
//				System.out.println(user);
//			}
//		} else {
//			System.out.println("\n조회 결과가 없습니다.");
//			System.out.println("처음 화면으로 이동합니다.\n");
//		}
//
//		return users.size();
//	}
//}
