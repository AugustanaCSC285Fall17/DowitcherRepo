package edu.augustana.csc285.game.datamodel.effect;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.Player;

public class ItemEffect implements Effect {
	private Item item;
	private EffectOperation operation;

	public ItemEffect() {

	}

	public ItemEffect(Item item, EffectOperation operation) {
		this.item = item;
		this.operation = operation;
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
		return (operation + " with item " + item);
	}

}
