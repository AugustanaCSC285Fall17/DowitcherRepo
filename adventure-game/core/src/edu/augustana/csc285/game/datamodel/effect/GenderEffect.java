package edu.augustana.csc285.game.datamodel.effect;

import edu.augustana.csc285.game.datamodel.Gender;
import edu.augustana.csc285.game.datamodel.Player;

public class GenderEffect implements Effect {
	private Gender gender;
	private String effectType = "Gender Effect";

	public GenderEffect() {

	}

	public GenderEffect(Gender gender) {
		this.gender = gender;
	}

	public String getEffectType() {
		return effectType;
	}

	@Override
	public void applyEffect(Player player) {
		player.setGender(gender);
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String toString() {
		return effectType + ": " + gender;
	}

}
