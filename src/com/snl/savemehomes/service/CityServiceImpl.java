package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dao.CityDao;
import com.snl.savemehomes.dao.CityDaoImpl;
import com.snl.savemehomes.dto.CityDto;

public class CityServiceImpl implements CityService {

	private final CityDao cityDao = CityDaoImpl.getInstance();
	
	private static CityService cityService;
	private CityServiceImpl() {}
	public static CityService getInstance() {
		if(cityService == null)
			cityService = new CityServiceImpl();
		return cityService;
	}
	
	@Override
	public List<CityDto> getSidoList() {
		return cityDao.readSido();
	}

	@Override
	public List<CityDto> getGugunList(long citycode) {
		return cityDao.readGugun(citycode);
	}

	@Override
	public List<CityDto> getDongList(long citycode) {
		return cityDao.readDong(citycode);
	}
	@Override
	public CityDto getCity(long citycode) {
		return cityDao.readCityByCityCode(citycode);
	}

}
