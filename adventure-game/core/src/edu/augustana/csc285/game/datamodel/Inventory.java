package edu.augustana.csc285.game.datamodel;

/**
 * author: Dat Tran
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	// Need to initialize in here so can have 0 argument constructor
	private HashMap<Integer, Item> collection = new HashMap<Integer, Item>();

	public Inventory(HashMap<Integer, Item> collection) {
		setCollection(collection);
	}

	public Inventory(Inventory other) {
		this.collection = other.collection;
	}

	public Inventory() {
	}

	public Map<Integer, Item> getCollection() {
		return collection;
	}

	public void setCollection(HashMap<Integer, Item> collection) {
		this.collection = (HashMap<Integer, Item>) collection;
	}

	/*
	 * post: add item to the inventory
	 */
	public void addItem(Item item) {
		Item temp = collection.get(item.getID());
		// if item already exist in Inventory
		if (temp != null) {
			temp.addItem(item);
			collection.put(temp.getID(), temp);
		} else {
			collection.put(item.getID(), new Item(item));
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
		collection.put(temp.getID(), temp);
	}

	// return the quantity of an item, if the item is not in the collection then
	// return 0
	public int getItemQuantity(int itemID) {
		Item temp = collection.get(itemID);
		if (temp == null) {
			return 0;
		} else {
			return temp.getQuantity();
		}
	}

	/*
	 * post: remove item from the inventory, make no change if the item is not
	 * in collection
	 */
	public void removeItem(Item item) {
		collection.remove(item.getID());
	}

	/*
	 * post: add Inventory other to this inventory if inventory other is empty,
	 * do nothing
	 */
	public void addInventory(Inventory other) {
		if (other != null && other.collection != null) {
			for (int id : other.collection.keySet()) {
				Item temp1 = collection.get(id);
				Item temp2 = other.collection.get(id);
				if (temp1 != null) {
					temp1.addItem(temp2);
				} else {
					temp1 = temp2;
				}
				collection.put(id, temp1);
			}
		}
	}

	/*
	 * post: subtract Inventory other from this do nothing if other is empty
	 * 
	 * @ throw: IllegalArgumentException if there is at least one item with not
	 * enough quantity
	 * 
	 */
	public void subtractInventory(Inventory other) {
		if (!checkInventory(other)) {
			throw new IllegalArgumentException("There is not enough items");
		} else {
			if (other != null && other.collection != null) {
				for (int id : other.collection.keySet()) {
					Item temp1 = collection.get(id);
					Item temp2 = other.collection.get(id);
					temp1.subtractItem(temp2);
					collection.put(id, temp1);
				}
			}
		}
	}

	/*
	 * @return: true if this has greater quantity in every item than Inventory
	 * other, also true if Inventory other is null
	 */
	public boolean checkInventory(Inventory other) {
		if (other != null) {
			for (int id : other.collection.keySet()) {
				Item temp1 = collection.get(id);
				Item temp2 = other.collection.get(id);
				if (temp1 == null) {
					return false;
				} else {
					if (!temp1.compareQuantity(temp2)) {
						return false;
					}
				}
			}
			return true;
		}
		return true;
	}

	public String toString() {
		ArrayList<String> str = new ArrayList<String>();
		for (Item item : collection.values()) {
			str.add("Name: " + item.getName() + " Q: " + item.getQuantity() + " ID: " + item.getID());
		}
		return str.toString();
	}
}
