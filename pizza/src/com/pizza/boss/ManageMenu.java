package com.pizza.boss;

import com.pizza.common.AppService;
import com.pizza.menu.domain.Menu;
import com.pizza.menu.repository.MenuRepository;

import static com.pizza.view.AppUI.*;

import java.util.List;

public class ManageMenu implements AppService{

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
				updatePrice();
				break;
			}
			case 3: { // 메뉴 삭제하기
				break;
			}
			default:
				System.out.println("올바른 메뉴 번호를 입력해주세요.");
			}

		}

	}

	// 메뉴 추가하기
	private void add() {
		System.out.println("\n------------------*** 메뉴 추가하기 ***------------------");
		System.out.println("(수정은 가격만 진행할 수 있으니 신중하게 기입해 주세요.)");

		System.out.print("메뉴명 : ");
		String menuName = inputString();

		System.out.print("가격 : ");
		int price = inputInteger();

		String side;
		while(true) {
			System.out.println("사이드 메뉴인가요? [Y / N]");
			System.out.print(">>> ");
			side = inputString().toUpperCase();

			if(side.equals("Y") || side.equals("N") || side.equals("ㅛ") || side.equals("ㅜ")) {
				break;
			} else {
				System.out.println("\n올바른 값으로 입력해주세요.\n");
			}
		}

		Menu newMenu = new Menu();
		newMenu.setMenuName(menuName);
		newMenu.setPrice(price);

		menuRepository.addMenu(newMenu, side);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////

	// 메뉴 가격 수정하기
	private void updatePrice() {
		System.out.println("\n------------------*** 가격 수정하기 ***------------------");		

		while(true) {
			System.out.print(" 메뉴명 : ");
			String menuName = inputString();

			List<Menu> menuList = menuRepository.viewTable(menuName);

			if(menuList.size() == 0) {
				System.out.println("\n 조회된 결과가 없습니다. 다시 입력해주세요.");
				continue;

			} else if(menuList.size() != 1) {
				System.out.printf("\n-------------------- 검색 결과 총 %d건 -------------------\n", menuList.size());
				for(Menu m : menuList) {
					System.out.println(m);
				}
				System.out.println("---------------------------------------------------------");
				System.out.println(" 가격을 수정할 메뉴의 번호를 입력해주시기 바랍니다.");
				System.out.print(" >>> ");
				String selection = inputString();
				
				menuList = menuRepository.viewMenu(selection);
			}
			
			Menu menu = menuList.get(0);
			System.out.println("\n '" + menu.getMenuName() + "'의 가격을 얼마로 수정하시겠습니까?");
			System.out.println(" (기존 가격 : " + menu.getPrice() + "원)");
			System.out.print(" >>> ");
			int newPrice = inputInteger();
			
			
		}




	}

}
