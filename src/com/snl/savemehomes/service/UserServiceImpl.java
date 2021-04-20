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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(UserDto userDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawal(UserDto userDto) {
		// TODO 회원탈퇴
		return false;
	}


}
