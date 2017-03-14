package com.student.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.student.demo.pojo.User;
import com.student.demo.service.LoginService;

//Using Spring to build controller instead of this servlet
public class LoginServlet extends HttpServlet	{

	@Autowired
	private LoginService loginService;
	//	private LoginService loginService = new LoginService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//		Boolean status = loginService.validateUser(username, password);
		Boolean status = true;

		if(status)	{
			List<User> list = loginService.findAllUsers();
			request.setAttribute("list", list);

			//			response.sendRedirect("dashboard.jsp");
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		}
		else	{
			response.sendRedirect("login.jsp?error=error");	// response to user and then send a new request
		}

	}

}
