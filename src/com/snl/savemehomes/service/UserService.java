package com.snl.savemehomes.service;

import com.snl.savemehomes.dto.UserDto;

public interface UserService {
	public boolean signUp(UserDto userDto);
	public UserDto signIn(String userId, String userPass);
	public boolean modify(UserDto userDto);
	public boolean withdrawal(UserDto userDto);
	public boolean idDuplication(String userId);
	
}
