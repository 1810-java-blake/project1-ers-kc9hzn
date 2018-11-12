package com.revature.util;

public class IntegerParser {
	public static boolean isInteger(String str) {
		try {
			int i = Integer.valueOf(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
