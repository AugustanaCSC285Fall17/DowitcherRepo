package edu.augustana.csc285.game.datamodel;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Story {
	private HashMap<Integer, Slide> slides;

	public Story(HashMap<Integer, Slide> slides) {
		this.slides = slides;
	}

	public Story() {
		slides = new HashMap<Integer, Slide>();
	}

	public Story(Story other) {
		this(other.slides);
	}

	/**
	 * post: add a slide to a story
	 * @param slide: slide to add into story
	 * @throws IllegalArgumentException if the slide has the id that was already used
	 */
	public void addSlide(Slide slide) {
		if (slides.containsKey(slide.getId())) {
			throw new IllegalArgumentException("Slide ID is already used");
		} else {
			slides.put(slide.getId(), slide);
		}
	}

	/**
	 * 
	 * @param id: id for a slide that the user wants to remove
	 * post: remove the certain slide with id
	 * @throws IllegalArgumentException if id is not in the story
	 */
	public void removeSlide(int id) {
		checkID(id);
		slides.remove(id);
	}

	/**
	 * 
	 * @param id: id for a slide that the user wants to remove
	 * post: remove the certain slide with id
	 * @throws IllegalArgumentException if id is not in the story
	 */
	public Slide getSlide(int id) {
		checkID(id);
		return new Slide(slides.get(id));
	}

	private void checkID(int id) {
		if (!slides.containsKey(id)) {
			throw new IllegalArgumentException("id is not valid");
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
	 */
	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	/**
	 * @return a GameData object, which is created from deserializing the JSON
	 *         data provided.
	 */
	public static Story fromJSON(String jsonData) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonData, Story.class);
	}

}
