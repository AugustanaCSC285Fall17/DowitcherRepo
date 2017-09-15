package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class will represent all of the data needed to 
 * load/save an adventure game.
 */
public class GameData {
	private List<Slide> slides;
	
	public GameData() { // needed for GSon
		slides = new ArrayList<Slide>();
	}

	public List<Slide> getSlides() {
		return slides;
	}

	public void setSlides(List<Slide> slides) {
		this.slides = slides;
	}
	
	/**
	 * @return a serialized JSON-format string that represents this GameData object
	 */
	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
	
	/**
	 * @return a GameData object, which is created from deserializing 
	 *         the JSON data provided. 
	 */
	public static GameData fromJSON(String jsonData) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonData, GameData.class);
	}
}
