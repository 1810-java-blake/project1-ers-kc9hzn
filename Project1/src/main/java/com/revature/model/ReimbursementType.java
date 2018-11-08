package com.revature.model;

public enum ReimbursementType {
	LODGING, TRAVEL, FOOD, OTHER;
	
	private static ReimbursementType[] values = ReimbursementType.values();
	
	public static ReimbursementType getType(int id) {
		return values[id - 1];
	}
}
