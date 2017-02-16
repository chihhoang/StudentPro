package com.student.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.demo.pojo.User;
import com.student.demo.service.LoginService;
import com.student.demo.service.UserService;
import com.student.demo.vo.LoginVO;
import com.student.demo.vo.UserVO;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/login.com", method = RequestMethod.GET)
	public String goToLoginPage(Model model) {

		LoginVO loginVO = new LoginVO();

		model.addAttribute("loginObject", loginVO);

		return "login.jsp";

	}

	@RequestMapping(value = "/logout.com", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {

		session.invalidate();

		return "redirect:login.com";

	}

	@RequestMapping(value = "/dologin.com", method = RequestMethod.POST)
	public String doLogin(Model model, HttpSession session, HttpServletResponse response,
			@ModelAttribute("loginObject") LoginVO loginVO) {

		User user = loginService.validateUser(loginVO.getUsername(), loginVO.getPassword());

		if (user != null) {

			UserVO userVO = new UserVO();

			session.setAttribute("name", user.getName());

			Cookie cookie = new Cookie("username", "Root");
			response.addCookie(cookie);

			List<User> users = loginService.findAllUsers();

			model.addAttribute("user", userVO);
			model.addAttribute("users", users);

			return "dashboard.jsp";
		} else {
			return "redirect:login.com?error=error";
		}

	}

	// Delete
	@RequestMapping(value = "/delete.com", method = RequestMethod.GET)
	public String deleteUser(Model model, @RequestParam("id") Integer id) {

		userService.deleteUserByUserId(id);

		UserVO user = new UserVO();

		List<User> users = loginService.findAllUsers();

		model.addAttribute("user", user);
		model.addAttribute("users", users);

		return "dashboard.jsp";

	}

	// Process users, refresh page
	@RequestMapping(value = "/processUserDetail.com", method = RequestMethod.POST)
	public String processUserDetails(Model model, @ModelAttribute("user") UserVO userVO) {

		userService.processUserDetails(userVO);

		UserVO user = new UserVO();

		List<User> users = loginService.findAllUsers();

		model.addAttribute("user", user);
		model.addAttribute("users", users);

		return "dashboard.jsp";
	}

	// Edit
	@RequestMapping(value = "/edit.com", method = RequestMethod.GET)
	public String editUser(Model model, @RequestParam("id") Integer id) {

		UserVO userVO = userService.findUserById(id);

		List<User> users = loginService.findAllUsers();

		model.addAttribute("user", userVO);
		model.addAttribute("users", users);

		return "dashboard.jsp";
	}

}
