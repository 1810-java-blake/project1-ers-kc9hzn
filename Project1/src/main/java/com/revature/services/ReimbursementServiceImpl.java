package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;

public class ReimbursementServiceImpl implements ReimbursementService {
	private ReimbursementDao rd = ReimbursementDao.currentImplementation;
	
	@Override
	public Reimbursement findById(int id) {
		// TODO Auto-generated method stub
		// GET
		return null;
	}

	@Override
	public List<Reimbursement> findAll() {
		return rd.findAll();
	}

	@Override
	public List<Reimbursement> findAllByUser(User user) {
		// TODO Auto-generated method stub
		// GET
		return null;
	}

	@Override
	public List<Reimbursement> findAllByStatus(ReimbursementStatus status) {
		// TODO Auto-generated method stub
		// GET
		return null;
	}

	@Override
	public void resolveReimbursement(int id, ReimbursementStatus status) {
		// TODO Auto-generated method stub
		// POST
		
	}

	@Override
	public int save(Reimbursement newReimbursement) {
		// TODO Auto-generated method stub
		// POST
		return 0;
	}

}
