package edu.augustana.csc285.game.datamodel.effect;

import java.util.ArrayList;

public enum EffectOperation {
	PLUS, MINUS, TIMES;
	// change should always be positive, original should always be non negative
	public int applyEffect(int original, int change) {
		switch (this) {
		case PLUS:
			return original + change;
		case MINUS:
			int temp = original - change;
			if (temp < 0) {
				temp = 0;
			}
			return temp;
		case TIMES:
			return original * change;
		default:
			throw new AssertionError("Unknown effect " + this);
		}
	}

	public static ArrayList<String> getEffectOperationList() {
		ArrayList<String> list = new ArrayList<String>();
		EffectOperation[] operations = EffectOperation.values();
		for (EffectOperation operation : operations) {
			list.add(operation.toString());
		}
		return list;
	}

}