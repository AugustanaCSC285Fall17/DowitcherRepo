package edu.augustana.csc285.game.datamodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.game.datamodel.condition.*;

/**
 * 
 * @author Dat Tran
 *
 */
public class Story {
	public HashMap<String, Slide> slides = new HashMap<String, Slide>();
	private String defaultMusic;
	private String startingSlideIndex;
	// Never use -1 since that is the ending indicator

	public Story() {

	}

	public Story(String startingSlideIndex) {
		this.startingSlideIndex = startingSlideIndex;
	}

	public Story(Story other) {
		slides = new HashMap<String, Slide>(other.slides);
		this.startingSlideIndex = other.startingSlideIndex;
	}

	public Story(String startingSlideIndex, String defaultMusic) {
		this(startingSlideIndex);
		this.defaultMusic = defaultMusic;
	}

	public String getDefaultMusic() {
		if (defaultMusic == null) {
			throw new IllegalStateException("There is no default music");
		}
		return defaultMusic;
	}

	/*
	 * Returns a list of slides, used for MainPanev2
	 */
	public List<Slide> getSlides() {
		ArrayList<Slide> output = new ArrayList<Slide>();
		for (String index : slides.keySet()) {
			output.add(slides.get(index));
		}
		return output;

	}

	public void setDefaultMusic(String defaultMusic) {
		this.defaultMusic = defaultMusic;
	}

	/**
	 * post: add a slide to a story
	 * 
	 * @param slide:
	 *            slide to add into story
	 * @throws IllegalArgumentException
	 *             if the slide has the id that was already used
	 */
	public void addSlide(Slide slide) {
		if (slides.containsKey(slide.getId())) {
			throw new IllegalArgumentException("Slide ID is already used");
		} else {
			slides.put(slide.getId(), new Slide(slide));
		}
	}

	public void addSlideOverride(Slide currentSlide) {
		slides.put(currentSlide.getId(), new Slide(currentSlide));

	}

	/**
	 * 
	 * @param id:
	 *            id for a slide that the user wants to remove post: remove the
	 *            certain slide with id
	 * @throws IllegalArgumentException
	 *             if id is not in the story
	 */
	public void removeSlide(String id) {
		checkID(id);
		slides.remove(id);
	}

	public boolean contains(String id) {
		return slides.containsKey(id);
	}

	/**
	 * 
	 * @param id:
	 *            id for a slide that the user wants to remove post: remove the
	 *            certain slide with id
	 * @throws IllegalArgumentException
	 *             if id is not in the story
	 */
	public Slide getSlide(String id) {
		return slides.get(id);
	}

	public String getStartingSlideIndex() {
		return startingSlideIndex;
	}

	public void setStartingSlideIndex(String startingSlideIndex) {
		this.startingSlideIndex = startingSlideIndex;
	}

	/**
	 * post: throw IllegalArgumentException if the id is not in the story
	 * 
	 * @param id
	 *            for the slide in the story
	 */
	public void checkID(String id) {
		if (!slides.containsKey(id)) {
			throw new IllegalArgumentException("id: " + id + " is not valid");
		}
	}

	public String toString() {
		String str = "";
		for (Slide slide : slides.values()) {
			str += slide.toString() + "\n";
		}
		return str;
	}

	/**
	 * @return a serialized JSON-format string that represents this GameData
	 *         object
	 * @throws JsonProcessingException
	 */
	public String toJSON() {
		return new Json().prettyPrint(this);
		
	}

	/**
	 * Returns an array containing all the Story files Uses libgdx for
	 * compatability with game engine
	 */
	public static FileHandle[] getStoryFiles() {
		return new FileHandle("storyData").list();
	}

