package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;

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
	private TreeSet<Item> itemSet = new TreeSet<Item>(new StringComp());

	// cannot have calls inside of this constructor
	public Inventory() {
	}

	public Inventory(TreeSet<Item> collection) {
		setCollection(collection);
	}

	public Inventory(Inventory other) {
		this.itemSet = other.itemSet;
	}

	public TreeSet<Item> getCollection() {
		return itemSet;
	}

	public void setCollection(TreeSet<Item> collection) {
		this.itemSet = (TreeSet<Item>) collection;
	}

	/*
	 * post: add item to the inventory
	 */
	public void addItem(Item item) {
		if (itemSet.contains(item)) {
			Item itemStored = this.findItem(item);
			itemStored.setQuantity(item.getQuantity() + itemStored.getQuantity());
		} else {
			itemSet.add(item);
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
		itemSet.remove(temp);
		itemSet.add(temp);
	}

	// return the quantity of an item, if the item is not in the collection then
	// return 0
	public int getItemQuantity(Item item) {
		Iterator<Item> itr = this.itemSet.iterator();
		while (itr.hasNext()) {
			Item temp = itr.next();
			if (temp.equals(item)) {
				return temp.getQuantity();
			}
		}
		return 0;
	}

	/*
	 * post: remove item from the inventory, make no change if the item is not
	 * in collection
	 */
	public void removeItem(Item item) {
		itemSet.remove(item);
	}

	/*
	 * post: add Inventory other to this inventory if inventory other is empty,
	 * do nothing
	 */
	public void addInventory(Inventory other) {
		Iterator<Item> itr = this.itemSet.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			if (other.itemSet.contains(item)) {
				Item temp = other.findItem(item);
				item.addItem(temp);
			}
		}
	}

	public String toString() {
		String str = "";
		for (Item item : this.itemSet) {
			if (item.getQuantity() != 0) {
				str += item + "\n";
			}
		}
		return str;
	}

	public ArrayList<Item> getVisibleItemList() {
		ArrayList<Item> list = new ArrayList<Item>();
		for (Item item : this.itemSet) {
			if (item.getQuantity() > 0 && item.isVisible()) {
				list.add(item);
			}
		}
		return list;
	}

	// Find item with matched name in the inventory, return null if nothing was
	// found
	public Item findItem(Item other) {
		Iterator<Item> itr = this.itemSet.iterator();
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
	 * "Currency of USA", 1000, null); collection.add(sek);
	 * collection.add(dollar); }
	 */
}