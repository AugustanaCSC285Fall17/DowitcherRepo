package edu.augustana.csc285.game.datamodel;

/**
 * author: Dat Tran
 */

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Inventory {
	class StringComp implements Comparator<Item> {
		@Override
		public int compare(Item o1, Item o2) {
			return o1.getName().compareTo(o2.getName());
		}
	}

	// Need to initialize in here so can have 0 argument constructor
	private TreeSet<Item> inventory = new TreeSet<Item>(new StringComp());

	// cannot have calls inside of this constructor
	public Inventory() {
	}

	public Inventory(TreeSet<Item> collection) {
		setCollection(collection);
	}

	public Inventory(Inventory other) {
		this.inventory = other.inventory;
	}

	public TreeSet<Item> getCollection() {
		return inventory;
	}

	public void setCollection(TreeSet<Item> collection) {
		this.inventory = (TreeSet<Item>) collection;
	}

	/*
	 * post: add item to the inventory
	 */
	public void addItem(Item item) {
		Item temp = this.findItem(item);
		if (temp != null) {
			temp.setQuantity(item.getQuantity() + temp.getQuantity());
		} else {
			inventory.add(item);
		}
	}

	// precondition that collection is not null
	// add Item in condition with new quantity to the collection if newQuantity
	// < 0 then set to 0
	public void changeItemQuantity(Item item, int newQuantity) {
		if (newQuantity < 0) {
			newQuantity = 0;
		}
		Item temp = new Item(item, newQuantity);
		inventory.remove(temp);
		inventory.add(temp);
	}

	// return the quantity of an item, if the item is not in the collection then
	// return 0
	public int getItemQuantity(Item item) {
		Iterator<Item> itr = this.inventory.iterator();
		while (itr.hasNext()) {
			Item temp = itr.next();
			if (temp.equals(item)) {
				return temp.getQuantity();
			}
		}
		return 0;
	}

	/*
	 * post: remove item from the inventory, make no change if the item is not in
	 * collection
	 */
	public void removeItem(Item item) {
		inventory.remove(item);
	}

	/*
	 * post: add Inventory other to this inventory if inventory other is empty, do
	 * nothing
	 */
	public void addInventory(Inventory other) {
		Iterator<Item> itr = this.inventory.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			if (other.inventory.contains(item)) {
				Item temp = other.findItem(item);
				item.addItem(temp);
			}
		}
	}

	public String toString() {
		String str = "";
		for (Item item : this.inventory) {
			if (item.getQuantity() != 0) {
				str += item + "\n";
			}
		}
		return str;
	}

	// Find item with matched name in the inventory, return null if nothing was
	// found
	public Item findItem(Item other) {
		Iterator<Item> itr = this.inventory.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			if (item.equals(other)) {
				return item;
			}
		}
		return null;
	}

	/*
	 * public void addDefaultItem() { Item sek = new Item("Sek",
	 * "Currency of Sweden", 1000, null); Item dollar = new Item("Dollar",
	 * "Currency of USA", 1000, null); collection.add(sek); collection.add(dollar);
	 * }
	 */
}
