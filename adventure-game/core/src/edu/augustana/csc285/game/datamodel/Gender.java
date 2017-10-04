package edu.augustana.csc285.game.datamodel;

public enum Gender {
	MALE,
	FEMALE,
	UNKNOWN;
	public String toString() {
		return this.name().toLowerCase();
	}
}
