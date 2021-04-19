package com.snl.savemehomes.dto;

public class CityDto {
	private long cityCode;
	private String citySido;
	private String cityGugun;
	private String cityDong;
	private String cityLi;
	
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
	
	
}
