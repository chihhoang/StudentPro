package com.student.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.demo.dao.UserDao;
import com.student.demo.pojo.User;
import com.student.demo.vo.UserVO;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void processUserDetails(UserVO userVO) {

		/*
		 * User user = new User(); user.setId(userVO.getId());
		 * user.setName(userVO.getName());
		 * user.setPhone_no(userVO.getPhoneNo());
		 * user.setUsername(userVO.getUsername());
		 * user.setPassword(userVO.getPassword()); user.setDob(userVO.getDob());
		 */

		User user = new User();
		BeanUtils.copyProperties(userVO, user);

		if (userVO.getId() == null) {
			userDao.insert(user);
		} else {
			userDao.update(user);
		}
	}

	public void deleteUserByUserId(Integer id) {
		userDao.delete(id);
	}

	public UserVO findUserById(Integer id) {

		User user = userDao.findById(id);

		UserVO userVO = new UserVO();

		BeanUtils.copyProperties(user, userVO);

		return userVO;
	}

}
