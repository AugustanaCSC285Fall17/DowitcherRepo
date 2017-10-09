package edu.augustana.csc285.game.datamodel.effect;

import edu.augustana.csc285.game.datamodel.Player;
import edu.augustana.csc285.game.datamodel.Property;

public class PropertyEffect implements Effect {
	private Property property;
	private EffectOperation operation;

	public PropertyEffect() {

	}

	public PropertyEffect(Property property, EffectOperation operation) {
		this.property = property;
		this.operation = operation;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public EffectOperation getOperation() {
		return operation;
	}

	public void setOperation(EffectOperation operation) {
		this.operation = operation;
	}

	public void applyEffect(Player player) {
		int currentQuantity = player.getProperties().getPropertyQuantity(property.getID());
		int newQuantity = operation.applyEffect(currentQuantity, property.getQuantity());
		player.getProperties().changePropertyQuantity(property, newQuantity);
	}

	public String toString() {
		return (operation + " with property " + property);
	}

}
