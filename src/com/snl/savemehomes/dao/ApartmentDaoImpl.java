package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.snl.savemehomes.dto.ApartmentDto;
import com.snl.savemehomes.dto.CityDto;
import com.snl.savemehomes.util.DBUtil;

public class ApartmentDaoImpl implements ApartmentDao {
	
	static ApartmentDao apartmentDao;
	
	private ApartmentDaoImpl() {}

	public static ApartmentDao getInstance() {
		if(apartmentDao == null)
			apartmentDao = new ApartmentDaoImpl();
		return apartmentDao;
	}

	@Override
	public boolean createApartmentList(List<ApartmentDto> apartmentDtoList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean ret = false;
	    StringBuilder querySB = new StringBuilder();
	    String query = "INSERT INTO Apartment (apartName, dealAmount, buildYear, landArea, dong, dealDate, "
	    		+ "dedicatedArea, lotNum, cityCode, floor) VALUES ";
	    querySB.append(query);
	    for(ApartmentDto apDto : apartmentDtoList) {
	    	querySB.append(String.format("(%s, %d, %d, %f, %s, %s, %f, %s, %d, %d),",
	    			apDto.getApartName(), apDto.getDealAmount(), apDto.getBuildYear(), apDto.getLandArea(),
	    			apDto.getDong(), apDto.getDealDate(), apDto.getDedicatedArea(), apDto.getLotNum(),
	    			apDto.getCityCode(), apDto.getFloor()));
	    }
	    querySB.deleteCharAt(querySB.lastIndexOf(","));
		querySB.append(";");
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			ret = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    return ret;
	}

	@Override
	public List<ApartmentDto> readApartmentList(long gugunCode, String dong) {
		List<ApartmentDto> apartmentList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		String query = "SELECT * FROM City WHERE cityCode=" + gugunCode + "AND dong=" + dong;
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ApartmentDto apartmentDto = new ApartmentDto();
				apartmentDto.setApartName(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	
		
		return apartmentList;
	}

}
