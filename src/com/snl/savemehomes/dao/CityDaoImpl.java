package com.snl.savemehomes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.snl.savemehomes.dto.CityDto;
import com.snl.savemehomes.util.CSVUtil;
import com.snl.savemehomes.util.DBUtil;

public class CityDaoImpl implements CityDao {

	static CityDao cityDao;

	private CityDaoImpl() {
	}

	public static CityDao getInstance() {
		if (cityDao == null)
			cityDao = new CityDaoImpl();
		return cityDao;
	}
	public static void main(String[] args) {
		CityDaoImpl.getInstance().createCityList("busan");
	}
	@Override
	public void createCityList(String cityName) {
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath + "/data/dong_"+cityName+".csv";
		List<String[]> cityList = CSVUtil.parse(filePath);
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO City (cityCode, citySido, cityGugun, cityDong, cityLi) VALUES");
		
		for(String[] city : cityList) {
			CityDto cityDto = new CityDto();
			long citycode = Long.parseLong(city[0]);
			String[] citynames = city[1].split(" ");
			
			cityDto.setCityCode(citycode);
			cityDto.setCitySido(citynames[0]);
			if (citynames.length >= 2)
				cityDto.setCityGugun(citynames[1]);
			if (citynames.length >= 3)
				cityDto.setCityDong(citynames[2]);
			if (citynames.length >= 4)
				cityDto.setCityLi(citynames[3]);

			sb.append(String.format("(%d, '%s', '%s', '%s', '%s'),", cityDto.getCityCode(), cityDto.getCitySido(),
					cityDto.getCityGugun(), cityDto.getCityDong(), cityDto.getCityLi()));
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append(";");
		Connection conn = null;
		PreparedStatement pstmt = null;
	    String query = sb.toString();
	    
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	}

	@Override
	public List<CityDto> readSido() {
		List<CityDto> sidoList = new ArrayList<>();
		// 11 00000000
		Connection conn = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		String query = "SELECT cityCode, citySido FROM City WHERE MOD(cityCode, 100000000) = 0";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CityDto cityDto = new CityDto();
				cityDto.setCityCode(rs.getLong(1));
				cityDto.setCitySido(rs.getString(2));
				sidoList.add(cityDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    return sidoList;
	}
	
	@Override
	public List<CityDto> readGugun(long sidoCode) {
		List<CityDto> gugunList = new ArrayList<>();
		// 11 00000000 / 1000000 = 11
		Connection conn = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		String query = "SELECT cityCode, citySido, cityGugun FROM City "
						+ "WHERE FLOOR(cityCode/ 100000000) = ?/100000000 "
						+ "AND MOD(cityCode, 10000) = 0 "
						+ "AND MOD(cityCode, 100000000) != 0;";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, sidoCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CityDto cityDto = new CityDto();
				cityDto.setCityCode(rs.getLong(1));
				cityDto.setCitySido(rs.getString(2));
				cityDto.setCityGugun(rs.getString(3));
				gugunList.add(cityDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    return gugunList;
	
	}

	@Override
	public List<CityDto> readDong(long gugunCode) {
		List<CityDto> dongList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
	    ResultSet rs = null;
		String query = "SELECT * FROM City "
					+ "WHERE FLOOR(cityCode/ 100000) = ?/100000 "
					+ "AND cityDong != 'null';";
	    try {
	    	conn = DBUtil.getConnect();
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, gugunCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CityDto cityDto = new CityDto();
				cityDto.setCityCode(rs.getLong(1));
				cityDto.setCitySido(rs.getString(2));
				cityDto.setCityGugun(rs.getString(3));
				cityDto.setCityDong(rs.getString(4));
				dongList.add(cityDto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt, conn);
		}
	    return dongList;
		
	}

}
