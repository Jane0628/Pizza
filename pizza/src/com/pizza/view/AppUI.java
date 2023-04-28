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
			System.out.println("정수로 입력해 주세요.");
		} finally {
			sc.nextLine();
		}
		
		return num;
	}
	
	//사장님인지 고객인지
		public static void checkBossOrCustomer() {
			System.out.println("\n---------- 진행하실 메뉴의 번호를 입력해주세요 ----------");
			System.out.println("1. 관리");
			System.out.println("2. 주문");
			System.out.println("---------------------------------------------------------");
			System.out.print(">>> ");
		}
		
		//회원 관리 시스템 화면 출력
		public static void bossMenu() {
			System.out.println("\n========== 관리자 메뉴 ==========");
			System.out.println("1.");
			System.out.println("2.");
			System.out.println("3.");
			System.out.println("4.");
			System.out.println("---------------------------------");
			System.out.print(">>> ");
		}
		
		//대여 주문 관리 시스템 화면 출력
		public static void customerMenu() {
	        System.out.println("\n========== 손님 메뉴 ==========");
	        System.out.println("1.");
	        System.out.println("2.");
	        System.out.println("3.");
	        System.out.println("----------------------------------------");
	        System.out.print(">>> ");
	    }
		

	}

