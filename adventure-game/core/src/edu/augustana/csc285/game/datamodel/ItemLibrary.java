package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.TreeMap;

import com.badlogic.gdx.utils.Json;

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

	public void addItem(Item item) {
		itemMap.put(item.getName(), item);
	}

	/**
	 * 
	 * @return: the list of item name
	 */
	public ArrayList<String> getItemNameList() {
		return new ArrayList<String>(itemMap.keySet());
	}

	public String toJSON() {
		return new Json().prettyPrint(this);
	}

	public static ItemLibrary fromJSON(String jsonData) {
		return new Json().fromJson(ItemLibrary.class, jsonData);
	}

	public static void main(String[] args) {
		ItemLibrary itemLibrary = new ItemLibrary();
		Item sek = new Item("Sek", "Currency of Sweden", 1, null);
		Item dollar = new Item("Dollar", "Currency of USA", 1, null);
		Item medicine = new Item("Medicine", null, 1, null);
		Item jDLetter = new Item("John Deere Letter", "A letter from John Deere", 1, "GameData/SlideImages/item5.jpg");
		Item iLetter = new Item("Illinois Letter", "A letter from Illinois", 1, "GameData/SlideImages/item6.jpg");
		Item wLetter = new Item("Washington Letter", "A letter from Washington", 1, "GameData/SlideImages/item7.jpg");
		Item mLetter = new Item("Minnesota Letter", "A letter from Minnesota", 1, "GameData/SlideImages/item8.jpg");
		Item officialPapers = new Item("Official papers", null, 1, null);
		Item forgedPapers = new Item("Forged papers", null, 1, null);
		Item bible = new Item("Bible", null, 1, null);
		Item familyHeirloom = new Item("Family Heirloom", null, 1, null);
		Item sewingMachine = new Item("Sewing Machine", null, 1, null);
		Item curedMeat = new Item("Cured Meat", null, 1, null);
		Item ticketToHull = new Item("Ticket to Hull", null, 1, null);
		Item ticketToAmerica = new Item("Ticket to America", null, 1, null);
		itemLibrary.addItem(sek);
		itemLibrary.addItem(dollar);
		itemLibrary.addItem(medicine);
		itemLibrary.addItem(jDLetter);
		itemLibrary.addItem(iLetter);
		itemLibrary.addItem(wLetter);
		itemLibrary.addItem(mLetter);
		itemLibrary.addItem(officialPapers);
		itemLibrary.addItem(forgedPapers);
		itemLibrary.addItem(bible);
		itemLibrary.addItem(familyHeirloom);
		itemLibrary.addItem(sewingMachine);
		itemLibrary.addItem(curedMeat);
		itemLibrary.addItem(ticketToHull);
		itemLibrary.addItem(ticketToAmerica);
		System.out.println(itemLibrary.toJSON());
	}
}
