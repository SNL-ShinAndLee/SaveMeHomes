package com.snl.savemehomes.dto;

public class ApartmentDto {
	private int idx;
	private String apartName;
	private int dealAmount;
	private int buildYear;
	private double landArea;
	private String dong;
	private String dealDate;
	private double dedicatedArea;
	private String lotNum;
	private long cityCode;
	private int floor;
	
	public ApartmentDto() {
		super();
	}

	public ApartmentDto(String apartName, int dealAmount, int buildYear, double landArea, String dong, String dealDate,
			double dedicatedArea, String lotNum, long cityCode, int floor) {
		super();
		this.apartName = apartName;
		this.dealAmount = dealAmount;
		this.buildYear = buildYear;
		this.landArea = landArea;
		this.dong = dong;
		this.dealDate = dealDate;
		this.dedicatedArea = dedicatedArea;
		this.lotNum = lotNum;
		this.cityCode = cityCode;
		this.floor = floor;
	}

	public int getIdx() {
		return idx;
	}

	public String getApartName() {
		return apartName;
	}

	public int getDealAmount() {
		return dealAmount;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public double getLandArea() {
		return landArea;
	}

	public String getDong() {
		return dong;
	}

	public String getDealDate() {
		return dealDate;
	}

	public double getDedicatedArea() {
		return dedicatedArea;
	}

	public String getLotNum() {
		return lotNum;
	}

	public long getCityCode() {
		return cityCode;
	}

	public int getFloor() {
		return floor;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public void setLandArea(double landArea) {
		this.landArea = landArea;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public void setDedicatedArea(double dedicatedArea) {
		this.dedicatedArea = dedicatedArea;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
	
	
}
