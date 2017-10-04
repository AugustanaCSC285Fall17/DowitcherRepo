package edu.augustana.csc285.game.datamodel;

public class GenderCondition implements Condition{

	private int gender;
	
	public GenderCondition(int gender) {
		this.gender = gender;
	}
	
	@Override
	public boolean checkCondition(Player player) {
		if(player.getProperties().getProperty(PropertyType.GENDER.getID()).getQuantity() == gender) {
			return true;
		}
		return false;
	}

}
