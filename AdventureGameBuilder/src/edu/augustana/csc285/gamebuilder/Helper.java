package edu.augustana.csc285.gamebuilder;

public class Helper {
	public static boolean checkLegalString(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkLegalInt(String str) {
		if (str != null && !str.equals("") && str.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkLegalDouble(String str) {
		if (str != null && !str.equals("") && str.matches("([0-9]*)\\.([0-9]*)")) {
			return true;
		} else {
			return false;
		}
	}
}
