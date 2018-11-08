package com.revature.model;

public enum UserRoles {
	USER, ADMIN;
	
	private static UserRoles[] values = UserRoles.values();
	
	public static UserRoles getRole(int id) {
		return values[id - 1];
	}
}