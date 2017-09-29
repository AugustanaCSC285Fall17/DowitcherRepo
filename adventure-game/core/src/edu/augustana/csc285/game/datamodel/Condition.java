package edu.augustana.csc285.game.datamodel;

import java.util.Random;

public enum Condition {
    GREATER,
    GREATER_OR_EQUAL,
    SMALLER,
    SMALLER_OR_EQUAL,
    EQUAL,
	RANDOM; // percentage
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
            case RANDOM:
            	Random rand = new Random();
            	int temp = rand.nextInt(100)+1;
            	return (temp>=condition);
            default:
                throw new AssertionError("Unknown condition " + this);
        }
    }

}