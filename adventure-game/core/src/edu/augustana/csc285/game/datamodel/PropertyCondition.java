package edu.augustana.csc285.game.datamodel;

public class PropertyCondition implements Condition {
	private Property property;
	private ConditionOperation operation;

	public PropertyCondition(Property property, ConditionOperation operation) {
		this.property = property;
		this.operation = operation;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public ConditionOperation getOperation() {
		return operation;
	}

	public void setOperation(ConditionOperation operation) {
		this.operation = operation;
	}

	public boolean checkCondition(Player player) {
		int currentQuantity = player.getProperties().getPropertyQuantity(property.getID());
		return operation.checkCondition(currentQuantity, property.getQuantity());
	}

}