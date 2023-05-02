package com.pizza.custommer.domain;
public class Member {

	private int userNumber;
	private String userName;
	private String birthDay;
	private String phoneNumber;
	private String address;

	public Member() {}

	public Member(int member_no, String userName, String birthDay, String phoneNumber, String address) {
		super();
		this.userNumber = member_no;
		this.userName = userName;
		this.birthDay = birthDay;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public int getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String secretPhone(String phoneNumber) {
		String answer = "";
		String[] num = phoneNumber.split("");

		int len = num.length;

		for(int i = 0; i < len; i++) {
			if(i < len - 4) 
				answer += "*";
			else
				answer += num[i];
		}

		return answer;

	}


	@Override
	public String toString() {
		return  "회원번호: " + userNumber +  //추가할때 회원번호 왜 0으로 뜨는지 모르겠다
				", 회원명: " + userName +
				", 생일: " + birthDay +
				", 전화번호: " + secretPhone(phoneNumber) +
				", 주소: " + address.replaceAll("(?<=.{9}).", "*");            
	}

}


