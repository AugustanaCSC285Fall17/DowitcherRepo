package edu.augustana.csc285.game.datamodel;

import java.io.File;
import java.util.ArrayList;

public class Slide {
	String image;
	String desc;
	String url;
	File music;
	int id;
	ArrayList<Option> options;
	/**
	 * @param image 
	 * @param desc 
	 * @param url 
	 * @param music 
	 * @param id 
	 * @param options 
	 */
	public Slide(String image, String desc, String url, File music, int id, ArrayList<Option> options) {
		super();
		this.image = image;
		this.desc = desc;
		this.url = url;
		this.music = music;
		this.id = id;
		this.options = options;
	}
	
	public Slide(String desc, String image) {
		this(image, desc, "", null, 0, null);
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
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
	
	
	public void addOption(int index, Option option) {
		options.add(index, option);
	}
	
	public void removeOption(int index) {
		options.remove(index);
	}

}
