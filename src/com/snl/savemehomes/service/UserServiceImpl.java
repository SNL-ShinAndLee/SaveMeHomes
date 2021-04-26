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
		return UserDaoImpl.getInstance().readUserById(userId);
	}
	
	@Override
	public boolean signUp(UserDto userDto) {
		return UserDaoImpl.getInstance().createUser(userDto);
	}

	@Override
	public UserDto signIn(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getInstance().readUser(userId, userPass);
		return userDto;
	}

	@Override
	public boolean isCorrectPass(String userId, String userPass) {
		UserDto userDto = UserDaoImpl.getInstance().readUser(userId, userPass);
		
		if(userDto!=null && userPass.equals(userDto.getUserPass())) return true;
		return false;
	}
	
	@Override
	public boolean modify(UserDto userDto) {
		return UserDaoImpl.getInstance().updateUser(userDto);
	}

	@Override
	public boolean withdraw(String userId, String userPass) {
		return UserDaoImpl.getInstance().deleteUser(userId, userPass);
	}



}
