package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dto.ApartmentDto;

public interface ApartmentService {
	public List<ApartmentDto> getHomesSalesInfo(String url, String apikey, long gugunCode, String dong) throws Exception; 
}
