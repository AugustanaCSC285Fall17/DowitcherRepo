package edu.augustana.csc285.game.datamodel;

import java.util.Random;

public enum ConditionOperation {
    GREATER,
    GREATER_OR_EQUAL,
    SMALLER,
    SMALLER_OR_EQUAL,
    EQUAL;
	// condition should always be positive, original should always be non negative
    boolean checkCondition(int original, int condition) {
        switch (this) {
            case GREATER:
                return (original>condition);
            case GREATER_OR_EQUAL:
            	return (original>=condition);
            case SMALLER:
                return (original<condition);
            case SMALLER_OR_EQUAL:
            	return (original<=condition);
            case EQUAL:
            	return (original==condition);
            default:
                throw new AssertionError("Unknown condition " + this);
        }
    }
	public String toString() {
		return this.name().toLowerCase();
	}

}