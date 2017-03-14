package com.student.demo.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.demo.pojo.User;
import com.student.demo.service.LoginService;
import com.student.demo.service.UserService;

// Using Spring to build controller instead of this servlet
public class ProcessUserServlet extends HttpServlet {

	UserService userService = new UserService();
	LoginService loginService = new LoginService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");

		User user = new User();
		if (id == null || id.isEmpty()) {
			user.setId(null);
		} else {
			user.setId(Integer.parseInt(id));
		}

		user.setName(name);
		user.setPhoneNo(phoneNo);
		user.setUsername(username);
		user.setPassword(password);
		try {
			user.setDob(format.parse(dob));
		} catch (ParseException e) {
			user.setDob(null);
		}

		// userService.processUserDetail(user);

		// refresh display
		List<User> list = loginService.findAllUsers();
		request.setAttribute("list", list);

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);

	}

}
