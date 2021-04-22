package com.snl.savemehomes.service;

import com.snl.savemehomes.dao.UserDaoImpl;
import com.snl.savemehomes.dto.UserDto;

public class UserServiceImpl implements UserService {

	static UserService userService;
	
	private UserServiceImpl() {}

	public static UserService getInstance() {
		if(userService == null)
			userService = new UserServiceImpl();
		return userService;
	}
	
	@Override
	public boolean idDuplication(String userId) {
		return UserDaoImpl.getInstance().ReadUserById(userId);
	}
	
	@Override
	public boolean signUp(UserDto userDto) {
		return UserDaoImpl.getInstance().CreateUser(userDto);
	}

	@Override
	public UserDto signIn(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getInstance().ReadUser(userId, userPass);
		return userDto;
	}

	@Override
	public boolean isCorrectPass(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getInstance().ReadUser(userId, userPass);
		
		if(userDto!=null && userPass.equals(userDto.getUserPass())) return true;
		return false;
	}
	
	@Override
	public boolean modify(UserDto userDto) {
		return UserDaoImpl.getInstance().UpdateUser(userDto);
	}

	@Override
	public boolean withdraw(String userId, String userPass) {
		return UserDaoImpl.getInstance().DeleteUser(userId, userPass);
	}



}
