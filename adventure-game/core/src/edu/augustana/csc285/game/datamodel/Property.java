package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public enum Property {
	HEALTH, MORALE, GOLD, DAY;

	public String toString() {
		switch (this) {
		case HEALTH:
			return "health";
		case MORALE:
			return "morale";
		case GOLD:
			return "gold";
		case DAY:
			return "day";
		default:
			throw new IllegalArgumentException("Not an enum type");
		}
	}
}
