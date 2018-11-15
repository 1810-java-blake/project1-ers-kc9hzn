package com.revature.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;
import com.revature.dao.UserDaoJdbc;
import com.revature.dao.UserDao;

public interface UserService {
	UserService currentImplementation = new UserServiceImpl();

	User getUserById(int id);
	
	User login(String username, String password, HttpSession httpSession);
}
