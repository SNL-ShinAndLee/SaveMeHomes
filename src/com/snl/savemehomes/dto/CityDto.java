package com.snl.savemehomes.dto;

public class CityDto {
	private long cityCode;//전체코드
	private String citySido;//시도 명 -서울시
	private String cityGugun;//구군 명 -종로구
	private String cityDong;//동 명 -?????
	private String cityLi;//리 명
	
	public CityDto() {}
	public CityDto(long cityCode, String citySido, String cityGugun, String cityDong, String cityLi) {
		super();
		this.cityCode = cityCode;
		this.citySido = citySido;
		this.cityGugun = cityGugun;
		this.cityDong = cityDong;
		this.cityLi = cityLi;
	}

	public long getCityCode() {
		return cityCode;
	}

	public String getCitySido() {
		return citySido;
	}

	public String getCityGugun() {
		return cityGugun;
	}

	public String getCityDong() {
		return cityDong;
	}

	public String getCityLi() {
		return cityLi;
	}

	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}

	public void setCitySido(String citySido) {
		this.citySido = citySido;
	}

	public void setCityGugun(String cityGugun) {
		this.cityGugun = cityGugun;
	}

	public void setCityDong(String cityDong) {
		this.cityDong = cityDong;
	}

	public void setCityLi(String cityLi) {
		this.cityLi = cityLi;
	}
	
	public String toJSONString() {
		return String.format("{\"cityCode\" : %d, \"citySido\" : \"%s\", \"cityGugun\" : \"%s\", "
							+ "\"cityDong\" : \"%s\", \"cityLi\" : \"%s\"}"
								, cityCode, citySido, cityGugun, cityDong, cityLi);
	}
}