package edu.augustana.csc285.game.datamodel.condition;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;

public class ItemCondition implements Condition {
	private Item item;
	private ConditionOperation operation;
	private String conditionType = "Item Condition";

	public ItemCondition() {

	}

	public ItemCondition(Item item, ConditionOperation operation) {
		this.item = item;
		this.operation = operation;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getConditionType() {
		return conditionType;
	}

	public ConditionOperation getOperation() {
		return operation;
	}

	public void setOperation(ConditionOperation operation) {
		this.operation = operation;
	}

	public boolean checkCondition(Player player) {
		int currentQuantity = player.getInventory().getItemQuantity(item);
		return operation.checkCondition(currentQuantity, item.getQuantity());
	}

	public String toString() {
		return conditionType + ": " + item.getName() + " (qty: " + item.getQuantity() + ") with Operation: "
				+ operation;
	}
}
