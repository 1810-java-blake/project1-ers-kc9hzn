package com.revature.services;

import javax.servlet.http.HttpSession;

import com.revature.dao.UserDao;
import com.revature.model.User;

public class UserServiceImpl implements UserService {
	private UserDao ud = UserDao.currentImplementation;
	@Override
	public User getUserById(int id) {
		return ud.getUserById(id);
	}

	@Override
	public User login(String username, String password, HttpSession httpSession) {
		User u = ud.findByUsernameAndPassword(username, password);
		if (u != null) {
			
			return u;
		}
		return null;
	}

}
