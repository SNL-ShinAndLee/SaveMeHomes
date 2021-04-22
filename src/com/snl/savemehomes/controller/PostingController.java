package com.snl.savemehomes.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snl.savemehomes.dto.NoticeDto;
import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.service.NoticeServiceImpl;

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
			noticeSave(request, response);
			response.sendRedirect(root+"/posting/notice.jsp");
		}
		else if("boardSave".equals(act)) {
			//게시글 저장 서비스
			response.sendRedirect(root+"/posting/board.jsp");
		}
		else if("noticeModify".equals(act)) {
			String url = "/posting/modify.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("boardModify".equals(act)) {
			String url = "/posting/modify.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("noticeModifiedSave".equals(act)) {
			//공지사항 수정 서비스
//			System.out.println(request.getParameter("idx"));
//			System.out.println(request.getParameter("writeId"));
//			System.out.println(request.getParameter("title"));
//			System.out.println(request.getParameter("contents"));
			response.sendRedirect(root+"/posting/notice.jsp");
		}
		else if("boardModifiedSave".equals(act)) {
			//게시글 수정 서비스
			response.sendRedirect(root+"/posting/board.jsp");
		}
		else {
			response.sendRedirect(root);
		}
	}

	private void noticeSave(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String noticeWriter = ((UserDto)session.getAttribute("signInUser")).getUserId();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setNoticeWriter(noticeWriter);
		noticeDto.setNoticeTitle(title);
		noticeDto.setNoticeContent(contents);
		
		if(!NoticeServiceImpl.getInstance().writeNotice(noticeDto)) {
			//작성 실패 시 어디로 가지?
			System.out.println("작성실패");
			return;
		}
		System.out.println("작성성공");
		//결과를 어떻게 받을 지
	}
	

}
