package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.dao.UserDao;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoJdbc implements ReimbursementDao {
	UserDao ud = UserDao.currentImplementation;
	private Reimbursement extractFromResultSet(ResultSet rs) throws SQLException {
		return new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_description"), rs.getString("reimb_receipt"), ud.getUserById(rs.getInt("reimb_author")), ud.getUserById(rs.getInt("reimb_resolver")), ReimbursementStatus.getStatus(rs.getInt("reimb_status_id")), ReimbursementType.getType(rs.getInt("reimb_type_id")));
	}
	
	@Override
	public Reimbursement findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findAll() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement");
			
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> findAllByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> findAllByStatus(ReimbursementStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void resolveReimbursement(int id, ReimbursementStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int save(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		return 0;
	}

}
