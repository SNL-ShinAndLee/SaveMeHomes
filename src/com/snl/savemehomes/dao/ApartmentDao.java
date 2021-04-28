package com.snl.savemehomes.dao;

import java.util.List;

import com.snl.savemehomes.dto.ApartmentDto;

public interface ApartmentDao {
	public boolean createApartmentList(List<ApartmentDto> apartmentDtoList);
	public List<ApartmentDto> readApartmentList(long gugunCode, String dong);
}
