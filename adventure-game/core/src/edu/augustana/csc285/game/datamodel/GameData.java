package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will represent all of the data needed to 
 * load/save an adventure game.
 */
public class GameData {
	// TODO: eventually use GSon library to serialize
	//       all the game data to a .JSON file.
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
	
}
