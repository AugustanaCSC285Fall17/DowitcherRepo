package edu.augustana.csc285.game.datamodel.tester;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.augustana.csc285.game.datamodel.Inventory;
import edu.augustana.csc285.game.datamodel.Item;

public class ItemTester {

	Item bible;
	Item book;
	Item bird;
	Item emu;
	Set<Item> items;
	
	@Test
	public void testContains() {
		
		Inventory inv = new Inventory();
		bible = new Item("Bible", "A Holy book", 3);
		book = new Item("Book", "A normal book", 1);
		bird = new Item("Bird", "A wild bird", 2);
		emu = new Item("Emu", "A wild bird", 2);
		
		inv.addItem(bible);
		inv.addItem(book);
		
		items = inv.getCollection();
		
		assertTrue(items.contains(book));
		assertTrue(items.contains(bible));
		assertFalse(items.contains(bird));
		
		
	}
	
	@Test
	public void testItemEquals() {
		bible = new Item("Bible", "A Holy book", 3);
		book = new Item("Book", "A normal book", 1);
		bird = new Item("Bird", "A wild bird", 2);
		emu = new Item("Emu", "A wild bird", 2);
		assertTrue(emu.equals(emu));
		assertFalse(emu.equals(bird));
		assertTrue(bird.equals(bird));
		assertFalse(bird.equals(bible));
	}
	
	@Test
	public void testInvEquals() {
		Inventory inv1 = new Inventory();
		Inventory inv2 = new Inventory();
		bible = new Item("Bible", "A Holy book", 3);
		book = new Item("Book", "A normal book", 1);
		bird = new Item("Bird", "A wild bird", 2);
		emu = new Item("Emu", "A wild bird", 2);
		
		inv1.addItem(emu);
		inv2.addInventory(inv1);
		
		assertTrue(inv1.equals(inv2));
		
		inv1.addItem(emu);
		
		assertFalse(inv1.equals(inv2));
		
		inv2.addItem(emu);
		
		assertTrue(inv1.equals(inv2));
		
		inv2.addItem(bible);
		
		assertFalse(inv1.equals(inv2));
		
	}
	
	@Test
	public void test() {

		
		
		testContains();
		testItemEquals();
		
	}

}
