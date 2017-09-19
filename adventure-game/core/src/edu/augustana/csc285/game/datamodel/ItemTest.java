package edu.augustana.csc285.game.datamodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Allows for testing of an inventory and items
 * Tests various methods and allow the user to visualize
 * what the computer sees when manipulating an inventory
 * 
 * @author Daniel Zwiener
 * @date 9-16-17
 *
 */
public class ItemTest {

	public static ArrayList<Item> items;
	
	public static void main(String[] args) {

		//String input = "items.txt";
		items = new ArrayList<>();
		loadItems();
		
		testBreak();
		System.out.println("Checking if items are the same type");
		
		for(int i = 0; i < items.size()-1; i++) {
			Item target = items.get(i);
			Item next = items.get(i + 1);
			
			System.out.println("\n\nFirst Item\nName: " + target.getName() + " description: " + target.getDesc() + " Quantity: " + target.getQuantity() + " Id: " + target.getID());
			System.out.println("Second Item\nName: " + next.getName() + " description: " + next.getDesc() + " Quantity: " + next.getQuantity() + " Id: " + next.getID());
			
			System.out.println("Checking if " + target.getName() + " equals " + next.getName());

			System.out.println(target.sameType(next));
		}
		
		testBreak();
		testInventory();
		testBreak();
		
	}
	
	/**
	 * Imports a file of items into the testing program. File must look like:
	 * (Name)
	 * (Description)
	 * (Quantity)
	 * (Id)
	 * 
	 * Ex:
	 * Apple
	 * A Food item that restores 5HP
	 * 5
	 * 1
	 * 
	 */
	public static void loadItems() {
		Scanner inputStream = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String input = inputStream.nextLine();
		
		try {
			Scanner fileIn = new Scanner(new File(input));
			while(fileIn.hasNextLine()) {
				items.add(new Item(fileIn.nextLine(), fileIn.nextLine(), 
						fileIn.nextInt(), fileIn.nextInt(), null));
				
				//Moves to the next line if it isn't the last element.
				if(fileIn.hasNextLine()) {
				fileIn.nextLine();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found! \n File: " + input + "\nException " + e);
		}
		
	}
	
	/**
	 * Simple program to print out nice headers and footers for testing
	 */
	public static void testBreak() {
		for(int i = 0; i < 100; i++) {
			System.out.print("=");
		}
		System.out.println("\n");
	}
	
	/**
	 * Adds items to the inventory and adds and subtracts inventories
	 */
	public static void testInventory() {
		Inventory inv1 = new Inventory();
		Inventory inv2 = new Inventory();
		System.out.println("Testing inventory addItem() and toString()");
		
		Item temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv1");
		inv1.addItem(temp);
		
		temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv1");
		inv1.addItem(temp);
		
		temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv1");
		inv1.addItem(temp);
		
		System.out.println(inv1);
		
		
		temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv2");
		inv2.addItem(temp);
		
		temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv2");
		inv2.addItem(temp);
		
		temp = items.get((int)(Math.random() * items.size()));
		System.out.println("Adding " + temp.getName() + " to inv2");
		inv2.addItem(temp);
		
		System.out.println(inv2);
		
		testBreak();
		
		//This is not working yet!!!
		System.out.println("Adding these two inventories together");
		inv1.addInventory(inv2);
		System.out.println(inv1);
		
		System.out.println("Subtracting inv2 from inv1");
		inv1.subtractInventory(inv2);
		System.out.println(inv1);
		
		testBreak();
		
	}
}
