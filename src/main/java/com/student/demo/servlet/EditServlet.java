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
import com.student.demo.vo.UserVO;

//Using Spring to build controller instead of this servlet
public class EditServlet extends HttpServlet {

	UserService userService = new UserService();

	LoginService loginService = new LoginService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		UserVO user = userService.findUserById(id);

		request.setAttribute("user", user);

		// refresh
		List<User> list = loginService.findAllUsers();
		request.setAttribute("list", list);

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);

	}
}
