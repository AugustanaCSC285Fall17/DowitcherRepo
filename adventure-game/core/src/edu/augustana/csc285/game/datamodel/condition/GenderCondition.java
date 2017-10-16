package edu.augustana.csc285.game.datamodel.condition;

import edu.augustana.csc285.game.datamodel.Gender;
import edu.augustana.csc285.game.datamodel.Player;

public class GenderCondition implements Condition {

	private Gender gender;

	public GenderCondition() {
	}

	public GenderCondition(Gender gender) {
		this.gender = gender;
	}

	// Only going to support EQUAL and NOT_EQUAL
	@Override
	public boolean checkCondition(Player player) {
		if (player.getGender() == gender) {
			return true;
		}
		return false;
	}

}
