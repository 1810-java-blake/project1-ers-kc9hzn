package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoJdbc;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.User;

public interface ReimbursementService {
	ReimbursementService currentImplementation = new ReimbursementServiceImpl();
	
	Reimbursement findById(int id);
	
	List<Reimbursement> findAll();
	List<Reimbursement> findAllByUser(User user);
	List<Reimbursement> findAllByStatus(ReimbursementStatus status);
	
	void resolveReimbursement(int id, ReimbursementStatus status);
	
	int save(Reimbursement newReimbursement);
}
