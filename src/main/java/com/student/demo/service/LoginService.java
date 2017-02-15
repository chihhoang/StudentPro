package com.student.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.dao.UserDao;
import com.student.demo.pojo.User;

@Service
public class LoginService {

	@Autowired
	private UserDao userDao;

	public User validateUser(String username, String password) {

		return userDao.getUserByUsernameAndPassword(username, password);

		/*
		 * User user = userDao.getUserByUsernameAndPassword(username, password);
		 * 
		 * if(user == null) { return false; } else { return true; }
		 */

	}

	public List<User> findAllUsers() {
		List<User> list = userDao.findAll();

		return list;
	}

}
