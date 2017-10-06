package edu.augustana.csc285.game.datamodel;

public class NameEffect implements Effect {
	private String name;

	public NameEffect() {

	}

	public NameEffect(String name) {
		this.name = name;
	}

	@Override
	public void applyEffect(Player player) {
		// TODO Auto-generated method stub
		player.setName(name);
	}

}
