package com.pizza.custommer.controller;

import static com.pizza.view.AppUI.inputString;

import java.util.List;

import com.pizza.common.AppService;
import com.pizza.custommer.domain.Member;
import com.pizza.custommer.service.MemberService;

public class MemberLogin implements AppService{
	
	private Member loginMember = null;
	
	private MemberService memberService = new MemberService();

	@Override
	public void start() {
		System.out.println("\n---------------------*** 로그인 ***----------------------");
		System.out.print(" 회원님의 성함을 입력해주세요.\n >>> ");
		String userName = inputString();
		login(userName);
		
		


	}

	// 이름, 전화번호 뒷자리 4개로 로그인
	private void login(String userName) {
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
			
			if(!flag) {
				System.out.println("\n 전화번호가 일치하는 회원이 없습니다.");
			} else {
				System.out.println("\n 환영합니다, '" + userName + "'님!");
			}
		}

	}

}
