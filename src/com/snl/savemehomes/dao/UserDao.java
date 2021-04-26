package com.snl.savemehomes.dao;

import com.snl.savemehomes.common.UserRole;
import com.snl.savemehomes.dto.UserDto;

public interface UserDao {
	public boolean createUser(UserDto userDto); // 회원가입 성공, 실패
	public UserDto readUser(String userId, String userPass);
	public boolean updateUser(UserDto userDto);
	public boolean deleteUser(String userId, String userPass);
	public boolean readUserById(String userId);
	public UserRole readUserRoleById(String userId);
}
