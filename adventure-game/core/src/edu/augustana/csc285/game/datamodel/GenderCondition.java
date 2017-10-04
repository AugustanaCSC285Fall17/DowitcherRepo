package edu.augustana.csc285.game.datamodel;

public class GenderCondition implements Condition {

	private Gender gender;

	public GenderCondition(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean checkCondition(Player player) {
		if (player.getGender() == gender) {
			return true;
		}
		return false;
	}

}
