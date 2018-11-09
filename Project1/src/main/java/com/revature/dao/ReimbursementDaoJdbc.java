package com.revature.dao;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
	public Reimbursement findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_reimbursement WHERE reimb_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return extractFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// GET /{id}
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
		// GET /
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
		// GET /user/{id}
		return null;
	}

	@Override
	public List<Reimbursement> findAllByStatus(ReimbursementStatus status) {
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
		// GET /status/{id}
		return null;
	}

	@Override
	public void resolveReimbursement(int id, ReimbursementStatus status) {
		GregorianCalendar cal = new GregorianCalendar();
		String timestamp = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE) + " ";
		timestamp = timestamp + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ers_reimbursement SET (reimb_resolved, reimb_status_id) "+ 
					"= (?, ?) WHERE reimb_id = ?");
			ps.setString(1, timestamp);
			ps.setInt(2, ReimbursementStatus.getIndex(status));
			ps.setInt(3, id);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// POST /status/{id}
	}

	@Override
	public int save(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt"
					+ " reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setDouble(1, newReimbursement.getAmount());
			ps.setString(2, newReimbursement.getSubmitted());
			ps.setString(3, newReimbursement.getResolved());
			ps.setString(4, newReimbursement.getDescription());
			ps.setString(5, newReimbursement.getReceipt());
			ps.setInt(6, newReimbursement.getAuthor().getId());
			ps.setNull(7, Types.INTEGER);
			ps.setInt(8, ReimbursementStatus.getIndex(newReimbursement.getStatus()));
			ps.setInt(9, ReimbursementType.getIndex(newReimbursement.getType()));
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// POST /
		return 0;
	}

}
