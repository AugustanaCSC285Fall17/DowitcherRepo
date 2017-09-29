package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public enum Property {
	HEALTH, MORALE, GOLD, DAY;

	public String toString() {
		return this.name().toLowerCase();
	}
}
