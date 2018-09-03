package com.test.service;

import com.test.entity.User;

public interface UserService {

	boolean checkLogin(String username, String password);

	User findUserByUserName(String username);

}
