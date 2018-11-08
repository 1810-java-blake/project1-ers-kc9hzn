package com.revature.model;

public enum ReimbursementStatus {
	PENDING, APPROVED, DENIED;

	private static ReimbursementStatus[] values = ReimbursementStatus.values();
	
	public static ReimbursementStatus getStatus(int id) {
		return values[id - 1];
	}
}


