package com.pizza.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppUI {
	private static Scanner sc = new Scanner(System.in);
	
	public static String inputString() {
		return sc.nextLine();
	}
	
	public static int inputInteger() {
		int num = 0;
		try {
			num = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("\n정수로 입력해 주세요.");
		} finally {
			sc.nextLine();
		}
		
		return num;
	}
	
	///////////////////////////////////////// 공통 /////////////////////////////////////////
	
	// 사장인지 고객인지 확인
	public static void checkBossOrCustomer() {
		System.out.println("안녕하세요, 피자 배달 전문점 PIZZA입니다. :)");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.println("---------------------------------------------------------");
		System.out.println(" 1. 관리");
		System.out.println(" 2. 주문");
		System.out.println("---------------------------------------------------------");
		System.out.print(">>> ");
	}
	
	///////////////////////////////////////// 사장 /////////////////////////////////////////
	
	// 사장 인증하기
	public static void checkRealBoss() {
		System.out.println("\n관리 탭에 들어가기 위해서는 별도의 인증이 필요합니다.");
		System.out.println("사장님의 코드를 입력해주세요.");
		System.out.print(">>> ");
	}
	
	// 사장 인증 후 관리 시스템 화면 출력
	public static void bossMenu() {
		System.out.println("\n-------------------*** 관리자 메뉴 ***-------------------");
		System.out.println(" 1. 메뉴 관리하기");
		System.out.println(" 2. 주문 확인하기");
		System.out.println(" 3. 매출 확인하기");
		System.out.println("---------------------------------------------------------");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.print(">>> ");
	}
	
	// 메뉴 관리하기
	public static void manageMenu() {
		System.out.println("\n------------------*** 메뉴 관리하기 ***------------------");
		System.out.println(" 1. 메뉴 추가하기");
		System.out.println(" 2. 메뉴 가격 수정하기");
		System.out.println(" 3. 메뉴 삭제하기");
		System.out.println("---------------------------------------------------------");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.print(">>> ");
	}
	
	// 매출 확인하기
	public static void checkSales() {
		System.out.println("\n------------------*** 매출 확인하기 ***------------------");
		System.out.println(" 1. 월별 매출 확인하기");
		System.out.println(" 2. 일일 매출 확인하기");
		System.out.println("---------------------------------------------------------");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.print(">>> ");
	}

	///////////////////////////////////////// 고객 /////////////////////////////////////////
	
	// 회원인지 비회원인지 확인
	public static void customerMenu() {
        System.out.println("\n--------------------*** 손님 메뉴 ***--------------------");
        System.out.println(" 1. 회원");
        System.out.println(" 2. 비회원");
        System.out.println("---------------------------------------------------------");
        System.out.println("해당되는 번호를 입력해주시기 바랍니다.");
        System.out.print(">>> ");
    }
	
	//회원일때 메뉴 출력
	public static void memberMenu() {
		System.out.println("\n--------------------*** 회원 메뉴 ***--------------------");
		System.out.println("1. 회원 정보 수정");
		System.out.println("2. 회원 탈퇴");
		System.out.println("3. 주문하기");
		System.out.println("---------------------------------------------------------");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.print(">>> ");
	}
	
	//비회원일때 메뉴 출력
	public static void nonmemMenu() {
		System.out.println("\n--------------------*** 비회원 메뉴 ***------------------");
		System.out.println("1. 회원가입");
		System.out.println("2. 주문하기");
		System.out.println("---------------------------------------------------------");
		System.out.println("진행하고 싶으신 메뉴의 번호를 입력해주시기 바랍니다.");
		System.out.print(">>> ");
	}

}

