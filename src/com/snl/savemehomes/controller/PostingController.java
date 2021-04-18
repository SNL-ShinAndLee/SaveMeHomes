package com.snl.savemehomes.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostingController
 */
@WebServlet(name = "posting", urlPatterns = { "/posting" })
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String root = request.getContextPath();
		String act = request.getParameter("act");
		
		if("notice".equals(act)) {
			String url = "/posting/reading.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("board".equals(act) ) {
			String url = "/posting/reading.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("noticeWrite".equals(act)) {
			String url = "/posting/writing.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("boardWrite".equals(act)) {
			String url = "/posting/writing.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("noticeSave".equals(act)) {
			//공지사항 저장 서비스
//			System.out.println(request.getParameter("writeId"));
//			System.out.println(request.getParameter("title"));
//			System.out.println(request.getParameter("contents"));
			response.sendRedirect(root+"/posting/notice.jsp");
		}
		else if("boardSave".equals(act)) {
			//게시글 저장 서비스
			response.sendRedirect(root+"/posting/board.jsp");
		}
		else {
			response.sendRedirect(root);
		}
	}

}
