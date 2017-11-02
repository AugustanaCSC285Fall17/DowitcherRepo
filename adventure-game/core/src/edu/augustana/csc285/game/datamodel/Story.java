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

/**Is the collection of Slides, music and starting index that comprises a story.
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
	 * @param checkId:
	 *            if true, checks if the Id has been used, otherwise will overwrite
	 *            whatever was there before
	 * @throws IllegalArgumentException
	 *             if the slide has the id that was already used
	 */
	public void addSlide(Slide slide, boolean checkId) {
		if (checkId && slides.containsKey(slide.getId())) {
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
	public void removeSlide(String id) {
		if(containsSlide(id)) {
			slides.remove(id);
		} else {
			throw new IllegalArgumentException("id: " + id + " is not valid");
		}
	}

	public boolean containsSlide(String id) {
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
	 * Compatibility with game engine
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


}
