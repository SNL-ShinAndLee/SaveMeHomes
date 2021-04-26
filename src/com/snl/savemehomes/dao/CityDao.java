package com.snl.savemehomes.dao;

import java.util.List;

import com.snl.savemehomes.dto.CityDto;

public interface CityDao {
	public void createCityList(String cityName);
	public List<CityDto> readSido();
	public List<CityDto> readGugun(long sidoCode);
	public List<CityDto> readDong(long gugunCode);
}
