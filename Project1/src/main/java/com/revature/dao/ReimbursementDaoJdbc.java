package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoJdbc implements ReimbursementDao {
	UserDao ud = UserDao.currentImplementation;
	private Reimbursement extractFromResultSet(ResultSet rs) throws SQLException {
		return new Reimbursement(rs.getInt("reimb_id"), rs.getDouble("reimb_amount"), rs.getString("reimb_submitted"), rs.getString("reimb_resolved"), rs.getString("reimb_description"), rs.getString("reimb_receipt"), ud.getUserById(rs.getInt("reimb_author")), ud.getUserById(rs.getInt("reimb_resolver")), ReimbursementStatus.getStatus(rs.getInt("reimb_status_id")), ReimbursementType.getType(rs.getInt("reimb_type_id")));
	}
	
	@Override
	public Reimbursement findById(int id) throws ArrayIndexOutOfBoundsException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return extractFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// GET /Project1/reimbursements/{id}
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
		// GET /Project1/reimbursements/
		return null;
	}

	@Override
	public List<Reimbursement> findAllByUser(User user) {
		int id = user.getId();
		try (Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_author = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// GET /Project1/reimbursements/user/{id}
		return null;
	}

	@Override
	public List<Reimbursement> findAllByStatus(ReimbursementStatus status) throws ArrayIndexOutOfBoundsException {
		int id = ReimbursementStatus.getIndex(status);
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<Reimbursement> reimbursements = new ArrayList<>();
			while (rs.next()) {
				reimbursements.add(extractFromResultSet(rs));
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// GET /Project1/reimbursements/status/{id}
		return null;
	}

	@Override
	public void resolveReimbursement(int id, ReimbursementStatus status, int userId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ers_reimbursement SET (reimb_resolved, reimb_resolver, reimb_status_id) "+ 
					"= (?, ?, ?) WHERE reimb_id = ?");
			ps.setTimestamp(1, timestamp);
			ps.setInt(2, userId);
			ps.setInt(3, ReimbursementStatus.getIndex(status));
			ps.setInt(4, id);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// POST /Project1/reimbursements/status/{id}
	}

	@Override
	public int save(Reimbursement newReimbursement) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, "
					+ " reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setDouble(1, newReimbursement.getAmount());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setNull(3, Types.TIMESTAMP);
			ps.setString(4, newReimbursement.getDescription());
			ps.setString(5, newReimbursement.getReceipt());
			System.out.println(newReimbursement.getAuthor());
			ps.setInt(6, newReimbursement.getAuthor().getId());
			ps.setNull(7, Types.INTEGER);
			ps.setInt(8, ReimbursementStatus.getIndex(newReimbursement.getStatus()));
			ps.setInt(9, ReimbursementType.getIndex(newReimbursement.getType()));
			ps.executeQuery();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// POST /Project1/reimbursements/
		return 0;
	}

}
