package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.badlogic.gdx.utils.Json;

/**
 * 
 * @OriginalAuthor Dat Tran
 * @Author Daniel Zwiener
 *
 */
public class StoryLibrary {
	// The map between story name and story with quantity 1
	List<Story> stories = new ArrayList<Story>();

	public StoryLibrary() {
	}
	
	public Story getStory(int index) {
		return stories.get(index);
	}
	
	public void addStory(Story story) {
		stories.add(story);
	}
	
	public String toJSON() {
		return new Json().prettyPrint(this);
	}

	public static StoryLibrary fromJSON(String jsonData) {
		return new Json().fromJson(StoryLibrary.class, jsonData);
	}

	public static void main(String[] args) {
		StoryLibrary storyLibrary = new StoryLibrary();
		storyLibrary.addStory(new Story());
		
		System.out.println(storyLibrary.toJSON());
	}

	public void removeStory(Story currentStory) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<String> getStoryNameList() {
		// TODO Auto-generated method stub
		return null;
	}
}
