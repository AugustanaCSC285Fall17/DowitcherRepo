package edu.augustana.csc285.game.datamodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Slide {
	private String image;
	private String desc;

	private String music;
	private String id;
	private String title;
	ArrayList<Option> options = new ArrayList<Option>();

	public Slide() {

	}

	/**
	 * This is a second constructor that includes the title of the slide
	 * 
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
	 * @param title
	 *            is the title of the slide
	 */
	public Slide(String image, String desc, String id, String title, String music) {
		this.image = image;
		this.desc = desc;
		this.music = music;
		this.id = id;
		this.title = title;
	}
		
	public Slide(String image, String desc, String id, String title) {
		this(image, desc, id, title, null);
	}

	/**
	 * This is a cloning method
	 */

	public Slide(Slide other) {
		this(other.image, other.desc, other.id, other.title, other.music);
		this.options = new ArrayList<Option>(other.options);
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	public ArrayList<Option> getOptions() {
		return options;
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
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            the title to set
	 */

	public void setTitle(String title) {
		this.title = title;
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

	public ArrayList<Option> getVisibleOptions(Player player) {
		ArrayList<Option> visibleOptions = new ArrayList<Option>();
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

	public void setOption(int index, Option option) {
		this.checkIndex(index);
		options.set(index, option);
	}

}
