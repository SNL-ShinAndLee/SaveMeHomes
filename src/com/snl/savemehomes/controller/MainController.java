package com.snl.savemehomes.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "main", urlPatterns = { "/main" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String root = request.getContextPath();
		String act = request.getParameter("act");
		
		if("notice".equals(act)) {
//			notice(request, response);
			response.sendRedirect(root + "/posting/notice.jsp");
		}
		else if("todaynews".equals(act))
		{
			response.sendRedirect(root /*+ "/notice.jsp"*/);
		}
		else if("board".equals(act) ) {
			response.sendRedirect(root + "/posting/board.jsp");
		}
		else {
			response.sendRedirect(root);
		}
	}

}
