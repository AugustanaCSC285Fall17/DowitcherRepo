package edu.augustana.csc285.game.datamodel;

import java.util.List;

public class SubsetCondition implements Condition {
	private List<Condition> conditions;
	private int numberOfSatisfied;

	@Override
	public boolean checkCondition(Player player) {
		if (numberOfSatisfied < 0 || numberOfSatisfied > conditions.size()) {
			throw new IllegalArgumentException("Number Of Satisfied: " + numberOfSatisfied + " is invalid");
		}
		int temp = 0;
		for (Condition condition : conditions) {
			if (condition.checkCondition(player)) {
				temp++;
			}
		}
		if (temp >= numberOfSatisfied) {
			return true;
		} else {
			return false;
		}
	}

	public void addCondition(Condition condition) {
		conditions.add(condition);
	}

	public String toString() {
		// To do note: finish implementation
		return "";
	}

}
