package edu.augustana.csc285.game.datamodel;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class ItemTester {

	Item bible;
	Item book;
	Item bird;
	Set<Item> items;
	
	public void createInventory() {
		bible = new Item("Bible", "A Holy book", 3);
		book = new Item("Book", "A normal book", 1);
		bird = new Item("Bird", "A wild bird", 2);
		
		Inventory inv = new Inventory();
		System.out.println(inv);
		inv.addItem(bible);
		inv.addItem(book);
		//inv.changeItemQuantity(b, newQuantity);
		System.out.println(inv);
		
		items = inv.getCollection();
		
		System.out.println(items.contains(book));
		System.out.println(items.contains(bible));
		System.out.println(items.contains(bird));
	}
	@Test
	public void test() {
		createInventory();
		
	}

}
