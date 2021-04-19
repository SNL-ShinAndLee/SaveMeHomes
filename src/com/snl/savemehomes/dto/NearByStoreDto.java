package com.snl.savemehomes.dto;

public class NearByStoreDto {
	private int idx;
	private String storeName;
	private String classCodeC;
	private String classNameC;
	private String roadAddress;
	private double lat;
	private double lng;
	
	public NearByStoreDto(String storeName, String classCodeC, String classNameC, String roadAddress, double lat,
			double lng) {
		super();
		this.storeName = storeName;
		this.classCodeC = classCodeC;
		this.classNameC = classNameC;
		this.roadAddress = roadAddress;
		this.lat = lat;
		this.lng = lng;
	}

	public int getIdx() {
		return idx;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getClassCodeC() {
		return classCodeC;
	}

	public String getClassNameC() {
		return classNameC;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setClassCodeC(String classCodeC) {
		this.classCodeC = classCodeC;
	}

	public void setClassNameC(String classNameC) {
		this.classNameC = classNameC;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
