package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dto.ApartmentDto;

public interface ApartmentService {
	public void insertHomesInfo(String url) throws Exception;
	public void insertHomesInfoByDate(String url, String apikey, String yearmonth) throws Exception;
	public List<ApartmentDto> readHomesInfoListByDong(long dongCode) throws Exception;
}
