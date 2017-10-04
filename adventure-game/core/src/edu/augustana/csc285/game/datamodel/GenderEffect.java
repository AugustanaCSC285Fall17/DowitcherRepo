package edu.augustana.csc285.game.datamodel;

public class GenderEffect implements Effect{
	private Gender gender;
	
	public GenderEffect(Gender gender) {
		this.gender = gender;
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
	
	
	
}
