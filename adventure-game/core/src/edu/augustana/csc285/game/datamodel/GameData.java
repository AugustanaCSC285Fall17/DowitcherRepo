package edu.augustana.csc285.game.datamodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;

/**
 * This class will represent all of the data needed to 
 * load/save an adventure game.
 */
public class GameData {
	private ArrayList<Slide> slides;
	
	public GameData() { // needed for GSon
		slides = new ArrayList<Slide>();
	}

	public void addSlide(Slide slide) {

		slides.add(slide);
	}
	public Slide getSlide(int index) {
		return slides.get(index);
	}
	
	/**
	 * @return a serialized JSON-format string that represents this GameData object
	 * @throws JsonProcessingException 
	 */
	public String toJSON() {
		return new Json().prettyPrint(this); 
	}

	/**
	 * @return a GameData object, which is created from deserializing 
	 *         the JSON data provided. 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static GameData fromJSON(String jsonData) {
		return new Json().fromJson(GameData.class,jsonData);
	}
}