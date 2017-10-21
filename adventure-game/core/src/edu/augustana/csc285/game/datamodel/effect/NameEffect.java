package edu.augustana.csc285.game.datamodel.effect;

import edu.augustana.csc285.game.datamodel.Player;

public class NameEffect implements Effect {
	private String name;
	private String effectType = "Name Effect";

	public NameEffect() {

	}

	public NameEffect(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEffectType() {
		return effectType;
	}

	@Override
	public void applyEffect(Player player) {
		player.setName(name);
	}

	public String toString() {
		return effectType + ": " + name;
	}

}
