package com.snl.savemehomes.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.service.UserService;
import com.snl.savemehomes.service.UserServiceImpl;

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
		
		if("signin".equals(act)) {
			System.out.println("signin");
		}
		else if("signout".equals(act) ) {
			
		}
		else if("signup".equals(act)) {
			if(!request.getParameter("signUpPassword").equals(request.getParameter("signUpPasswordCheck"))) {
				request.setAttribute("msg", "회원가입에 실패 했습니다.");
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
			
			boolean success = UserServiceImpl.getUserService().signUp(userDto);
			
			if(success) {
				System.out.println("성공이다");
				request.setAttribute("msg", "회원가입 성공");
				path = root + "/success/signupsuccess.jsp" ;
			}
			else {
				System.out.println("성공이다");
				request.setAttribute("msg", "회원가입에 실패 했습니다.");
				path = root + "/error/signuperror.jsp";
			}
//			request.getRequestDispatcher(path).forward(request, response);
			response.sendRedirect(path);

		}
		else if("modifyUser".equals(act)) {
			
		}
		else if("deleteUser".equals(act)) {
			
		}
		else if("userInfo".equals(act)) {
			
		}
		else if("idDuplication".equals(act)) {
			String id = request.getParameter("signupid");
			System.out.println(id);
			boolean duplication = UserServiceImpl.getUserService().idDuplication(id);
			response.getWriter().println(duplication);
		}
		else {
			response.sendRedirect(root);
		}
	}

}
