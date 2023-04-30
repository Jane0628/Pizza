package com.pizza.user.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pizza.user.domain.User;

public class UserRepositoryImpl implements UserRepository{
	private static final Map<Integer, User> userDatabase = new HashMap<>();


    @Override
    public void addUser(User user) {
        userDatabase.put(user.getUserNumber(), user);
    }

    @Override
    public List<User> findByUserName(String userName) {
        List<User> findUserList = new ArrayList<>();
        for (int key : userDatabase.keySet()) {
            User user = userDatabase.get(key);
            if (user.getUserName().equals(userName)) {
                findUserList.add(user);
            }
        }
        return findUserList;
    }

    @Override
    public User findByUserNumber(int userNumber) {
        return userDatabase.get(userNumber);
    }

    @Override
    public User deleteUser(int userNumber) {
        return userDatabase.remove(userNumber);
    }

}

