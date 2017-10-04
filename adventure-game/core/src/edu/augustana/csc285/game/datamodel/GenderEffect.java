package edu.augustana.csc285.game.datamodel;

public class GenderEffect implements Effect{
	private int gender;
	
	public GenderEffect(int gender) {
		this.gender = gender;
	}
	@Override
	public void applyEffect(Player player) {
		player.getProperties().getProperty(PropertyType.GENDER.getID()).setQuantity(gender);
	}
	/**
	 * @return the gender
	 */
	public int getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	
}
