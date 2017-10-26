package edu.augustana.csc285.game.datamodel.tester;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.augustana.csc285.game.datamodel.Inventory;
import edu.augustana.csc285.game.datamodel.Item;

/** To test the functionality of Items and inventories and their behavior
 * 
 * @author Daniel
 *
 */
public class ItemTester {

	//creates some items
	Item bible;
	Item book;
	Item bird;
	Item emu;
	//setup a set that will store items for testing.
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
		assertEquals(emu, emu);
		assertNotEquals(emu, bird);
		assertEquals(bird, bird);
		assertNotEquals(bird, bible);
		assertEquals(bird.getDesc(), emu.getDesc());
		assertNotEquals(bird.getQuantity(), bible.getQuantity());
		assertEquals(bird.getQuantity(), emu.getQuantity());

	}

	@Test
	public void testInvEquals() {
		Inventory inv1 = new Inventory();
		Inventory inv2 = new Inventory(inv1.getCollection());
		bible = new Item("Bible", "A Holy book", 3);
		book = new Item("Book", "A normal book", 1);
		bird = new Item("Bird", "A wild bird", 2);
		emu = new Item("Emu", "A wild bird", 2);

		inv1.addItem(emu);
		// inv2.addInventory(inv1);
		Set<Item> list1 = inv1.getCollection();
		Set<Item> list2 = inv2.getCollection();

		assertEquals(list1, list2);
		
		inv1.addItem(emu);
		list1 = inv1.getCollection();
		
		//Failure, cannot add an item's quantity by simply adding an item with quantity
		// 2
		assertNotEquals(list1, list2);

		inv2.addItem(emu);
		list2 = inv2.getCollection();
		// This test run successfully because neither of the lists were modified when
		// adding an item
		assertEquals(list1, list2);

		inv2.addItem(bible);
		list2 = inv2.getCollection();

		assertNotEquals(list1, list2);
		System.out.println(inv2);

	}

}
