package edu.augustana.csc285.game.datamodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Slide {
	private String image;
	private String desc;
	
	//Deteriorated
	private String url;
	
	private String music;
	private String id;
	ArrayList<Option> options;

	public Slide() {

	}

	/**
	 * @param image
	 *            is the name of the image
	 * @param desc
	 *            is the description of the slide that will appear on the screen
	 * @param url
	 *            is the link to the more information page on each slide
	 * @param music
	 *            is the name of the music file
	 * @param id
	 *            is the unique identifier for the slide
	 * @param options
	 *            is the group of options the player could have access to on
	 *            this slide
	 */
	public Slide(String image, String desc, String url, String music, String id) {
		this.image = image;
		this.desc = desc;
		this.url = url;
		this.music = music;
		this.id = id;
		this.options = new ArrayList<Option>();
	}

	public Slide(String image, String desc, String id) {
		this(image, desc, "", null, id);
	}

	/**
	 * This is a cloning method
	 */

	public Slide(Slide other) {
		this(other.image, other.desc, other.url, other.music, other.id);
		this.options = new ArrayList<Option>(other.options);
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
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
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the url
	 * Deteriorated
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the music
	 */
	public String getMusic() {
		return music;
	}

	/**
	 * @param music
	 *            the music to set
	 */
	public void setMusic(String music) {
		this.music = music;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the options
	 */
	private void checkIndex(int index) {
		if (options.size() <= index || index < 0) {
			throw new IllegalArgumentException("This index is out of bounds!");
		}
	}

	public Option getOption(int index) {
		checkIndex(index);
		return options.get(index);
	}

	/**
	 * Adds an option to this slide
	 * 
	 * @param index
	 *            is the index of the option
	 * @param option
	 *            is the option you wish to add
	 */
	public void addOption(Option option) {
		options.add(option);
	}

	/**
	 * Removes an option from this slide
	 * 
	 * @param index
	 *            is the index of the slide
	 */
	public void removeOption(int index) {
		checkIndex(index);
		options.remove(index);
	}

	public List<Option> getVisibleOptions(Player player) {
		List<Option> visibleOptions = new ArrayList<Option>();
		for (Option option : options) {
			if (option.isVisible(player)) {
				visibleOptions.add(option);
			}
		}
		return visibleOptions;
	}

	public String toString() {
		String output = "Current slide " + id + " Image: " + image + " Desc: " + desc + "\n\n";

		for (Option option : options) {
			output += option.toString() + "\n";
		}

		return output;
	}

}
