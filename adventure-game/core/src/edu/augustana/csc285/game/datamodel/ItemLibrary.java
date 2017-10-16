package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.TreeMap;

public class ItemLibrary {
	// THe map between item name and item with quantity 1
	TreeMap<String, Item> itemMap = new TreeMap<String, Item>();

	public ItemLibrary() {

	}

	public Item getItem(String itemName, int quantity) {
		itemName = itemName.toLowerCase();
		if (!itemMap.containsKey(itemName)) {
			throw new IllegalArgumentException("The item is not in the library");
		}
		return new Item(itemMap.get(itemName), quantity);
	}

	/**
	 * 
	 * @param itemName:
	 *            the string representing the item name, case insensitive
	 * @return: The corresponding item with default quantity 1
	 */
	public Item getItem(String itemName) {
		return getItem(itemName, 1);
	}

	/**
	 * 
	 * @param itemName:
	 *            the string representing the item name, case insensitive
	 * @param description:
	 *            String description of the item
	 * @param image:
	 *            String to represent the location of assest
	 */
	public void addItem(String itemName, String description, String image) {
		itemName = itemName.toLowerCase();
		Item item = new Item(itemName, description, image);
		itemMap.put(itemName, item);
	}

	/**
	 * 
	 * @return: the list of item name
	 */
	public ArrayList<String> getItemNameList() {
		return new ArrayList<String>(itemMap.keySet());
	}
}
