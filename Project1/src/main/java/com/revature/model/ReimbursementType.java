package com.revature.model;

public enum ReimbursementType {
	LODGING, TRAVEL, FOOD, OTHER;
	
	private static ReimbursementType[] values = ReimbursementType.values();
	
	public static ReimbursementType getType(int id) {
		return values[id - 1];
	}
	
	public static int getIndex(ReimbursementType type) {
		for (int i = 0; i < values.length; i++) {
			if (type.equals(values[i])) {
				return i + 1;
			}
		}
		return -1;
	}
}
