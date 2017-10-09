package edu.augustana.csc285.game.datamodel.tester;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.game.datamodel.condition.*;

public class GameDataTester {

	public static void main(String[] args) {
		// Define Items
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
		// Item
		GameData gd = new GameData();
		Player player = new Player();
		Slide s0 = new Slide("GameData/SlideImages/slide0.jpg",
				"YouÃ¢â‚¬â„¢re a young Swedish immigrant to America in 1880. YouÃ¢â‚¬â„¢ve made the tough decision to leave your family and life in Sweden behind. Will you survive and prosper in America?\n",
				null, null, "0");
		s0.addOption(new Option("Take the Journey", "You have embarked on a journey!", null, "1", null, null));
		Slide s1 = new Slide("GameData/SlideImages/slide1.jpg",
				"ItÃ¢â‚¬â„¢s the 1880s in Sweden. The opportunities in America, such as cheap land and a stable economy, make migration attractive to many Europeans who face problems related to overpopulation. Millions have migrated from their homelands for a new life. You now wish to join them. \nWho are you?",
				null, null, "1");
		Option temp = new Option("Anders Bengtsson ", "You choose Anders Bengtsson", null, "2", null, null);
		Effect effect = new GenderEffect(Gender.MALE);
		temp.addEffect(effect);
		effect = new NameEffect("Anders Bengtsson");
		temp.addEffect(effect);
		s1.addOption(temp);
		temp = new Option("Lovisa Eriksdotter", "You choose Lovisa Eriksdotter", null, "3", null, null);
		effect = new GenderEffect(Gender.FEMALE);
		temp.addEffect(effect);
		effect = new NameEffect("Lovisa Eriksdotter");
		temp.addEffect(effect);
		s1.addOption(temp);

		Slide s2 = new Slide("GameData/SlideImages/slide2.jpg",
				"You are an 18 year-old male from Mellby Parish, SmÃƒÂ¥land. You are the youngest of 4 brothers and will not have any farmland to work. There are too many men in your family and not enough farmland. Additionally, the crop failures of 1867-1868 hit your family farm hard. You would also like to avoid Swedish military conscription. You are coming to America to own your own farmland or find a good job. ",
				null, null, "2");
		s2.addOption(new Option("Continue", null, null, "4", null, null));

		Slide s3 = new Slide("GameData/SlideImages/slide3.jpg",
				"You are an 18 year-old female maid from Stockholm. You dislike your job as maid and canÃ¢â‚¬â„¢t seem to find a better one because there is too much competition (population has been booming in the city and countryside). You decide to seek better employment in America.",
				null, null, "3");
		s3.addOption(new Option("Continue", null, null, "4", null, null));

		Slide s4 = new Slide("GameData/SlideImages/slide4.jpg",
				"Receiving letters from those who journeyed to America prompted many of your family friends to pursue their own journey over. Life must be treating them well in AmericaÃ¢â‚¬â€� economic opportunities, building their own legacy and family. The American Dream is a strong pull for Swedes, yourself included. \n"
						+ "\n" + "Choose a letter to read:",
				null, null, "4");

		Option johnDeereLetter = new Option("John Deere Letter", null, null, "5", null, null);
		johnDeereLetter.addEffect(new ItemEffect(jDLetter, EffectOperation.PLUS));
		johnDeereLetter.addVisibleCondition(new GenderCondition(Gender.MALE));
		s4.addOption(johnDeereLetter);

		Option illinoisLetter = new Option("Illinois Letter", null, null, "6", null, null);
		illinoisLetter.addEffect(new ItemEffect(iLetter, EffectOperation.PLUS));
		s4.addOption(illinoisLetter);

		Option washingtonLetter = new Option("Washington Letter", null, null, "7", null, null);
		washingtonLetter.addEffect(new ItemEffect(wLetter, EffectOperation.PLUS));
		washingtonLetter.addVisibleCondition(new GenderCondition(Gender.FEMALE));
		s4.addOption(washingtonLetter);

		Option minnesotaLetter = new Option("Minnesota Letter", null, null, "8", null, null);
		minnesotaLetter.addEffect(new ItemEffect(mLetter, EffectOperation.PLUS));
		minnesotaLetter.addVisibleCondition(new GenderCondition(Gender.MALE));
		s4.addOption(minnesotaLetter);

		Slide s5 = new Slide("GameData/SlideImages/slide5.jpg",
				"To Whom It May Concern,\n"
						+ "We hope you find this letter in good spirits. On behalf of the John Deere Company, we thank you for meeting with us concerning about jobs within our factories. We are pleased to inform you you have been recruited for a position at the Moline factory site. We are looking forward to seeing you again and have safe travels. Please bring along this notarized letter with you to our recruitment office.\n"
						+ "God speed,\n" + "From the Office of John Deere, President of John Deere Co.",
				null, null, "5");
		s5.addOption(new Option("Continue", null, null, "9", null, null));

		Slide s6 = new Slide("GameData/SlideImages/slide6.jpg",
				"Dear Cousin,\n"
						+ "May God find you well, dear friend! God is good and God is plentiful. He has shown me the way to a brighter future here in America. My journey has led myself and my travelling companions to a quaint town in the state of Illinois called Moline. It may be quaint now but we are fortunate to have factories and farming jobs available for the people. I have friends all over Illinois including Rockford and Chicago! We stay in touch through correspondence and the occasional social visit when we can. My piece of advice for you, should you make the journey, is to find peace with yourself and our loving God. For He is the way and will guide you! The Christian faith has given me hope and salvation along my journey. My fellow companions and myself attend the Lutheran service held by a pastor who is also from Sweden. It is wonderful to have familiarity in a strange place. Services are something to look forward to as it brings us closer to our Maker and builds a strong sense of community. I pray for all the success in the world for you as you make your own journey. \n"
						+ "Lovingly,\n" + "Erika Leiffson",
				null, null, "6");
		s6.addOption(new Option("Continue", null, null, "9", null, null));

		Slide s7 = new Slide("GameData/SlideImages/slide7.jpg",
				"Dear Cousin,\n"
						+ "I have been blessed to have found a place I can call home. My journeys were long, and at times I had felt grief for leaving my beautiful Sweden. I remember so clearly how it felt to be a stranger in a strange place. Only having a small trunk and broken English left me apprehensive of my surroundings. I am thankful that I have experienced no ill wills on my passage to America. I had heard some of my kin had travelled to the far west of this vast country, in a place called Washington state. My family settled near Tacoma and are now prosperous farmers and fishermen! I decided to try my hand for this place. I took the Walla Walla railroad instead of the Northern Pacific, as it was the cheaper option. I was taken to Seattle and found a boardinghouse for Swedes. Within a month I had found employment as a maid to one of the founding families. They treat me well despite some language barrier, but I am becoming more proficient and feel more confident in my new home.\n"
						+ "I do wish you well and hope someday to meet you again in my new home! Give my love to my family and friends!\n"
						+ "\n" + "Linnea Parsson\n",
				null, null, "7");
		s7.addOption(new Option("Continue", null, null, "9", null, null));

		Slide s8 = new Slide("GameData/SlideImages/slide8.jpg",
				"Dear Cousin,\n"
						+ "I am content to report that I have found steady employment as my own master. I had arrived only two months in St. Paul, working odds and ends in the town when I noticed an advertisement of purchasable land not far from my lodgings. Land is so cheap here in America! I saved enough to buy my own lot and have begun to till and grind the land. My hope is to grow peas and potatoes and sell them at market. Let us pray for a good harvest this next season!\n"
						+ "Life has treated me well. The journey is hard on everyone, harder for others. My companion on the trip to America arrived well but I have received word that he has fallen into poverty. He was swindled by a false recruiter at the docks. He is struggling to find employment in the city but, God willing, I hope he will find something soon!\n"
						+ "As you make the journey, make sure to bring plenty of food. They treat you well on the ships and trains but the journey alone can make one homesick. A little piece of home can appease the qualms of the stomach and might make you a friend or two!\n"
						+ "Godspeed dear cousin! \n" + "Arvid Lothbrok\n",
				null, null, "8");
		s8.addOption(new Option("Continue", null, null, "9", null, null));

		Slide s9 = new Slide("GameData/SlideImages/slide9.jpg",
				"To start your journey, you need a few provisions. The first and foremost being your papers. You have $70 USD worth of Kronor (Swedish money)  right now. Do you:\n",
				null, null, "9");
		temp = new Option("Buy official papers from your home parish $20 or 80 SEK", null, null, "10", null, null);
		temp.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 20), ConditionOperation.GREATER_OR_EQUAL));
		temp.addEffect(new ItemEffect(officialPapers, EffectOperation.PLUS));
		s9.addOption(temp);
		temp = new Option("Buy forged papers $10 or 40 SEK", null, null, "10", null, null);
		temp.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 10), ConditionOperation.GREATER_OR_EQUAL));
		temp.addEffect(new ItemEffect(forgedPapers, EffectOperation.PLUS));
		s9.addOption(temp);

		Slide s10 = new Slide("GameData/SlideImages/slide10.jpg",
				"Excellent. You are the first in your family to take on this journey. They are counting on your success in America. Your family has pooled enough money for passage, thankfully. You cannot take too much with you, so you must choose carefully which items to bring. Some items are more useful than others but even the smallest, trivial item can lead to unexpected use. Remember, you already have some money left after purchasing papers.  \n",
				null, null, "10");
		Option bibleOption = new Option("A Bible", null, null, "11", null, null);
		bibleOption.addVisibleCondition(new ItemCondition(bible, ConditionOperation.EQUAL));
		bibleOption.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
		s10.addOption(bibleOption);
		Option familyHeirloomOption = new Option("Family Heirloon", null, null, "11", null, null);
		familyHeirloomOption.addVisibleCondition(new ItemCondition(familyHeirloom, ConditionOperation.EQUAL));
		familyHeirloomOption.addEffect(new ItemEffect(familyHeirloom, EffectOperation.PLUS));
		s10.addOption(familyHeirloomOption);
		Option sewingMachineOption = new Option("sewingMachine", null, null, "11", null, null);
		sewingMachineOption.addVisibleCondition(new ItemCondition(sewingMachine, ConditionOperation.EQUAL));
		sewingMachineOption.addEffect(new ItemEffect(sewingMachine, EffectOperation.PLUS));
		s10.addOption(sewingMachineOption);
		Option medicineOption = new Option("Medicine", null, null, "11", null, null);
		medicineOption.addVisibleCondition(new ItemCondition(medicine, ConditionOperation.EQUAL));
		medicineOption.addEffect(new ItemEffect(medicine, EffectOperation.PLUS));
		s10.addOption(medicineOption);
		Option curedMeatOption = new Option("Cured Meat", null, null, "11", null, null);
		curedMeatOption.addVisibleCondition(new ItemCondition(curedMeat, ConditionOperation.EQUAL));
		curedMeatOption.addEffect(new ItemEffect(curedMeat, EffectOperation.PLUS));
		s10.addOption(curedMeatOption);

		Slide s11 = new Slide("GameData/SlideImages/slide10.jpg",
				"Excellent. You are the first in your family to take on this journey. They are counting on your success in America. Your family has pooled enough money for passage, thankfully. You cannot take too much with you, so you must choose carefully which items to bring. Some items are more useful than others but even the smallest, trivial item can lead to unexpected use. Remember, you already have some money left after purchasing papers.  \n",
				null, null, "11");
		bibleOption.setNextSlideIndex("12");
		s11.addOption(bibleOption);
		familyHeirloomOption.setNextSlideIndex("12");
		s11.addOption(familyHeirloomOption);
		sewingMachineOption.setNextSlideIndex("12");
		s11.addOption(sewingMachineOption);
		medicineOption.setNextSlideIndex("12");
		s11.addOption(medicineOption);
		curedMeatOption.setNextSlideIndex("12");
		s11.addOption(curedMeatOption);

		Slide s12 = new Slide("GameData/SlideImages/slide10.jpg",
				"Excellent. You are the first in your family to take on this journey. They are counting on your success in America. Your family has pooled enough money for passage, thankfully. You cannot take too much with you, so you must choose carefully which items to bring. Some items are more useful than others but even the smallest, trivial item can lead to unexpected use. Remember, you already have some money left after purchasing papers.  \n",
				null, null, "12");
		bibleOption.setNextSlideIndex("13");
		s12.addOption(bibleOption);
		familyHeirloomOption.setNextSlideIndex("13");
		s12.addOption(familyHeirloomOption);
		sewingMachineOption.setNextSlideIndex("13");
		s12.addOption(sewingMachineOption);
		medicineOption.setNextSlideIndex("13");
		s12.addOption(medicineOption);
		curedMeatOption.setNextSlideIndex("13");
		s12.addOption(curedMeatOption);

		Slide s13 = new Slide("GameData/SlideImages/slide13.jpg",
				"You take a wagon to the port city of Gothenburg. You walk along Sillgatan or Ã¢â‚¬Å“Herring StreetÃ¢â‚¬ï¿½ where there were many emigrant agents, hotels, shops, and taverns. Here you wait to board a ship to England.",
				null, null, "13");
		s13.addOption(new Option("Continue", null, null, "14", null, null));

		Slide s14 = new Slide("GameData/SlideImages/slide14.jpg",
				"Over a million Swedish immigrants left through the port of Gothenburg in southwest Sweden. Many American genealogists believe their ancestors lived in Gothenburg, when really this was just the port they left through. Provinces of Sweden including SmÃƒÂ¥land, VÃƒÂ¤stergÃƒÂ¶tland, Dalsland, VÃƒÂ¤rmland, and Ãƒâ€“land saw large numbers emigrate. ",
				null, null, "14");
		s14.addOption(new Option("Left", null, null, "15", null, null));
		s14.addOption(new Option("Forward", null, null, "16", null, null));
		s14.addOption(new Option("Right", null, null, "17", null, null));
		gd.addSlide(s14);

		Slide s15 = new Slide("GameData/SlideImages/slide15.jpg",
				"You see a market at the end of the lane. Do you wish to shop here?", null, null, "15");
		s15.addOption(new Option("Yes", null, null, "16", null, null));
		s15.addOption(new Option("No", null, null, "14", null, null));

		Slide s16 = new Slide("GameData/SlideImages/slide15.jpg",
				"Inspection After passing initial health inspection, the ticketmaster asks for your papers. Do you have your ticket?\n",
				null, null, "16");
		Option shipBoarding = new Option("Yes", null, null, "21", null, null);
		shipBoarding.addVisibleCondition(new ItemCondition(ticketToHull, ConditionOperation.EQUAL));
		shipBoarding.addEffect(new ItemEffect(ticketToHull, EffectOperation.MINUS));
		s16.addOption(shipBoarding);
		s16.addOption(new Option("No", null, null, "14", null, null));

		Slide s17 = new Slide("GameData/SlideImages/slide15.jpg", "Ticket agent Ã¢â‚¬Å“What can I do for you?Ã¢â‚¬ï¿½",
				null, null, "17");
		Option buyHull = new Option("Pay for transport to Hull $15 or 60 SEK.\n", null, null, "14", null, null);
		Option buyHullAmerica = new Option(
				"Pay for transport to Hull with connection to Liverpool and ship passage $30 or 120 SEK", null, null,
				"14", null, null);
		buyHull.addEffect(new ItemEffect(ticketToHull, EffectOperation.PLUS));
		buyHull.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 15), ConditionOperation.GREATER_OR_EQUAL));
		buyHull.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 15), EffectOperation.MINUS));
		buyHullAmerica.addEffect(new ItemEffect(ticketToHull, EffectOperation.PLUS));
		buyHullAmerica.addEffect(new ItemEffect(ticketToAmerica, EffectOperation.PLUS));
		buyHullAmerica.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 30), EffectOperation.MINUS));
		buyHullAmerica.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 30), ConditionOperation.GREATER_OR_EQUAL));

		Slide s18 = new Slide("GameData/SlideImages/slide10.jpg",
				"YouÃ¢â‚¬â„¢re in the shop. What would you like to do?\n", null, null, "18");
		s18.addOption(new Option("Buy", null, null, "19", null, null));
		s18.addOption(new Option("Sell", null, null, "20", null, null));
		s18.addOption(new Option("Leave", null, null, "14", null, null));

		Slide s19 = new Slide("GameData/SlideImages/slide10.jpg", "Choose one item to buy", null, null, "19");
		Option buyBible = new Option("Buy Bible $5 or 20 SEK", null, null, "14", null, null);
		buyBible.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 5), ConditionOperation.GREATER_OR_EQUAL));
		buyBible.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
		buyBible.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 5), EffectOperation.MINUS));
		s19.addOption(buyBible);

		Option buyFamilyHeirloom = new Option("Buy Family Heirloom $5 or 20 SEK", null, null, "14", null, null);
		buyFamilyHeirloom.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 5), ConditionOperation.GREATER_OR_EQUAL));
		buyFamilyHeirloom.addEffect(new ItemEffect(familyHeirloom, EffectOperation.PLUS));
		buyFamilyHeirloom.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 5), EffectOperation.MINUS));
		s19.addOption(buyFamilyHeirloom);

		Option buySewingMachine = new Option("Buy Sewing Machine $5 or 20 SEK", null, null, "14", null, null);
		buySewingMachine.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 5), ConditionOperation.GREATER_OR_EQUAL));
		buySewingMachine.addEffect(new ItemEffect(sewingMachine, EffectOperation.PLUS));
		buySewingMachine.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 5), EffectOperation.MINUS));
		s19.addOption(buySewingMachine);

		Option buyMedicine = new Option("Buy Medicine $5 or 20 SEK", null, null, "14", null, null);
		buyMedicine.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 5), ConditionOperation.GREATER_OR_EQUAL));
		buyMedicine.addEffect(new ItemEffect(medicine, EffectOperation.PLUS));
		buyMedicine.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 5), EffectOperation.MINUS));
		s19.addOption(buyMedicine);

		Option buyCuredMeat = new Option("Buy Cured Meat $5 or 20 SEK", null, null, "14", null, null);
		buyCuredMeat.addFeasibleCondition(
				new PropertyCondition(new Property(PropertyType.GOLD, 5), ConditionOperation.GREATER_OR_EQUAL));
		buyCuredMeat.addEffect(new ItemEffect(curedMeat, EffectOperation.PLUS));
		buyCuredMeat.addEffect(new PropertyEffect(new Property(PropertyType.GOLD, 5), EffectOperation.MINUS));
		s19.addOption(buyCuredMeat);

		s19.addOption(new Option("Leave", null, null, "14", null, null));

		gd.addSlide(s0);
		gd.addSlide(s1);
		gd.addSlide(s2);
		gd.addSlide(s3);
		gd.addSlide(s4);
		gd.addSlide(s5);
		gd.addSlide(s6);
		gd.addSlide(s7);
		gd.addSlide(s8);
		gd.addSlide(s9);
		gd.addSlide(s10);
		gd.addSlide(s11);
		gd.addSlide(s12);
		gd.addSlide(s13);
		gd.addSlide(s14);
		gd.addSlide(s15);
		gd.addSlide(s16);
		gd.addSlide(s17);
		gd.addSlide(s18);
		gd.addSlide(s19);

		String serializedJSONText = gd.toJSON();

		System.out.println(serializedJSONText);

		// GameData gdRecreated = GameData.fromJSON(serializedJSONText);
		// System.out.println("Slide 0 has this image: ");
		// System.out.println(gdRecreated.getSlide(0).getImageFileName());

	}

}