	/**
	 * @return a GameData object, which is created from deserializing the JSON
	 *         data provided.
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Story fromJSON(String jsonData) {
		return new Json().fromJson(Story.class, jsonData);
	}

	public ArrayList<String> getSlideIds() {
		ArrayList<String> ids = new ArrayList<String>(slides.size());
		ids.addAll((this.slides.keySet()));
		Collections.sort(ids, new ComparatorForStringID());
		return ids;
	}
	//
	// public static void main(String[] args) {
	// Item sek = new Item("Sek", "Currency of Sweden", 1, null);
	// Item dollar = new Item("Dollar", "Currency of USA", 1, null);
	// Item medicine = new Item("Medicine", null, 1, "icons/medicine.jpg");
	// Item jDLetter = new Item("John Deere Letter", "A letter from John Deere",
	// 1, "icons/letter.jpg");
	// Item iLetter = new Item("Illinois Letter", "A letter from Illinois", 1,
	// "icons/letter.jpg");
	// Item wLetter = new Item("Washington Letter", "A letter from Washington",
	// 1, "icons/letter.jpg");
	// Item mLetter = new Item("Minnesota Letter", "A letter from Minnesota", 1,
	// "icons/letter.jpg");
	// Item officialPapers = new Item("Official papers", null, 1,
	// "icons/official-papers.jpg");
	// Item forgedPapers = new Item("Forged papers", null, 1,
	// "icons/forged-paper.jpg");
	// Item bible = new Item("Bible", null, 1, "icons/bible.jpg");
	// Item familyHeirloom = new Item("Family Heirloom", null, 1,
	// "icons/familyheirloom.jpg");
	// Item sewingMachine = new Item("Sewing Machine", null, 1,
	// "icons/sewing-machine.jpg");
	// Item curedMeat = new Item("Cured Meat", null, 1, "icons/meat.jpg");
	// Item ticketToHull = new Item("Ticket to Hull", null, 1,
	// "icons/ticket.jpg");
	// Item ticketToAmerica = new Item("Ticket to America", null, 1,
	// "icons/ticket.jpg");
	// // Item
	//
	// // I've only added the title parameter to the first 2 slides (1 & 2) btw
	// Story story = new Story("0");
	// Slide s0 = new Slide("GameData/SlideImages/slide0.jpg",
	// "You're young Swedish Immigrat to America in 1880. You've made the tough
	// decision to leave your family and life in Sweden behind. Will you survive
	// and prosper in America?\n",
	// "0", "The Swedish Immigrant Trail");
	// s0.addOption(new Option("Take the Journey", null, null, "1", null,
	// null));
	// Slide s1 = new Slide("GameData/SlideImages/slide1.jpg",
	// "It's the 1880s in Sweden. The opportunities in America, such as cheap
	// land and a stable economy, make migration attractive to many Europeans
	// who face problems related to overpopulation. Millions have migrated from
	// their homelands for a new life. You now wish to join them. \nWho are
	// you?",
	// "1", "Historical Note: Swedish Immigration");
	// Option temp = new Option("Anders Bengtsson ", null, null, "2", null,
	// null);
	// Effect effect = new GenderEffect(Gender.MALE);
	// temp.addEffect(effect);
	// effect = new NameEffect("Anders Bengtsson");
	// temp.addEffect(effect);
	// s1.addOption(temp);
	// temp = new Option("Lovisa Eriksdotter", null, null, "3", null, null);
	// effect = new GenderEffect(Gender.FEMALE);
	// temp.addEffect(effect);
	// effect = new NameEffect("Lovisa Eriksdotter");
	// temp.addEffect(effect);
	// s1.addOption(temp);
	//
	// Slide s2 = new Slide("GameData/SlideImages/slide2.jpg",
	// "You are an 18 year-old male from Mellby Parish, Småland. You are the
	// youngest of 4 brothers and will not have any farmland to work. There are
	// too many men in your family and not enough farmland. Additionally, the
	// crop failures of 1867-1868 hit your family farm hard. You would also like
	// to avoid Swedish military conscription. You are coming to America to own
	// your own farmland or find a good job. ",
	// "2", "Anders Bengtsson");
	// s2.addOption(new Option("Continue", null, null, "4", null, null));
	//
	// Slide s3 = new Slide("GameData/SlideImages/slide3.jpg",
	// "You are an 18 year-old female maid from Stockholm. You dislike your job
	// as maid and can't seem to find a better one because there is too much
	// competition (population has been booming in the city and countryside).
	// You decide to seek better employment in America.",
	// null, "3");
	// s3.addOption(new Option("Continue", null, null, "4", null, null));
	//
	// Slide s4 = new Slide("GameData/SlideImages/slide4.jpg",
	// "Receiving letters from those who journeyed to America prompted many of
	// your family friends to pursue their own journey over. Life must be
	// treating them well in America— economic opportunities, building their
	// own legacy and family. The American Dream is a strong pull for Swedes,
	// yourself included. \n"
	// + "\n" + "Choose a letter to read:",
	// null, "4");
	//
	// Option johnDeereLetter = new Option("John Deere Letter",
	// /* new Notification("You received the letter") */null, null, "5", null,
	// null);
	// johnDeereLetter.addEffect(new ItemEffect(jDLetter,
	// EffectOperation.PLUS));
	// // stage.addActor(not);
	// johnDeereLetter.addVisibleCondition(new GenderCondition(Gender.MALE));
	// s4.addOption(johnDeereLetter);
	//
	// Option illinoisLetter = new Option("Illinois Letter", null, null, "6",
	// null, null);
	// illinoisLetter.addEffect(new ItemEffect(iLetter, EffectOperation.PLUS));
	// s4.addOption(illinoisLetter);
	//
	// Option washingtonLetter = new Option("Washington Letter", null, null,
	// "7", null, null);
	// washingtonLetter.addEffect(new ItemEffect(wLetter,
	// EffectOperation.PLUS));
	// washingtonLetter.addVisibleCondition(new GenderCondition(Gender.FEMALE));
	// s4.addOption(washingtonLetter);
	//
	// Option minnesotaLetter = new Option("Minnesota Letter", null, null, "8",
	// null, null);
	// minnesotaLetter.addEffect(new ItemEffect(mLetter, EffectOperation.PLUS));
	// minnesotaLetter.addVisibleCondition(new GenderCondition(Gender.MALE));
	// s4.addOption(minnesotaLetter);
	//
	// Slide s5 = new Slide("GameData/SlideImages/slide5.jpg",
	// "To Whom It May Concern,\n"
	// + "We hope you find this letter in good spirits. On behalf of the John
	// Deere Company, we thank you for meeting with us concerning about jobs
	// within our factories. We are pleased to inform you you have been
	// recruited for a position at the Moline factory site. We are looking
	// forward to seeing you again and have safe travels. Please bring along
	// this notarized letter with you to our recruitment office.\n"
	// + "God speed,\n" + "From the Office of John Deere, President of John
	// Deere Co.",
	// null, "5");
	// s5.addOption(new Option("Continue", null, null, "9", null, null));
	//
	// Slide s6 = new Slide("GameData/SlideImages/slide6.jpg",
	// "Dear Cousin,\n"
	// + "May God find you well, dear friend! God is good and God is plentiful.
	// He has shown me the way to a brighter future here in America. My journey
	// has led myself and my travelling companions to a quaint town in the state
	// of Illinois called Moline. It may be quaint now but we are fortunate to
	// have factories and farming jobs available for the people. I have friends
	// all over Illinois including Rockford and Chicago! We stay in touch
	// through correspondence and the occasional social visit when we can. My
	// piece of advice for you, should you make the journey, is to find peace
	// with yourself and our loving God. For He is the way and will guide you!
	// The Christian faith has given me hope and salvation along my journey. My
	// fellow companions and myself attend the Lutheran service held by a pastor
	// who is also from Sweden. It is wonderful to have familiarity in a strange
	// place. Services are something to look forward to as it brings us closer
	// to our Maker and builds a strong sense of community. I pray for all the
	// success in the world for you as you make your own journey. \n"
	// + "Lovingly,\n" + "Erika Leiffson",
	// null, "6");
	// s6.addOption(new Option("Continue", null, null, "9", null, null));
	//
	// Slide s7 = new Slide("GameData/SlideImages/slide7.jpg",
	// "Dear Cousin,\n"
	// + "I have been blessed to have found a place I can call home. My journeys
	// were long, and at times I had felt grief for leaving my beautiful Sweden.
	// I remember so clearly how it felt to be a stranger in a strange place.
	// Only having a small trunk and broken English left me apprehensive of my
	// surroundings. I am thankful that I have experienced no ill wills on my
	// passage to America. I had heard some of my kin had travelled to the far
	// west of this vast country, in a place called Washington state. My family
	// settled near Tacoma and are now prosperous farmers and fishermen! I
	// decided to try my hand for this place. I took the Walla Walla railroad
	// instead of the Northern Pacific, as it was the cheaper option. I was
	// taken to Seattle and found a boardinghouse for Swedes. Within a month I
	// had found employment as a maid to one of the founding families. They
	// treat me well despite some language barrier, but I am becoming more
	// proficient and feel more confident in my new home.\n"
	// + "I do wish you well and hope someday to meet you again in my new home!
	// Give my love to my family and friends!\n"
	// + "\n" + "Linnea Parsson\n",
	// null, "7");
	// s7.addOption(new Option("Continue", null, null, "9", null, null));
	//
	// Slide s8 = new Slide("GameData/SlideImages/slide8.jpg",
	// "Dear Cousin,\n"
	// + "I am content to report that I have found steady employment as my own
	// master. I had arrived only two months in St. Paul, working odds and ends
	// in the town when I noticed an advertisement of purchasable land not far
	// from my lodgings. Land is so cheap here in America! I saved enough to buy
	// my own lot and have begun to till and grind the land. My hope is to grow
	// peas and potatoes and sell them at market. Let us pray for a good harvest
	// this next season!\n"
	// + "Life has treated me well. The journey is hard on everyone, harder for
	// others. My companion on the trip to America arrived well but I have
	// received word that he has fallen into poverty. He was swindled by a false
	// recruiter at the docks. He is struggling to find employment in the city
	// but, God willing, I hope he will find something soon!\n"
	// + "As you make the journey, make sure to bring plenty of food. They treat
	// you well on the ships and trains but the journey alone can make one
	// homesick. A little piece of home can appease the qualms of the stomach
	// and might make you a friend or two!\n"
	// + "Godspeed dear cousin! \n" + "Arvid Lothbrok\n",
	// null, "8");
	// s8.addOption(new Option("Continue", null, null, "9", null, null));
	//
	// Slide s9 = new Slide("GameData/SlideImages/slide9.jpg",
	// "To start your journey, you need a few provisions. The first and foremost
	// being your papers. You have $70 USD worth of Kronor (Swedish money) right
	// now. Do you:\n",
	// null, "9");
	// temp = new Option("Buy official papers from your home parish 80 SEK",
	// null, null, "10", null, null);
	// temp.addFeasibleCondition(new ItemCondition(new Item(sek, 80),
	// ConditionOperation.GREATER_OR_EQUAL));
	// temp.addEffect(new ItemEffect(new Item(officialPapers, 1),
	// EffectOperation.PLUS));
	// temp.addEffect(new ItemEffect(new Item(sek, 80), EffectOperation.MINUS));
	// s9.addOption(temp);
	// temp = new Option("Buy forged papers 40 SEK", null, null, "10", null,
	// null);
	// temp.addFeasibleCondition(new ItemCondition(new Item(sek, 40),
	// ConditionOperation.GREATER_OR_EQUAL));
	// temp.addEffect(new ItemEffect(forgedPapers, EffectOperation.PLUS));
	// temp.addEffect(new ItemEffect(new Item(sek, 40), EffectOperation.MINUS));
	// s9.addOption(temp);
	//
	// Slide s10 = new Slide("GameData/SlideImages/slide10.jpg",
	// "Excellent. You are the first in your family to take on this journey.
	// They are counting on your success in America. Your family has pooled
	// enough money for passage, thankfully. You cannot take too much with you,
	// so you must choose carefully which items to bring. Some items are more
	// useful than others but even the smallest, trivial item can lead to
	// unexpected use. Remember, you already have some money left after
	// purchasing papers. \n",
	// null, "10");
	// Option bibleOption = new Option("A Bible", null, null, "11", null, null);
	// bibleOption.addVisibleCondition(new ItemCondition(bible,
	// ConditionOperation.SMALLER));
	// bibleOption.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
	// s10.addOption(bibleOption);
	// Option familyHeirloomOption = new Option("Family Heirloon", null, null,
	// "11", null, null);
	// familyHeirloomOption.addVisibleCondition(new
	// ItemCondition(familyHeirloom, ConditionOperation.SMALLER));
	// familyHeirloomOption.addEffect(new ItemEffect(familyHeirloom,
	// EffectOperation.PLUS));
	// s10.addOption(familyHeirloomOption);
	// Option sewingMachineOption = new Option("Sewing Machine", null, null,
	// "11", null, null);
	// sewingMachineOption.addVisibleCondition(new ItemCondition(sewingMachine,
	// ConditionOperation.SMALLER));
	// sewingMachineOption.addEffect(new ItemEffect(sewingMachine,
	// EffectOperation.PLUS));
	// s10.addOption(sewingMachineOption);
	// Option medicineOption = new Option("Medicine", null, null, "11", null,
	// null);
	// medicineOption.addVisibleCondition(new ItemCondition(medicine,
	// ConditionOperation.SMALLER));
	// medicineOption.addEffect(new ItemEffect(medicine, EffectOperation.PLUS));
	// s10.addOption(medicineOption);
	// Option curedMeatOption = new Option("Cured Meat", null, null, "11", null,
	// null);
	// curedMeatOption.addVisibleCondition(new ItemCondition(curedMeat,
	// ConditionOperation.SMALLER));
	// curedMeatOption.addEffect(new ItemEffect(curedMeat,
	// EffectOperation.PLUS));
	// s10.addOption(curedMeatOption);
	//
	// Slide s11 = new Slide("GameData/SlideImages/slide10.jpg",
	// "Excellent. You are the first in your family to take on this journey.
	// They are counting on your success in America. Your family has pooled
	// enough money for passage, thankfully. You cannot take too much with you,
	// so you must choose carefully which items to bring. Some items are more
	// useful than others but even the smallest, trivial item can lead to
	// unexpected use. Remember, you already have some money left after
	// purchasing papers. \n",
	// null, "11");
	// Option bibleOption1 = new Option("A Bible", null, null, "12", null,
	// null);
	// bibleOption1.addVisibleCondition(new ItemCondition(bible,
	// ConditionOperation.SMALLER));
	// bibleOption1.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
	// s11.addOption(bibleOption1);
	// Option familyHeirloomOption1 = new Option("Family Heirloon", null, null,
	// "12", null, null);
	// familyHeirloomOption1.addVisibleCondition(new
	// ItemCondition(familyHeirloom, ConditionOperation.SMALLER));
	// familyHeirloomOption1.addEffect(new ItemEffect(familyHeirloom,
	// EffectOperation.PLUS));
	// s11.addOption(familyHeirloomOption1);
	// Option sewingMachineOption1 = new Option("Sewing Machine", null, null,
	// "12", null, null);
	// sewingMachineOption1.addVisibleCondition(new ItemCondition(sewingMachine,
	// ConditionOperation.SMALLER));
	// sewingMachineOption1.addEffect(new ItemEffect(sewingMachine,
	// EffectOperation.PLUS));
	// s11.addOption(sewingMachineOption1);
	// Option medicineOption1 = new Option("Medicine", null, null, "12", null,
	// null);
	// medicineOption1.addVisibleCondition(new ItemCondition(medicine,
	// ConditionOperation.SMALLER));
	// medicineOption1.addEffect(new ItemEffect(medicine,
	// EffectOperation.PLUS));
	// s11.addOption(medicineOption1);
	// Option curedMeatOption1 = new Option("Cured Meat", null, null, "12",
	// null, null);
	// curedMeatOption1.addVisibleCondition(new ItemCondition(curedMeat,
	// ConditionOperation.SMALLER));
	// curedMeatOption1.addEffect(new ItemEffect(curedMeat,
	// EffectOperation.PLUS));
	// s11.addOption(curedMeatOption1);
	//
	// Slide s12 = new Slide("GameData/SlideImages/slide10.jpg",
	// "Excellent. You are the first in your family to take on this journey.
	// They are counting on your success in America. Your family has pooled
	// enough money for passage, thankfully. You cannot take too much with you,
	// so you must choose carefully which items to bring. Some items are more
	// useful than others but even the smallest, trivial item can lead to
	// unexpected use. Remember, you already have some money left after
	// purchasing papers. \n",
	// null, "12");
	// Option bibleOption2 = new Option("A Bible", null, null, "13", null,
	// null);
	// bibleOption2.addVisibleCondition(new ItemCondition(bible,
	// ConditionOperation.SMALLER));
	// bibleOption2.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
	// s12.addOption(bibleOption2);
	// Option familyHeirloomOption2 = new Option("Family Heirloon", null, null,
	// "13", null, null);
	// familyHeirloomOption2.addVisibleCondition(new
	// ItemCondition(familyHeirloom, ConditionOperation.SMALLER));
	// familyHeirloomOption2.addEffect(new ItemEffect(familyHeirloom,
	// EffectOperation.PLUS));
	// s12.addOption(familyHeirloomOption2);
	// Option sewingMachineOption2 = new Option("Sewing Machine", null, null,
	// "13", null, null);
	// sewingMachineOption2.addVisibleCondition(new ItemCondition(sewingMachine,
	// ConditionOperation.SMALLER));
	// sewingMachineOption2.addEffect(new ItemEffect(sewingMachine,
	// EffectOperation.PLUS));
	// s12.addOption(sewingMachineOption2);
	// Option medicineOption2 = new Option("Medicine", null, null, "13", null,
	// null);
	// medicineOption2.addVisibleCondition(new ItemCondition(medicine,
	// ConditionOperation.SMALLER));
	// medicineOption2.addEffect(new ItemEffect(medicine,
	// EffectOperation.PLUS));
	// s12.addOption(medicineOption2);
	// Option curedMeatOption2 = new Option("Cured Meat", null, null, "13",
	// null, null);
	// curedMeatOption2.addVisibleCondition(new ItemCondition(curedMeat,
	// ConditionOperation.SMALLER));
	// curedMeatOption2.addEffect(new ItemEffect(curedMeat,
	// EffectOperation.PLUS));
	// s12.addOption(curedMeatOption2);
	//
	// Slide s13 = new Slide("GameData/SlideImages/slide13.jpg",
	// "You take a wagon to the port city of Gothenburg. You walk along
	// Sillgatan or \"Herring Street\" where there were many emigrant agents,
	// hotels, shops, and taverns. Here you wait to board a ship to England.",
	// null, "13");
	// s13.addOption(new Option("Continue", null, null, "14", null, null));
	//
	// Slide s14 = new Slide("GameData/SlideImages/slide14.jpg",
	// "Over a million Swedish immigrants left through the port of Gothenburg in
	// southwest Sweden. Many American genealogists believe their ancestors
	// lived in Gothenburg, when really this was just the port they left
	// through. Provinces of Sweden including SmÃ¥land, VÃ¤stergÃ¶tland,
	// Dalsland, VÃ¤rmland, and Ã–land saw large numbers emigrate. ",
	// null, "14");
	// s14.addOption(new Option("Left", null, null, "15", null, null));
	// s14.addOption(new Option("Forward", null, null, "16", null, null));
	// s14.addOption(new Option("Right", null, null, "17", null, null));
	//
	// Slide s15 = new Slide("GameData/SlideImages/slide15.jpg",
	// "You see a market at the end of the lane. Do you wish to shop here?",
	// null, "15");
	// s15.addOption(new Option("Yes", null, null, "16", null, null));
	// s15.addOption(new Option("No", null, null, "14", null, null));
	//
	// Slide s16 = new Slide("GameData/SlideImages/slide15.jpg",
	// "Inspection After passing initial health inspection, the ticketmaster
	// asks for your papers. Do you have your ticket?\n",
	// null, "16");
	// Option shipBoarding = new Option("Yes", null, null, "21", null, null);
	// shipBoarding.addVisibleCondition(new ItemCondition(ticketToHull,
	// ConditionOperation.EQUAL));
	// shipBoarding.addEffect(new ItemEffect(ticketToHull,
	// EffectOperation.MINUS));
	// s16.addOption(shipBoarding);
	// s16.addOption(new Option("No", null, null, "14", null, null));
	//
	// Slide s17 = new Slide("GameData/SlideImages/slide15.jpg", "Ticket agent
	// \"What can I do for you?\"", null,
	// "17");
	// Option buyHull = new Option("Pay for transport to Hull 60 SEK", null,
	// null, "14", null, null);
	// Option buyHullAmerica = new Option("Pay for transport to Hull through
	// Liverpool 120 SEK", null, null, "14",
	// null, null);
	//
	// buyHull.addEffect(new ItemEffect(ticketToHull, EffectOperation.PLUS));
	// buyHull.addFeasibleCondition(new ItemCondition(new Item(sek, 60),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyHull.addEffect(new ItemEffect(new Item(sek, 60),
	// EffectOperation.MINUS));
	//
	// buyHullAmerica.addFeasibleCondition(new ItemCondition(new Item(sek, 120),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyHullAmerica.addEffect(new ItemEffect(ticketToHull,
	// EffectOperation.PLUS));
	// buyHullAmerica.addEffect(new ItemEffect(ticketToAmerica,
	// EffectOperation.PLUS));
	// buyHull.addEffect(new ItemEffect(new Item(sek, 120),
	// EffectOperation.MINUS));
	// s17.addOption(buyHull);
	// s17.addOption(buyHullAmerica);
	//
	// Slide s18 = new Slide("GameData/SlideImages/slide10.jpg", "You're in the
	// shop. What would you like to do?\n",
	// null, "18");
	// s18.addOption(new Option("Buy", null, null, "19", null, null));
	// s18.addOption(new Option("Sell", null, null, "20", null, null));
	// s18.addOption(new Option("Leave", null, null, "14", null, null));
	//
	// Slide s19 = new Slide("GameData/SlideImages/slide10.jpg", "Choose one
	// item to buy", null, "19");
	// Option buyBible = new Option("Buy Bible 20 SEK", null, null, "14", null,
	// null);
	// buyBible.addFeasibleCondition(new ItemCondition(new Item(sek, 20),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyBible.addEffect(new ItemEffect(bible, EffectOperation.PLUS));
	// buyBible.addEffect(new ItemEffect(new Item(sek, 20),
	// EffectOperation.MINUS));
	// s19.addOption(buyBible);
	//
	// Option buyFamilyHeirloom = new Option("Buy Family Heirloom 20 SEK", null,
	// null, "14", null, null);
	// buyFamilyHeirloom
	// .addFeasibleCondition(new ItemCondition(new Item(sek, 20),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyFamilyHeirloom.addEffect(new ItemEffect(familyHeirloom,
	// EffectOperation.PLUS));
	// buyFamilyHeirloom.addEffect(new ItemEffect(new Item(sek, 20),
	// EffectOperation.MINUS));
	// s19.addOption(buyFamilyHeirloom);
	//
	// Option buySewingMachine = new Option("Buy Sewing Machine 20 SEK", null,
	// null, "14", null, null);
	// buySewingMachine
	// .addFeasibleCondition(new ItemCondition(new Item(sek, 20),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buySewingMachine.addEffect(new ItemEffect(sewingMachine,
	// EffectOperation.PLUS));
	// buySewingMachine.addEffect(new ItemEffect(new Item(sek, 20),
	// EffectOperation.MINUS));
	// s19.addOption(buySewingMachine);
	//
	// Option buyMedicine = new Option("Buy Medicine 20 SEK", null, null, "14",
	// null, null);
	// buyMedicine.addFeasibleCondition(new ItemCondition(new Item(sek, 20),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyMedicine.addEffect(new ItemEffect(medicine, EffectOperation.PLUS));
	// buyMedicine.addEffect(new ItemEffect(new Item(sek, 20),
	// EffectOperation.MINUS));
	// s19.addOption(buyMedicine);
	//
	// Option buyCuredMeat = new Option("Buy Cured Meat 20 SEK", null, null,
	// "14", null, null);
	// buyCuredMeat.addFeasibleCondition(new ItemCondition(new Item(sek, 20),
	// ConditionOperation.GREATER_OR_EQUAL));
	// buyCuredMeat.addEffect(new ItemEffect(curedMeat, EffectOperation.PLUS));
	// buyCuredMeat.addEffect(new ItemEffect(new Item(sek, 20),
	// EffectOperation.MINUS));
	// s19.addOption(buyCuredMeat);
	//
	// s19.addOption(new Option("Leave", null, null, "14", null, null));
	//
	// story.addSlide(s0);
	// story.addSlide(s1);
	// story.addSlide(s2);
	// story.addSlide(s3);
	// story.addSlide(s4);
	// story.addSlide(s5);
	// story.addSlide(s6);
	// story.addSlide(s7);
	// story.addSlide(s8);
	// story.addSlide(s9);
	// story.addSlide(s10);
	// story.addSlide(s11);
	// story.addSlide(s12);
	// story.addSlide(s13);
	// story.addSlide(s14);
	// story.addSlide(s15);
	// story.addSlide(s16);
	// story.addSlide(s17);
	// story.addSlide(s18);
	// story.addSlide(s19);
	//
	// System.out.println(story.toJSON());
	// }

}
