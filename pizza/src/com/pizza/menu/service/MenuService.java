package com.pizza.menu.service;

import com.pizza.common.AppService;
import com.pizza.menu.domain.Menu;
import com.pizza.menu.repository.MenuRepository;

import static com.pizza.view.AppUI.*;

import java.util.List;

public class MenuService implements AppService{

	private final MenuRepository menuRepository = new MenuRepository();

	@Override
	public void start() {
		while(true) {
			manageMenu();
			int selection = inputInteger();

			switch (selection) {
			case 1: { // 메뉴 추가하기
				add();
				break;
			}
			case 2: { // 메뉴 가격 수정하기
				System.out.println("\n------------------*** 가격 수정하기 ***------------------");
				view(selection);
				break;
			}
			case 3: { // 메뉴 삭제하기
				System.out.println("\n------------------*** 메뉴 삭제하기 ***------------------");
				view(selection);
				break;
			}

			case 4: { // 전메뉴 조회하기
				viewMenu();
				break;
			}

			case 5: { // 메인 메뉴로 돌아가기
				return;
			}

			default:
				System.out.println("올바른 메뉴 번호를 입력해주세요.");
			}

		}

	}

	// 1. 메뉴 추가하기
	private void add() {
		System.out.println("\n------------------*** 메뉴 추가하기 ***------------------");
		System.out.println(" (수정은 가격만 진행할 수 있으니 신중하게 기입해 주세요.)");

		System.out.print(" 메뉴명 : ");
		String menuName = inputString();

		System.out.print(" 가격 : ");
		int price = inputInteger();

		String side;
		while(true) {
			System.out.println(" 사이드 메뉴인가요? [Y / N]");
			System.out.print(" >>> ");
			side = inputString().toUpperCase();

			if(side.equals("Y") || side.equals("N") || side.equals("ㅛ") || side.equals("ㅜ")) {
				break;
			} else {
				System.out.println("\n 올바른 값으로 입력해주세요.\n");
			}
		}

		Menu newMenu = new Menu();
		newMenu.setMenuName(menuName);
		newMenu.setPrice(price);

		menuRepository.addMenu(newMenu, side);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	// 조회하기
	private void view(int number) {		

		while(true) {
			System.out.print(" 메뉴명 : ");
			String menuName = inputString();

			List<Menu> menuList = menuRepository.viewMenuThroughMenuName(menuName);

			if(menuList.size() == 0) {
				System.out.println("\n 조회된 결과가 없습니다. 다시 입력해주세요.");
				continue;

			} else if(menuList.size() != 1) {

				outer : while(true) {

					System.out.printf("\n-------------------- 검색 결과 총 %d건 -------------------\n", menuList.size());
					for(Menu m : menuList) {
						System.out.println(m);
					}
					System.out.println("---------------------------------------------------------");

					if(number == 2) {
						System.out.println(" 가격을 수정할 메뉴의 번호를 입력해주시기 바랍니다.");						
					} else {
						System.out.println(" 삭제할 메뉴의 번호를 입력해주시기 바랍니다.");
					}

					System.out.print(" >>> ");
					String selection = inputString().toUpperCase();

					boolean flag = false;
					for(Menu m : menuList) {
						if(m.getMenuNo().equals(selection)) {
							menuList = menuRepository.viewMenuThroughMenuNo(selection);
							flag = true;
							break outer;
						}						
					}	

					if(!flag) {
						System.out.println("\n 올바른 메뉴 번호를 입력해주세요.");						
					}

				}

			}

			if(number == 2) {
				if(modify(menuList.get(0))) {
					break;
				}
			} else {
				if(delete(menuList.get(0))) {
					break;
				}
			}


		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	// 메뉴 수정하기
	private boolean modify(Menu menu) {
		System.out.println("\n '" + menu.getMenuName() + "'의 가격을 얼마로 수정하시겠습니까?");
		System.out.println(" (기존 가격 : " + menu.getPrice() + "원)");
		System.out.print(" >>> ");
		int newPrice = inputInteger();

		if(menuRepository.updatePrice(menu, newPrice) == 1) {
			System.out.println("\n 가격 수정을 완료했습니다. :)");
			return true;
		} else {
			System.out.println("\n 가격 수정을 실패했습니다. :(");
			return false;
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	// 메뉴 삭제하기
	private boolean delete(Menu menu) {

		if(menuRepository.deleteMenu(menu) == 1) {
			System.out.println("\n '" + menu.getMenuName() + "'를 삭제했습니다. :)");
			return true;
		} else {
			System.out.println("\n 삭제를 실패했습니다. :(");
			return false;
		}

	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	
	// 전메뉴 조회하기
	private void viewMenu() {
		List<Menu> menuList = menuRepository.viewWholeMenu();
		
		System.out.printf("\n------------------------ 총 %d건 ------------------------\n", menuList.size());
		for(Menu m : menuList) {
			System.out.println(m);
		}
		System.out.println("---------------------------------------------------------");
	}
	
	
}