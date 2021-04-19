package com.snl.savemehomes.dto;

public class FavoriteAreaDto {
	private int idx;
	private String userId;
	private long cityCode;
	
	public FavoriteAreaDto(String userId, long cityCode) {
		super();
		this.userId = userId;
		this.cityCode = cityCode;
	}

	public int getIdx() {
		return idx;
	}

	public String getUserId() {
		return userId;
	}

	public long getCityCode() {
		return cityCode;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

	
	
}
