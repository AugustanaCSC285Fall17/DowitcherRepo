package edu.augustana.csc285.game.datamodel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.utils.Json;

/**
 * 
 * @author Dat Tran
 *
 */
public class Story {
	public HashMap<Integer, Slide> slides;
	private String defaultMusic;
	private int startingSlideIndex;
	// Never use -1 since that is the ending indicator

	public Story() {

	}

	public Story(int startingSlideIndex) {
		slides = new HashMap<Integer, Slide>();
		this.startingSlideIndex = startingSlideIndex;
	}

	public Story(Story other) {
		slides = new HashMap<Integer, Slide>(other.slides);
		this.startingSlideIndex = other.startingSlideIndex;
	}

	public Story(int startingSlideIndex, String defaultMusic) {
		this(startingSlideIndex);
		this.defaultMusic = defaultMusic;
	}

	public String getDefaultMusic() {
		if (defaultMusic == null) {
			throw new IllegalStateException("There is no default music");
		}
		return defaultMusic;
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

	/**
	 * 
	 * @param id:
	 *            id for a slide that the user wants to remove post: remove the
	 *            certain slide with id
	 * @throws IllegalArgumentException
	 *             if id is not in the story
	 */
	public void removeSlide(int id) {
		checkID(id);
		slides.remove(id);
	}

	/**
	 * 
	 * @param id:
	 *            id for a slide that the user wants to remove post: remove the
	 *            certain slide with id
	 * @throws IllegalArgumentException
	 *             if id is not in the story
	 */
	public Slide getSlide(int id) {
		checkID(id);
		return slides.get(id);
	}

	public int getStartingSlideIndex() {
		return startingSlideIndex;
	}

	public void setStartingSlideIndex(int startingSlideIndex) {
		this.startingSlideIndex = startingSlideIndex;
	}

	/**
	 * post: throw IllegalArgumentException if the id is not in the story
	 * 
	 * @param id
	 *            for the slide in the story
	 */
	public void checkID(int id) {
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
	 * @return a GameData object, which is created from deserializing the JSON
	 *         data provided.
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static Story fromJSON(String jsonData) {
		return new Json().fromJson(Story.class, jsonData);
	}

	// for testing purpose
	public static void main(String[] args) {
		Story story = new Story(1);
		/*
		 * Slide s0 = new Slide("Room 0", "A boring room", "slide_000.png");
		 * ActionChoice choice = new
		 * ActionChoice("go to room 1 and get bananas", 1); choice.addEffect(new
		 * InventoryEffect("bananas", 42)); s0.getActionChoices().add(choice);
		 * s0.getActionChoices().add(new ActionChoice("go to room 2", 2)); Slide
		 * s1 = new Slide("Room 1", "A fascinating room", "slide_001.png");
		 * s1.getActionChoices().add(new ActionChoice("die", -1));
		 * s1.getActionChoices().add(new ActionChoice("go to room 2", 2)); Slide
		 * s2 = new Slide("Room 2", "An emu farm", "slide_002.png");
		 * s2.getActionChoices().add(new ActionChoice("go back to room 0", 0));
		 */
		Slide s1 = new Slide("slideImages/slide1.jpg",
				"It’s the 1880s in Sweden. The opportunities in America, such as cheap land and a stable economy, make migration attractive to many Europeans who face problems related to overpopulation. Millions have migrated from their homelands for a new life. You now wish to join them. \nWho are you?",
				null, null, 1);
		s1.addOption(new Option("Anders Bengtsson ", "You choose Anders Bengtsson", null, 2, null, null));
		s1.addOption(new Option("Lovisa Eriksdotter", "You choose Lovisa Eriksdotter", null, 3, null, null));

		Slide s2 = new Slide("slideImages/Slide2.jpg",
				"You are an 18 year-old female maid from Stockholm. You dislike your job as maid and can’t seem to find a better one because there is too much competition (population has been booming in the city and countryside). You decide to seek better employment in America.",
				null, null, 2);
		s2.addOption(new Option("Continue", null, null, 6, null, null));

		Slide s3 = new Slide("slideImages/Slide3.jpg",
				"You are an 18 year-old male from Mellby Parish, Småland. You are the youngest of 4 brothers and will not have any farmland to work. There are too many men in your family and not enough farmland. Additionally, the crop failures of 1867-1868 hit your family farm hard. You would also like to avoid Swedish military conscription. You are coming to America to own your own farmland or find a good job. ",
				null, null, 3);
		s3.addOption(new Option("Continue", null, null, 6, null, null));

		Slide s6 = new Slide("slideImages/Slide6.jpg",
				"Receiving letters from those who journeyed to America prompted many of your family friends to pursue their own journey over. Life must be treating them well in America— economic opportunities, building their own legacy and family. The American Dream is a strong pull for Swedes, yourself included. \n"
						+ "\n" + "Choose a letter to read:",
				null, null, 6);

		story.addSlide(s1);
		story.addSlide(s2);
		story.addSlide(s3);
		story.addSlide(s6);

		String serializedJSONText = story.toJSON();
		System.out.println(serializedJSONText);

	}

}
