package com.test.dao;

import org.apache.ibatis.annotations.Param;

import com.test.entity.User;

public interface UserMapper {

	int selectUserCount(@Param("username") String username,@Param("password") String password);

	User selectUserByUserName(@Param("username") String username);

}
