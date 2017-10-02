package edu.augustana.csc285.game.datamodel;

public class GameDataTester {

	public static void main(String[] args) {
		GameData gd = new GameData();
		/*
		 * Slide s0 = new Slide("Room 0", "A boring room", "slide_000.png");
		 * ActionChoice choice = new ActionChoice("go to room 1 and get bananas", 1);
		 * choice.addEffect(new InventoryEffect("bananas", 42));
		 * s0.getActionChoices().add(choice); s0.getActionChoices().add(new
		 * ActionChoice("go to room 2", 2)); Slide s1 = new Slide("Room 1",
		 * "A fascinating room", "slide_001.png"); s1.getActionChoices().add(new
		 * ActionChoice("die", -1)); s1.getActionChoices().add(new
		 * ActionChoice("go to room 2", 2)); Slide s2 = new Slide("Room 2",
		 * "An emu farm", "slide_002.png"); s2.getActionChoices().add(new
		 * ActionChoice("go back to room 0", 0));
		 */
		Slide s1 = new Slide("slideImages/slide1.jpg",
				"It’s the 1880s in Sweden. The opportunities in America, such as cheap land and a stable economy, make migration attractive to many Europeans who face problems related to overpopulation. Millions have migrated from their homelands for a new life. You now wish to join them. \nWho are you?",
				null, null, 1);
		s1.addOption(new Option("Anders Bengtsson ", "You choose Anders Bengtsson", null, 2, null, null));
		s1.addOption(new Option("Lovisa Eriksdotter", "You choose Lovisa Eriksdotter", null, 3, null, null));

		Slide s2 = new Slide("slideImages/Slide2.jpg",
				"You are an 18 year-old female maid from Stockholm. You dislike your job as maid and can’t seem to find a better one because there is too much competition (population has been booming in the city and countryside). You decide to seek better employment in America.",
				null, null, 2);
		s2.addOption(new Option("Continue", null, null, 0, null, null));

		Slide s3 = new Slide("slideImages/Slide3.jpg",
				"You are an 18 year-old male from Mellby Parish, Småland. You are the youngest of 4 brothers and will not have any farmland to work. There are too many men in your family and not enough farmland. Additionally, the crop failures of 1867-1868 hit your family farm hard. You would also like to avoid Swedish military conscription. You are coming to America to own your own farmland or find a good job. ",
				null, null, 3);
		s3.addOption(new Option("Continue", null, null, 6, null, null));

		Slide s6 = new Slide("slideImages/Slide6.jpg",
				"Receiving letters from those who journeyed to America prompted many of your family friends to pursue their own journey over. Life must be treating them well in America— economic opportunities, building their own legacy and family. The American Dream is a strong pull for Swedes, yourself included. \n"
						+ "\n" + "Choose a letter to read:",
				null, null, 0);

		gd.addSlide(s1);
		gd.addSlide(s2);
		gd.addSlide(s3);
		gd.addSlide(s6);

		String serializedJSONText = gd.toJSON();

		System.out.println(serializedJSONText);

		//GameData gdRecreated = GameData.fromJSON(serializedJSONText);
		// System.out.println("Slide 0 has this image: ");
		// System.out.println(gdRecreated.getSlide(0).getImageFileName());

	}

}