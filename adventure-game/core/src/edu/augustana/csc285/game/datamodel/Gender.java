package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;

public enum Gender {
	MALE, FEMALE, UNKNOWN;

	public static ArrayList<String> getGenders() {
		Gender[] genders = Gender.values();
		ArrayList<String> genderList = new ArrayList<String>();
		for (Gender gender : genders) {
			genderList.add(gender.toString());
		}
		return genderList;
	}
}
