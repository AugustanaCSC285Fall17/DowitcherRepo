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
	private TreeSet<Item> collection = new TreeSet<Item>(new StringComp());

	public Inventory() {
	}

	public Inventory(TreeSet<Item> collection) {
		setCollection(collection);
	}

	public Inventory(Inventory other) {
		this.collection = other.collection;
	}

	public TreeSet<Item> getCollection() {
		return collection;
	}

	public void setCollection(TreeSet<Item> collection) {
		this.collection = (TreeSet<Item>) collection;
	}

	/*
	 * post: add item to the inventory
	 */
	public void addItem(Item item) {
		Item temp = this.findItem(item);
		if (temp != null) {
			temp.addItem(item);
		}
	}

	// precondition that collection is not null
	// add Item in condition with new quantity to the collection if newQuantity
	// <0 then set to 0
	public void changeItemQuantity(Item item, int newQuantity) {
		if (newQuantity < 0) {
			newQuantity = 0;
		}
		Item temp = new Item(item);
		temp.setQuantity(newQuantity);
		collection.add(temp);
	}

	// return the quantity of an item, if the item is not in the collection then
	// return 0
	public int getItemQuantity(Item item) {
		Iterator<Item> itr = this.collection.iterator();
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
		collection.remove(item);
	}

	/*
	 * post: add Inventory other to this inventory if inventory other is empty,
	 * do nothing
	 */
	public void addInventory(Inventory other) {
		Iterator<Item> itr = this.collection.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			if (other.collection.contains(item)) {
				Item temp = other.findItem(item);
				item.addItem(temp);
			}
		}
	}

	public String toString() {
		String str = "";
		for (Item item : this.collection) {
			if (item.getQuantity() != 0) {
				str += item + "\n";
			}
		}
		return str;
	}

	// Find item with matched name in the inventory, return null if nothing was
	// found
	public Item findItem(Item other) {
		Iterator<Item> itr = this.collection.iterator();
		while (itr.hasNext()) {
			Item item = itr.next();
			if (item.equals(other)) {
				return item;
			}
		}
		return null;
	}
}
