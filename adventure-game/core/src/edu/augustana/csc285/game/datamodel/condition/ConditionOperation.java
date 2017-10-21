package edu.augustana.csc285.game.datamodel.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import edu.augustana.csc285.game.datamodel.Gender;

public enum ConditionOperation {
	GREATER, GREATER_OR_EQUAL, SMALLER, SMALLER_OR_EQUAL, EQUAL, NOT_EQUAL;
	// condition should always be positive, original should always be non
	// negative
	public boolean checkCondition(Gender original, Gender condition) {
		switch (this) {
		case EQUAL:
			return (original == condition);
		case NOT_EQUAL:
			return (original != condition);
		default:
			throw new AssertionError("Unknown condition " + this);
		}
	}

	public boolean checkCondition(int original, int condition) {
		switch (this) {
		case GREATER:
			return (original > condition);
		case GREATER_OR_EQUAL:
			return (original >= condition);
		case SMALLER:
			return (original < condition);
		case SMALLER_OR_EQUAL:
			return (original <= condition);
		case EQUAL:
			return (original == condition);
		case NOT_EQUAL:
			return (original != condition);
		default:
			throw new AssertionError("Unknown condition " + this);
		}
	}

	public static ArrayList<String> getConditionOperationList() {
		ArrayList<String> list = new ArrayList<String>();
		ConditionOperation[] operations = ConditionOperation.values();
		for (ConditionOperation operation : operations) {
			list.add(operation.toString());
		}
		return list;
	}

	public static ArrayList<String> getConditionOperationListForEqualTest() {
		ArrayList<String> list = (ArrayList<String>) Arrays.asList(EQUAL.toString(), NOT_EQUAL.toString());
		return list;
	}
}