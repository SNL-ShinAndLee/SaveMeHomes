package com.snl.savemehomes.service;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.snl.savemehomes.dao.ApartmentDao;
import com.snl.savemehomes.dao.ApartmentDaoImpl;
import com.snl.savemehomes.dao.CityDao;
import com.snl.savemehomes.dao.CityDaoImpl;
import com.snl.savemehomes.dto.ApartmentDto;
import com.snl.savemehomes.dto.CityDto;
import com.snl.savemehomes.util.XMLUtil;

public class ApartmentServiceImpl implements ApartmentService {

	static ApartmentService ApartmentService;
	private final ApartmentDao apartmentDao = ApartmentDaoImpl.getInstance();
	private final CityDao cityDao = CityDaoImpl.getInstance();

	private ApartmentServiceImpl() {
	}

	public static ApartmentService getInstance() {
		if (ApartmentService == null)
			ApartmentService = new ApartmentServiceImpl();
		return ApartmentService;
	}

	@Override
	public void insertHomesInfo(String url) throws Exception {
		List<ApartmentDto> apartmentList = new ArrayList<>();
		List<Map<String, String>> XMLlist = XMLUtil.parse(url);
		
		for(Map<String, String> m : XMLlist) {
			ApartmentDto apartmentDto = new ApartmentDto();
			String date = new String();
			apartmentDto.setApartName(m.get("연립다세대"));
			apartmentDto.setBuildYear(Integer.parseInt(m.get("건축년도")));
			apartmentDto.setCityCode(Long.parseLong(m.get("지역코드")) * 100000);
			apartmentDto.setDealAmount(Integer.parseInt(m.get("거래금액").replaceAll(",", "")));
			date = m.get("년") + "-" + m.get("월") + "-" + m.get("일");
			apartmentDto.setDealDate(date);
			apartmentDto.setDedicatedArea(Double.parseDouble(m.get("전용면적")));
			apartmentDto.setDong(m.get("법정동"));
			apartmentDto.setFloor(Integer.parseInt(m.get("층")));
			apartmentDto.setLandArea(Double.parseDouble(m.get("대지권면적")));
			apartmentDto.setLotNum(m.get("지번"));
			apartmentList.add(apartmentDto);
		}
		apartmentDao.createApartmentList(apartmentList);
	}

	@Override
	public void insertHomesInfoByDate(String url, String apikey, String yearmonth) throws Exception {
		// TODO Auto-generated method stub
		List<CityDto> sidoList = cityDao.readSido();
		
		for(CityDto sido : sidoList) {
			List<CityDto> gugunList = cityDao.readGugun(sido.getCityCode());
			for(CityDto gugun : gugunList) {
				long guguncode = gugun.getCityCode() / 100000;
				
				insertHomesInfo(getRequestURL(url, apikey, guguncode, yearmonth));
			}
		}
	}

	private String getRequestURL(String apiUrl, String apiKey, long gugunCode, String yearMonth) throws Exception {
		StringBuilder urlBuilder = new StringBuilder(apiUrl); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + apiKey); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "="
				+ URLEncoder.encode(Long.toString(gugunCode), "UTF-8")); /* 각 지역별 코드 */
		urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "="
				+ URLEncoder.encode(yearMonth, "UTF-8")); /* 월 단위 신고자료 */

		return urlBuilder.toString();
	}

	@Override
	public List<ApartmentDto> readHomesInfoListByDong(long dongCode) throws Exception {
		long gugunCode = (dongCode / 100000) * 100000;
		String dong = cityDao.readDongNameByCityCode(dongCode);
		return apartmentDao.readApartmentList(gugunCode, dong);
	}
	
	public static void main(String[] args) throws Exception {
		ApartmentServiceImpl.getInstance().insertHomesInfoByDate(
			"http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade",
			"d7%2BZqfSRFyM3daSsQL%2FNjKO0wpXE9o4WXMg9Ym7UgmyzLE%2FiowPDQ6oWpepTGXK%2BlS%2F2%2FqpT60HxAqm5oRmmEQ%3D%3D",
				"202104");
		
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH) + 1;
		/*String yearmonth[] = new String[3];
		for(int i=0; i<3; ++i) {
			int newMonth, newYear;
			if(month-i<1) {
				newMonth = (month-i) + 12;
				newYear = year-1;
			}
			else {
				newMonth = month - i;
				newYear = year;
			}
			yearmonth[i] = String.format("%04d%02d", newYear, newMonth);
			System.out.println(yearmonth[i]);
		}*/
	}

}
