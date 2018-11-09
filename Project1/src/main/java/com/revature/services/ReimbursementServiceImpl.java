package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;

public class ReimbursementServiceImpl implements ReimbursementService {
	private ReimbursementDao rd = ReimbursementDao.currentImplementation;
	
	@Override
	public Reimbursement findById(int id) throws ArrayIndexOutOfBoundsException {
		return rd.findById(id);
	}

	@Override
	public List<Reimbursement> findAll() {
		return rd.findAll();
	}

	@Override
	public List<Reimbursement> findAllByUser(User user) {
		return rd.findAllByUser(user);
	}

	@Override
	public List<Reimbursement> findAllByStatus(ReimbursementStatus status) throws ArrayIndexOutOfBoundsException {
		return rd.findAllByStatus(status);
	}

	@Override
	public void resolveReimbursement(int id, ReimbursementStatus status, int userId) {
		rd.resolveReimbursement(id, status, userId);
	}

	@Override
	public int save(Reimbursement newReimbursement) {
		return rd.save(newReimbursement);
	}

}
