package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.pizza.common.AppService;
import com.pizza.common.DataBaseConnection;
import com.pizza.custommer.domain.Member;
import com.pizza.custommer.service.MemberService;
import com.pizza.menu.repository.MenuRepository;

public class MemberMenu implements AppService {

	private Member loginMember = null;
	
	private AppService service;

	private MemberService memberService = new MemberService();

	private DataBaseConnection connection = DataBaseConnection.getInstance();


	@Override
	public void start() {
		System.out.println("\n---------------------*** 로그인 ***----------------------");
		System.out.print(" 회원님의 성함을 입력해주세요.\n >>> ");
		String userName = inputString();

		if(!login(userName)) {
			return;
		}

		while(true) {
			memberMenu();
			int sel = inputInteger();

			switch (sel) {
			case 1:
				changeUser();
				break;

			case 2:
				if(deleteUser()) {
					return;
				} else {
					break;					
				}

			case 3:
				return;

			default:
				System.out.print("\n 번호를 정확하게 다시 입력해주세요.");
			}

		}	

	}

	// 이름, 전화번호 뒷자리 4개로 로그인
	private boolean login(String userName) {
		List<Member> memberList = memberService.findByUserName(userName);

		if(memberList.size() > 0) {
			System.out.print("\n 전화번호 뒷자리를 입력해주세요.\n >>> ");
			String fourPN = inputString();

			boolean flag = false;
			for(Member m : memberList) {

				// 010-1234-5678
				String phoneNum = m.getPhoneNumber();

				// 5678
				phoneNum = phoneNum.substring(phoneNum.length()-4);

				if(phoneNum.equals(fourPN)) {
					loginMember = m;
					flag = true;
					break;
				}

			}

			if(flag) {
				System.out.println("\n 환영합니다, '" + userName + "'님!");
				return true;
			} else {
				System.out.println("\n 전화번호가 일치하는 회원이 없습니다.");
				return false;
			}

		} else {
			System.out.println("\n 존재하지 않는 회원입니다.");
			System.out.println(" 회원가입 후 이용해주세요.");
			return false;
		}


	}


	//회원번호로 정보 수정하기
	public void changeUser() {
		if(loginMember != null) {
			memberInfoUpdate();
			int cInfoNum=inputInteger();

			String nPN;
			if(cInfoNum == 1) {
				while(true) {
					System.out.println("\n 새로운 전화번호를 입력해주세요.");
					System.out.print(" >>> ");
					nPN = inputString();

					if(nPN.length() == 13 && nPN.charAt(3) == '-' && nPN.charAt(8) == '-') {
						break;
					} else {
						System.out.println("\n '-'를 포함한 13자리의 형태로 입력해주세요.");				
					}
				}
				
				memberService.changePhoneNumber(nPN, loginMember);
				
			} else if(cInfoNum == 2) {
				System.out.println("\n 새로운 집주소를 입력해주세요.");
				System.out.print(" >>> ");
				String nAddress = inputString();
				
				memberService.changeAddress(nAddress, loginMember);
			}
			
		} else {
			System.out.println("\n 로그인부터 진행해주세요.");
		}
	}


	//회원탈퇴
	public boolean deleteUser() {
		if(loginMember != null) {
			while(true) {
				System.out.println("\n 정말로 탈퇴하시겠습니까? ;-; [Y / N]");
				System.out.print(" >>> ");
				String answer = inputString().toUpperCase();
				
				switch (answer) {
				case "Y": case "ㅛ":
					memberService.deleteMember(loginMember);	
					return true;
					
				case "N": case "ㅜ":
					System.out.println("\n 회원 메뉴로 돌아갑니다.");
					return false;
					
				default:
					System.out.print("\n 정확하게 다시 입력해주세요.");					
				}
			}

		} else {
			System.out.println("\n 로그인부터 진행해주세요.");
			return false;
		}

	}
}
