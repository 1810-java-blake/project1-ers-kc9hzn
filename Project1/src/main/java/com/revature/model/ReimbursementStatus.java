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
	
	
	
//	public static ReimbursementStatus parseString(String s) {
//		switch (s) {
//			case "PENDING":
//				return ReimbursementStatus.valueOf("PENDING");
//			case "APPROVED":
//				return ReimbursementStatus.valueOf("APPROVED");
//			case "DENIED":
//				return ReimbursementStatus.valueOf("DENIED");
//			default:
//				return null;
//		}
//	}
	
	
}


