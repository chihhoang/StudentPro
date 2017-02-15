package com.student.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.demo.pojo.User;
import com.student.demo.service.LoginService;
import com.student.demo.service.UserService;

public class DeleteServlet extends HttpServlet {
	
	LoginService loginService = new LoginService();
	UserService userService = new UserService();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		userService.deleteUserByUserId(id);
		
		List<User> list = loginService.findAllUsers();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		
		
	}
	
	
	
	
	
	

}
