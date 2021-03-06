package com.snl.savemehomes.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.service.UserService;
import com.snl.savemehomes.service.UserServiceImpl;
import com.snl.savemehomes.util.JSONUtil;

import sun.misc.IOUtils;

@WebServlet(name = "user", urlPatterns = { "/user" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
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
		String path = "/index.jsp";
		String root = request.getContextPath();
		String act = request.getParameter("act");
		
		if("idDuplication".equals(act)) {
			isDuplication(request, response);
		}
		else if("signup".equals(act)) {
			signUp(request, response);
		}
		else if("signin".equals(act)) {
			signIn(request, response);
		}
		else if("signout".equals(act) ) {
			signOut(request, response);
		}
		else if("userInfo".equals(act)) {
			userInfo(request, response);
		}
		else if("modifyUser".equals(act)) {
			modifyUser(request, response);
		}
		else if("withdrawUser".equals(act)) {
			withdrawUser(request, response);
		}
		else {
			response.sendRedirect(root);
		}
	}
	


	protected void isDuplication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("signupid");
		boolean duplication = UserServiceImpl.getInstance().idDuplication(id);
		response.getWriter().println(duplication);
	}
	protected void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		String root = request.getContextPath();
		
		if(!request.getParameter("signUpPassword").equals(request.getParameter("signUpPasswordCheck"))) {
			request.setAttribute("msg", "??????????????? ?????? ????????????.");
			path = root + "/error/signuperror.jsp";
			response.sendRedirect(path);
			return;
		}		
		UserDto userDto = new UserDto();
		
		userDto.setUserId(request.getParameter("signUpId"));
		userDto.setUserPass(request.getParameter("signUpPassword"));
		userDto.setUserName(request.getParameter("signUpName"));
		userDto.setUserEmail(request.getParameter("signUpEmail"));
		userDto.setUserAddress(request.getParameter("signUpAddress"));
		
		boolean success = UserServiceImpl.getInstance().signUp(userDto);
		
		if(success) {
			System.out.println("????????????");
			request.setAttribute("msg", "???????????? ??????");
			path = root + "/success/signupsuccess.jsp" ;
		}
		else {
			System.out.println("????????????");
			request.setAttribute("msg", "??????????????? ?????? ????????????.");
			path = root + "/error/signuperror.jsp";
		}
//		request.getRequestDispatcher(path).forward(request, response);
		response.sendRedirect(path);
		
	}
	protected void signIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signInId = request.getParameter("signInId");
		String signInPassword = request.getParameter("signInPassword");
		
		UserDto userDto = UserServiceImpl.getInstance().signIn(signInId, signInPassword);
		String path = "/index.jsp";
		if(userDto != null) { // ??????
//			session ??????
			HttpSession session = request.getSession();
			session.setAttribute("signInUser", userDto);
//			Cookie ??????
			String idSave = request.getParameter("signInCheck");
			if("saveCheck".equals(idSave)) { // ????????? ?????? O
				Cookie cookie = new Cookie("saveId", signInId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				response.addCookie(cookie);
			} 
			else { // ????????? ?????? X
				Cookie cookies[] = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if(cookie.getName().equals("saveId")) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
		} 
		else { //????????? ??????
			request.setAttribute("msg", "???????????? ?????? ??????????????????, ????????? ?????????????????????.");
			path = "/error/error500.jsp";
		}
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}
	protected void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}
	
	protected void userInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String root = request.getContextPath();
		response.sendRedirect(root+"/user/userinfo.jsp");
	}

	private void modifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		
		String requestPOSTData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Map<String, String> map = JSONUtil.parse(requestPOSTData);
		
		String msg;
		String signInUserId = ((UserDto)session.getAttribute("signInUser")).getUserId(); 
		String originPass = map.get("originpass");
		
		if(!UserServiceImpl.getInstance().isCorrectPass(signInUserId, originPass)) {
			msg="WrongPass";
			response.getWriter().println(msg);
			return;
		}
		
		String newpass = map.get("newpass");
		String newpasscheck = map.get("newpasscheck");
		if(!newpass.equals(newpasscheck)) {
			msg = "fail";
			response.getWriter().println(msg);
			return;
		}
		
		UserDto userDto = new UserDto();
		userDto.setIdx(((UserDto)session.getAttribute("signInUser")).getIdx());
		userDto.setUserId(signInUserId);
		if(!newpass.equals(""))
			userDto.setUserPass(newpass);
		else 
			userDto.setUserPass(originPass);
		userDto.setUserName(map.get("modifyName"));
		userDto.setUserEmail(map.get("modifyEmail"));
		userDto.setUserAddress(map.get("modifyAddress"));
		
		if(!UserServiceImpl.getInstance().modify(userDto)) {
			msg = "fail";
			response.getWriter().println(msg);
			return;
		}
		
		msg = "success";
		session.invalidate();
		response.getWriter().println(msg);
	}
	
	private void withdrawUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		String requestPOSTData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		Map<String, String> map = JSONUtil.parse(requestPOSTData);
		
		String msg;
		String signInUserId = ((UserDto)session.getAttribute("signInUser")).getUserId(); 
		String originPass = map.get("originpass");
		
		System.out.println(signInUserId);
		System.out.println(originPass);
		if(!UserServiceImpl.getInstance().withdraw(signInUserId, originPass)) {
			msg = "fail";
			response.getWriter().println(msg);
			return;
		}
		msg = "success";
		session.invalidate();
		response.getWriter().println(msg);
	}
	
}
