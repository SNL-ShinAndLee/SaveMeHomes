package com.snl.savemehomes.dto;

import com.snl.savemehomes.common.UserRole;

public class UserDto {
	private int idx;
	private String userId;
	private String userPass;
	private String userName;
	private UserRole userRole;
	private String userEmail;
	private String userAddress;
	
	public UserDto() {}
	
	public UserDto(String userId, String userPass, String userName, UserRole userRole, String userEmail,
			String userAddress) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
	}
	
	public UserDto(String userId, String userPass, String userName, String userEmail, String userAddress) {
		super();
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
	}

	public UserDto(int idx, String userId, String userPass, String userName, UserRole userRole, String userEmail,
			String userAddress) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userRole = userRole;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
	}

	public int getIdx() {
		return idx;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public String getUserName() {
		return userName;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		return "UserDto [idx=" + idx + ", userId=" + userId + ", userPass=" + userPass + ", userName=" + userName
				+ ", userRole=" + userRole + ", userEmail=" + userEmail + ", userAddress=" + userAddress + "]";
	}
	
	
}
