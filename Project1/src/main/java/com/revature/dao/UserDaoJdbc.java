package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.User;
import com.revature.model.UserRoles;
import com.revature.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
	private User extractFromResultSet(ResultSet rs) throws SQLException {
		return new User(rs.getInt("ers_users_id"), rs.getString("ers_username"), rs.getString("ers_password"), rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"), UserRoles.getRole(rs.getInt("user_role_id")));
	}
	
	@Override
	public User getUserById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users WHERE ers_users_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// GET /{id}
		return null;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?");
			ps.setString(1,  username);
			ps.setString(2,  password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			
		}
		// POST /login
		return null;
	}
}