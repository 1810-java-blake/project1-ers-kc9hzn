package com.revature.model;

public enum ReimbursementStatus {
	PENDING, APPROVED, DENIED;

	private int status;
	
	private static ReimbursementStatus[] values = ReimbursementStatus.values();
	
	public static ReimbursementStatus getStatus(int id) {
		return values[id - 1];
	}
	
	public static int getIndex(ReimbursementStatus status) {
		for (int i = 0; i < values.length; i++) {
			if (status.equals(values[i])) {
				return i + 1;
			}
		}
		return -1;
	}
}


