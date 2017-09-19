package edu.augustana.csc285.game.datamodel;

import java.io.File;
import java.util.ArrayList;

public class Slide {
	File image;
	String desc;
	String story;
	String url;
	File music;
	int id;
	ArrayList<Option> options;
	/**
	 * @param image
	 * @param desc
	 * @param story
	 * @param url
	 * @param music
	 * @param id
	 * @param options
	 */
	public Slide(File image, String desc, String story, String url, File music, int id, ArrayList<Option> options) {
		super();
		this.image = image;
		this.desc = desc;
		this.story = story;
		this.url = url;
		this.music = music;
		this.id = id;
		this.options = options;
	}
	
	public Slide(String desc, File image) {
		this(image, desc, "", "", null, 0, null);
		
	}

	/**
	 * @return the image
	 */
	public File getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(File image) {
		this.image = image;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the story
	 */
	public String getStory() {
		return story;
	}
	/**
	 * @param story the story to set
	 */
	public void setStory(String story) {
		this.story = story;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the music
	 */
	public File getMusic() {
		return music;
	}
	/**
	 * @param music the music to set
	 */
	public void setMusic(File music) {
		this.music = music;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the options
	 */
	public ArrayList<Option> getOptions() {
		return options;
	}
	/**
	 * @param options the options to set
	 */
	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
	
	public void addOption(Option option) {
		options.add(option);
	}
	
	public void removeOption(Option option) {
		
		for(int i = 0; i < options.size(); i++) {
			
		}
		
	}
	
	
	/**
	 * Add a method to check if this slide's index has been used before and if so throw
	 * an invalidArgumentException
	 */
	
	
	
	
}
