package com.snl.savemehomes.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snl.savemehomes.dto.ApartmentDto;
import com.snl.savemehomes.dto.CityDto;
import com.snl.savemehomes.dto.NoticeDto;
import com.snl.savemehomes.service.ApartmentService;
import com.snl.savemehomes.service.ApartmentServiceImpl;
import com.snl.savemehomes.service.CityService;
import com.snl.savemehomes.service.CityServiceImpl;

@WebServlet(name = "city", urlPatterns = { "/city" })
public class CityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CityService cityService = CityServiceImpl.getInstance();
	private final ApartmentService apartmentService = ApartmentServiceImpl.getInstance();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String root = request.getContextPath();
		String act = request.getParameter("act");
		
		if("sidolist".equals(act)) {
			sidoList(request, response);
		}
		else if("gugunlist".equals(act)) {
			gugunList(request, response);
		}
		else if("donglist".equals(act)) {
			dongList(request, response);
		}
		else if("citysearch".equals(act)) {
			citySearch(request, response);
		}
		else if("homesinfo".equals(act)) {
			homesInfo(request, response);
		}
		else if("dropdowndata".equals(act)) {
			dropdownData(request, response);
		}
		else {
			response.sendRedirect(root);
		}
	}
	
	
	private void sidoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
        List<CityDto> sidoList = cityService.getSidoList();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("charset=utf-8");
        
        StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(CityDto cityDto : sidoList) {
			SB.append(cityDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());
	}
	
	private void gugunList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long cityCode = Long.parseLong(request.getParameter("citycode"));
		
		List<CityDto> gugunList = cityService.getGugunList(cityCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("charset=utf-8");
        
        StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(CityDto cityDto : gugunList) {
			SB.append(cityDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());

	}
	
	private void dongList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long cityCode = Long.parseLong(request.getParameter("citycode"));
		
		List<CityDto> dongList = cityService.getDongList(cityCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("charset=utf-8");
        
        StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(CityDto cityDto : dongList) {
			SB.append(cityDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());
		
	}
	
	private void citySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/search.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void homesInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long dongCode = Long.parseLong(request.getParameter("dongcode"));
		
		List<ApartmentDto> homesInfoList = apartmentService.readHomesInfoListByDong(dongCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("charset=utf-8");
        
        StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(ApartmentDto apartmentDto : homesInfoList) {
			SB.append(apartmentDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());
		
	}
	
	private void dropdownData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		long cityCode = Long.parseLong(request.getParameter("citycode"));
		CityDto cityDto = cityService.getCity(cityCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("charset=utf-8");
        
        StringBuilder SB = new StringBuilder();
		SB.append(cityDto.toJSONString());
		response.getWriter().print(SB.toString());
	}
}
