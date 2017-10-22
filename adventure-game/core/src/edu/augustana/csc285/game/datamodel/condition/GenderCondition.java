package edu.augustana.csc285.game.datamodel.condition;

import edu.augustana.csc285.game.datamodel.Gender;
import edu.augustana.csc285.game.datamodel.Player;

public class GenderCondition implements Condition {

	private Gender gender;
	private String conditionType = "Gender Condition";

	public GenderCondition() {
	}

	public GenderCondition(Gender gender) {
		this.gender = gender;
	}

	// Only going to support EQUAL and NOT_EQUAL
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getConditionType() {
		return conditionType;
	}

	@Override
	public boolean checkCondition(Player player) {
		return player.getGender() == gender;
	}

	public String toString() {
		return conditionType + ": " + gender;
	}

}
