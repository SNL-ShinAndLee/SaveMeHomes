package com.snl.savemehomes.dao;

import com.snl.savemehomes.dto.UserDto;

public interface UserDao {
	public boolean CreateUser(UserDto userDto); // 회원가입 성공, 실패
	public UserDto ReadUser(String userId, String userPass);
	public UserDto UpdateUser(UserDto userDto);
	public boolean DeleteUser(UserDto userDto);
}
