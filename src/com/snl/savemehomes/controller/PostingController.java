package com.snl.savemehomes.controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NoPermissionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snl.savemehomes.common.UserRole;
import com.snl.savemehomes.dto.BoardDto;
import com.snl.savemehomes.dto.NoticeDto;
import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.exception.NoPermissionsException;
import com.snl.savemehomes.service.BoardServiceImpl;
import com.snl.savemehomes.service.NoticeServiceImpl;

@WebServlet(name = "posting", urlPatterns = { "/posting" })
public class PostingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostingController() {
        super();
    }
    
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
		
		if("noticeWrite".equals(act)) {
			// 페이지이동 : 공지사항 작성 페이지 이동
			String url = "/posting/writing.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("noticeSave".equals(act)) {
			// CREATE : 공지사항 저장
			noticeSave(request, response);
		}
		else if("noticeList".equals(act)) {
			// READ : 공지사항 목록 pagination 
			noticeList(request, response);
		}
		else if("notice".equals(act)) {
			// READ : 공지사항 읽기
			noticeRead(request, response);
		}
		else if("noticeModify".equals(act)) {
			// 페이지이동 : 공지사항 수정 페이지 이동
			noticeModify(request, response);
		}
		else if("noticeModifiedSave".equals(act)) {
			// UPDATE : 공지사항 수정 후 저장
			noticeModifiedSave(request, response);
		}
		else if("noticeDelete".equals(act)) {
			// DELETE : 공지사항 삭제
			noticeDelete(request, response);
		}
		else if("boardWrite".equals(act)) {
			// 페이지이동 : 게시판 작성 페이지 이동
			String url = "/posting/writing.jsp";
			request.getRequestDispatcher(url).forward(request, response);
		}
		else if("boardSave".equals(act)) {
			// CREATE : 게시글 저장
			boardSave(request, response);
		}
		else if("board".equals(act)) {
			// READ : 게시글 읽기
//			String url = "/posting/reading.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
			boardRead(request, response);
		}
		else if("boardList".equals(act)) {
			// READ : 공지사항 목록 pagination 
			boardList(request, response);
		}
		else if("boardModify".equals(act)) {
			// 페이지이동 : 게시글 수정 페이지 이동
//			String url = "/posting/modify.jsp";
//			request.getRequestDispatcher(url).forward(request, response);
			boardModify(request, response);
		}
		else if("boardModifiedSave".equals(act)) {
			// UPDATE : 게시글 수정 후 저장
//			response.sendRedirect(root+"/posting/board.jsp");
			boardModifiedSave(request, response);
		}
		else if("boardDelete".equals(act)) {
			// DELETE : 게시글 삭제
			boardDelete(request, response);
		}
		else {
			response.sendRedirect(root);
		}
	}

	private void noticeSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		String noticeWriter = ((UserDto)session.getAttribute("signInUser")).getUserId();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setNoticeWriter(noticeWriter);
		noticeDto.setNoticeTitle(title);
		noticeDto.setNoticeContent(contents);
		
		try {
			if(!NoticeServiceImpl.getInstance().writeNotice(noticeDto)) {
				request.setAttribute("msg", "공지사항 작성에 실패했습니다.");
				path = "/error/error500.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (NoPermissionsException e) {
			request.setAttribute("msg", "공지사항 작성 권한이 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			e.printStackTrace();
			return;
		}
		int pageCount = NoticeServiceImpl.getInstance().readNoticePageCount();
		session.setAttribute("noticePageCount", pageCount);
		response.sendRedirect(root+"/posting/notice.jsp");
	}


	private void noticeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		List<NoticeDto> noticeList = NoticeServiceImpl.getInstance().readNoticeList(page);
		int pageCount = NoticeServiceImpl.getInstance().readNoticePageCount();
		HttpSession session = request.getSession();
		session.setAttribute("noticePageCount", pageCount);
		StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(NoticeDto noticeDto : noticeList) {
			SB.append(noticeDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());
		
	}
	
	private void noticeRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = root;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeDto noticeDto = NoticeServiceImpl.getInstance().readNoticeByIdx(idx);
		if(noticeDto==null) {
			request.setAttribute("msg", "[공지사항]<br>공지사항을 읽을 수 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		request.setAttribute("title", noticeDto.getNoticeTitle());
		request.setAttribute("writer", noticeDto.getNoticeWriter());
		request.setAttribute("postingdate", noticeDto.getNoticeDate());
		request.setAttribute("contents", noticeDto.getNoticeContent());
		path = "/posting/reading.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

	private void noticeModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = root;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeDto noticeDto = NoticeServiceImpl.getInstance().readNoticeByIdx(idx);
		if(noticeDto==null) {
			request.setAttribute("msg", "[공지사항 수정]<br>공지사항을 읽을 수 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		request.setAttribute("title", noticeDto.getNoticeTitle());
		request.setAttribute("writer", noticeDto.getNoticeWriter());
		request.setAttribute("contents", noticeDto.getNoticeContent());
		path = "/posting/modify.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void noticeModifiedSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		String noticeWriter = ((UserDto)session.getAttribute("signInUser")).getUserId();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setIdx(idx);
		noticeDto.setNoticeWriter(noticeWriter);
		noticeDto.setNoticeTitle(title);
		noticeDto.setNoticeContent(contents);
		
		try {
			if(!NoticeServiceImpl.getInstance().modifyNotice(noticeDto)) {
				request.setAttribute("msg", "공지사항 수정에 실패했습니다.");
				path = "/error/error500.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (NoPermissionsException e) {
			request.setAttribute("msg", "공지사항 수정 권한이 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			e.printStackTrace();
			return;
		}
		response.sendRedirect(root+"/posting/notice.jsp");
		
	}

	private void noticeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		
		System.out.println(((UserDto)session.getAttribute("signInUser")).toString());
		UserRole userRole = ((UserDto)session.getAttribute("signInUser")).getUserRole();
		if(userRole != UserRole.ADMINISTRATOR) {
			request.setAttribute("msg", "공지사항 삭제 권한이 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		if(!NoticeServiceImpl.getInstance().deleteNotice(Integer.parseInt(request.getParameter("idx")))) {
			request.setAttribute("msg", "공지사항 삭제에 실패했습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		int pageCount = NoticeServiceImpl.getInstance().readNoticePageCount();
		session.setAttribute("noticePageCount", pageCount);
		response.sendRedirect(root+"/posting/notice.jsp");
					
	}
	
	private void boardSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		String boardWriter = ((UserDto)session.getAttribute("signInUser")).getUserId();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardWriter(boardWriter);
		boardDto.setBoardTitle(title);
		boardDto.setBoardContent(contents);
		
		if(!BoardServiceImpl.getInstance().writeBoard(boardDto)) {
			System.out.println("작성실패");
			request.setAttribute("msg", "게시글 작성에 실패했습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		int pageCount = BoardServiceImpl.getInstance().readBoardPageCount();
		session.setAttribute("boardPageCount", pageCount);
		response.sendRedirect(root+"/posting/board.jsp");
	}
	
	private void boardList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		List<BoardDto> boardList = BoardServiceImpl.getInstance().readBoardList(page);
		int pageCount = BoardServiceImpl.getInstance().readBoardPageCount();
		HttpSession session = request.getSession();
		session.setAttribute("boardPageCount", pageCount);
		StringBuilder SB = new StringBuilder();
		SB.append("[");
		for(BoardDto boardDto : boardList) {
			SB.append(boardDto.toJSONString()).append(",");
		}
		SB.deleteCharAt(SB.lastIndexOf(","));
		SB.append("]");
		response.getWriter().print(SB.toString());
	}
	
	private void boardRead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = root;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDto boardDto = BoardServiceImpl.getInstance().readBoardByIdx(idx);
		if(boardDto==null) {
			request.setAttribute("msg", "[게시판]<br>게시글을 읽을 수 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		request.setAttribute("title", boardDto.getBoardTitle());
		request.setAttribute("writer", boardDto.getBoardWriter());
		request.setAttribute("postingdate", boardDto.getBoardDate());
		request.setAttribute("contents", boardDto.getBoardContent());
		path = "/posting/reading.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void boardModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = root;
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDto boardDto = BoardServiceImpl.getInstance().readBoardByIdx(idx);
		if(boardDto==null) {
			request.setAttribute("msg", "[게시글 수정]<br>게시글을 읽을 수 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			return;
		}
		request.setAttribute("title", boardDto.getBoardTitle());
		request.setAttribute("writer", boardDto.getBoardWriter());
		request.setAttribute("contents", boardDto.getBoardContent());
		path = "/posting/modify.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void boardModifiedSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		String boardWriter = ((UserDto)session.getAttribute("signInUser")).getUserId();
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		BoardDto boardDto = new BoardDto();
		boardDto.setIdx(idx);
		boardDto.setBoardWriter(boardWriter);
		boardDto.setBoardTitle(title);
		boardDto.setBoardContent(contents);
		
		try {
			if(!BoardServiceImpl.getInstance().modifyBoard(boardDto)) {
				request.setAttribute("msg", "게시글 수정에 실패했습니다.");
				path = "/error/error500.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (NoPermissionsException e) {
			request.setAttribute("msg", "게시글 수정 권한이 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			e.printStackTrace();
			return;
		}
		response.sendRedirect(root+"/posting/board.jsp");
	}
	
	private void boardDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String root = request.getContextPath();
		String path = root;
		
		try {
			if(!BoardServiceImpl.getInstance().deleteBoard(Integer.parseInt(request.getParameter("idx")), ((UserDto)session.getAttribute("signInUser")))) {
				request.setAttribute("msg", "게시글 삭제에 실패했습니다.");
				path = "/error/error500.jsp";
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (NoPermissionsException e) {
			request.setAttribute("msg", "게시글 삭제 권한이 없습니다.");
			path = "/error/error500.jsp";
			request.getRequestDispatcher(path).forward(request, response);
			e.printStackTrace();
			return;
		} 
		int pageCount = BoardServiceImpl.getInstance().readBoardPageCount();
		session.setAttribute("boardPageCount", pageCount);
		response.sendRedirect(root+"/posting/board.jsp");
	}
}
