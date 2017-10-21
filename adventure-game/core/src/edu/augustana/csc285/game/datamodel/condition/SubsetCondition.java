package edu.augustana.csc285.game.datamodel.condition;

import java.util.ArrayList;
import java.util.List;

import edu.augustana.csc285.game.datamodel.Player;

public class SubsetCondition implements Condition {
	private ArrayList<Condition> conditions = new ArrayList<Condition>();
	private int numberOfSatisfied;
	private String conditionType = "Subset Condition";

	public SubsetCondition() {

	}

	public SubsetCondition(ArrayList<Condition> conditions, int numberOfSatisfied) {
		this.conditions = conditions;
		this.numberOfSatisfied = numberOfSatisfied;
	}

	public SubsetCondition(int numberOfSatisfied) {
		this.numberOfSatisfied = numberOfSatisfied;
	}

	public int getNumberOfSatisfied() {
		return numberOfSatisfied;
	}

	public void setNumberOfSatisfied(int numberOfSatisfied) {
		this.numberOfSatisfied = numberOfSatisfied;
	}

	public ArrayList<Condition> getConditions() {
		return conditions;
	}

	public String getConditionType() {
		return conditionType;
	}

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
