package com.pizza.user.domain;

public class User {

	private int userNumber;
	private String userName;
	private String birthDay;
	private String phoneNumber;
	private String address;
	
	public User() {}

	public User(int userNumber, String userName, String birthDay, String phoneNumber, String address) {
		super();
		this.userNumber = userNumber;
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
	
	@Override
    public String toString() {
        return  "## 회원번호: " + userNumber +
                        ", 회원명: " + userName +
                        ", 생일: " + birthDay +
                        ", 전화번호: " + phoneNumber  +
                        ", 집 주소: " + address;
                       
    }
	
}
	

	