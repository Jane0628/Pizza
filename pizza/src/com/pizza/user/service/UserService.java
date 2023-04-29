package com.pizza.user.service;

import com.pizza.user.repository.UserRepository;
import com.pizza.user.repository.UserRepositoryImpl;

import static com.pizza.view.AppUI.*;

import com.pizza.common.AppService;

public class UserService implements AppService {
	private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void start() {
        while (true) {
            userManagementScreen();
            int selection = inputInteger();

            switch (selection) {
                case 1:
                    join();
                    break;
                case 2:
                    showSearchResult();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("메뉴를 다시 입력하세요.");
            }
            System.out.println("\n====== 계속 진행하려면 ENTER를 누르세요 ======");
            inputString();
        }
    }

}
