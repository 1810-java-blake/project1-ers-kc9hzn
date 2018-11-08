package com.revature.dao;

import javax.servlet.http.HttpSession;
import com.revature.model.User;

public interface UserDao {
	UserDao currentImplementation = new UserDaoJdbc();
	User getUserById(int id);
	User findByUsernameAndPassword(String username, String password);
}
