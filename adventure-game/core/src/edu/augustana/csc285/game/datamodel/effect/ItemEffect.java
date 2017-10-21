package edu.augustana.csc285.game.datamodel.effect;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;

public class ItemEffect implements Effect {
	private Item item;
	private EffectOperation operation;
	private String effectType = "Item Effect";

	public ItemEffect() {

	}

	public ItemEffect(Item item, EffectOperation operation) {
		this.item = item;
		this.operation = operation;
	}

	public String getEffectType() {
		return effectType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public EffectOperation getOperation() {
		return operation;
	}

	public void setOperation(EffectOperation operation) {
		this.operation = operation;
	}

	public void applyEffect(Player player) {
		int currentQuantity = player.getInventory().getItemQuantity(item);
		int newQuantity = operation.applyEffect(currentQuantity, item.getQuantity());
		player.getInventory().changeItemQuantity(item, newQuantity);
	}

	public String toString() {
		return effectType + ": " + item.getName() + " (qty: " + item.getQuantity() + ") with Operation: " + operation;
	}
}
