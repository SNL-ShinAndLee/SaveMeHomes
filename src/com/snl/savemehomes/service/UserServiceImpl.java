package com.snl.savemehomes.service;

import com.snl.savemehomes.dao.UserDaoImpl;
import com.snl.savemehomes.dto.UserDto;

public class UserServiceImpl implements UserService {

	static UserService userService;
	
	private UserServiceImpl() {}

	public static UserService getUserService() {
		if(userService == null)
			userService = new UserServiceImpl();
		return userService;
	}
	
	@Override
	public boolean idDuplication(String userId) {
		return UserDaoImpl.getUserDao().ReadUserById(userId);
	}
	
	@Override
	public boolean signUp(UserDto userDto) {
		return UserDaoImpl.getUserDao().CreateUser(userDto);
	}

	@Override
	public UserDto signIn(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getUserDao().ReadUser(userId, userPass);
		return userDto;
	}

	@Override
	public boolean isCorrectPass(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getUserDao().ReadUser(userId, userPass);
		
		if(userDto!=null && userPass.equals(userDto.getUserPass())) return true;
		return false;
	}
	
	@Override
	public boolean modify(UserDto userDto) {
		return UserDaoImpl.getUserDao().UpdateUser(userDto);
	}

	@Override
	public boolean withdraw(String userId, String userPass) {
		return UserDaoImpl.getUserDao().DeleteUser(userId, userPass);
	}



}
