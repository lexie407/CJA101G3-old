package com.toiukha.spot.util;

public class NumberUtil {
	public static Integer safeParseInt(String value) {
		try {
			return Integer.valueOf(value.trim());
		} catch (Exception e) {
			return null;
		}
	}

	public static Double safeParseDouble(String value) {
		try {
			return Double.valueOf(value.trim());
		} catch (Exception e) {
			return null;
		}
	}

	public static Byte safeParseByte(String value) {
		try {
			return Byte.valueOf(value.trim());
		} catch (Exception e) {
			return null;
		}
	}
}
