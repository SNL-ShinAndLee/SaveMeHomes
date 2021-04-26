package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dto.CityDto;

public interface CityService {
	public List<CityDto> getSidoList();
	public List<CityDto> getGugunList(long citycode);
	public List<CityDto> getDongList(long citycode);
}
