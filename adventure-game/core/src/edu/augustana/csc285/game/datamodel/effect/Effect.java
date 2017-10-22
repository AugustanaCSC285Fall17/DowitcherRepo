package edu.augustana.csc285.game.datamodel.effect;
import edu.augustana.csc285.game.datamodel.Player;

public interface Effect {
	public void applyEffect(Player player);
	public String getEffectType();
}
