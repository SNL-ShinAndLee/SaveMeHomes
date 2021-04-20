package com.snl.savemehomes.service;

import com.snl.savemehomes.dto.UserDto;

public class UserSerbiveImpl implements UserService {

	@Override
	public boolean signUp(UserDto userDto) {
		
		
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean idDuplication(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
