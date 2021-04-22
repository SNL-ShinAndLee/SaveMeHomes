package com.snl.savemehomes.service;

import com.snl.savemehomes.dto.UserDto;

public interface UserService {
	public boolean idDuplication(String userId);
	public boolean signUp(UserDto userDto);
	public UserDto signIn(String userId, String userPass);
	public boolean isCorrectPass(String userId, String userPass);
	public boolean modify(UserDto userDto);
	public boolean withdraw(String userId, String userPass);
}
