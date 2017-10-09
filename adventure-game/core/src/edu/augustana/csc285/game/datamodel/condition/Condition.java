package edu.augustana.csc285.game.datamodel.condition;

import edu.augustana.csc285.game.datamodel.Player;

public interface Condition {
	public boolean checkCondition(Player player);
	public String toString(); // add to remind to include in implementation
}