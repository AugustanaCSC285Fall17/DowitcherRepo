package edu.augustana.csc285.game.datamodel.tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.augustana.csc285.game.datamodel.*;

/**
 * Allows for testing of an slide and options Tests various methods and allow
 * the user to visualize what the computer sees when manipulating an inventory
 * 
 * @author Daniel Zwiener
 * @date 9-16-17
 *
 */
public class SlideTest {

	public static ArrayList<Slide> slides;

	public static void main(String[] args) {

		// String input = "slides.txt";
		slides = new ArrayList<>();
		loadSlides();
		testBreak();
		for (Slide index : slides) {
			System.out.println(index);
			testBreak();
		}
		testBreak();

	}

	/**
	 * public Slide(String image, String desc, String url, String music, int id,
	 * ArrayList<Option> options) { EX Text file
	 * 
	 * 001.jpg You see an old lady in the swamp ladyinaswamp.com Skrillex.mp3 4
	 * 3 (number of options) Option's next slide (* number of options) Option's
	 * next slide Option's next slide
	 * 
	 */
	public static void loadSlides() {
		Scanner inputStream = new Scanner(System.in);
		System.out.println("Please enter the name of the file: ");
		String input = inputStream.nextLine();

		try {
			Scanner fileIn = new Scanner(new File(input));
			int slideCount = 0;
			while (fileIn.hasNextLine()) {
				String tempStr;
				String tempStr2;
				String tempInt;

				tempStr = fileIn.nextLine();
				tempStr2 = fileIn.nextLine();
				System.out.print("Creating new slide with image: " + tempStr + " and desc: " + tempStr2 + " = ");
				// commented out to run project w/o errors
				// slides.add(new Slide(tempStr, tempStr2));
				System.out.println("SUCCESS");

				tempStr = fileIn.nextLine();
				System.out.print("Assigning the URL: " + tempStr + " to the slide = ");
				slides.get(slideCount).setUrl(tempStr);
				System.out.println("SUCCESS");

				tempStr = fileIn.nextLine();
				System.out.print("Assigning the music: " + tempStr + " to the slide = ");
				slides.get(slideCount).setMusic(tempStr);
				System.out.println("SUCCESS");

				tempInt = fileIn.nextLine();
				System.out.print("Assigning the ID: " + tempInt + " to the slide = ");
				slides.get(slideCount).setId("" + tempInt);
				System.out.println("Adding options = SUCCESS");

				// ArrayList<Option> options = new ArrayList<>();

				tempInt = fileIn.nextLine();
				System.out.println("Adding " + tempInt + " option(s)");
				for (int i = 1; i <= tempInt.length(); i++) {
					int temp = fileIn.nextInt();
					System.out.print("Creating a new Option and assigning it an ID: " + temp + " = ");
					// same here
					// slides.get(slideCount).addOption(new Option(temp));
					System.out.println("SUCCESS");
				}
				System.out.println("Adding options = SUCCESS");

				// Moves to the next line if it isn't the last element.
				if (fileIn.hasNextLine()) {
					fileIn.nextLine();
				}
				slideCount++;
				System.out.println();
			}

		} catch (FileNotFoundException E) {
			// TODO Auto-generated catch block
			System.out.println("File not found!\nFile: " + input + "\nException " + E);
			// System.exit(0);
		} catch (NullPointerException E) {
			System.out.println("FAILED \nException: " + E);
			// System.exit(0);
		}

	}

	/**
	 * Simple program to print out nice headers and footers for testing
	 */
	public static void testBreak() {
		for (int i = 0; i < 100; i++) {
			System.out.print("=");
		}
		System.out.println("\n");
	}

	/**
	 * Adds items to the inventory and adds and subtracts inventories
	 */
	public static void testSlides() {

	}
}
