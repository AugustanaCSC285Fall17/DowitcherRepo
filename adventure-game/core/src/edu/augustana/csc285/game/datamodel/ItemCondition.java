package edu.augustana.csc285.game.datamodel;

public class ItemCondition implements Condition {
	private Item item;
	private ConditionOperation operation;

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

	public ConditionOperation getOperation() {
		return operation;
	}

	public void setOperation(ConditionOperation operation) {
		this.operation = operation;
	}

	public boolean checkCondition(Player player) {
		int currentQuantity = player.getInventory().getItemQuantity(item.getID());
		return operation.checkCondition(currentQuantity, item.getQuantity());
	}

	public String toString() {
		return (operation + " with item " + item);
	}
}
