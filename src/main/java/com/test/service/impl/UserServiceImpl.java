package com.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.UserMapper;
import com.test.entity.User;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean checkLogin(String username, String password) {
		int count = userMapper.selectUserCount(username,password);
		return count > 0;
	}

	@Override
	public User findUserByUserName(String username) {
		User u = userMapper.selectUserByUserName(username);
		System.out.println("USER ==================== "+u);
		return u;
	}

}
