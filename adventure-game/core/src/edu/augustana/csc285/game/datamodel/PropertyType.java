package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public enum PropertyType {
	HEALTH(10, "0"), MORALE(10, "1"), DAY(0, "2");
	private int default_value;
	private String default_id;

	// this should not be used only to make it compilable
	private PropertyType(int value, String id) {
		this.default_value = value;
		this.default_id = id;
	}

	public int getValue() {
		return this.default_value;
	}

	public String getID() {
		return this.default_id;
	}

	public String toString() {
		return this.name().toLowerCase();
	}

}
