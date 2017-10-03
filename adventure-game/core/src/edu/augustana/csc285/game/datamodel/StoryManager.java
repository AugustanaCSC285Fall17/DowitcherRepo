package edu.augustana.csc285.game.datamodel;

import java.util.List;

/**
 * 
 * @author Dat Tran
 *
 */
public class StoryManager {
	private Story story;
	private Player player;
	private Slide currentSlide;

	public StoryManager() {

	}

	/**
	 * 
	 * @param story
	 *            Load a story
	 * @param player
	 *            Load a player
	 * @param index
	 *            the starting index
	 */
	public StoryManager(Story story, Player player, int index) {
		this.story = story;
		this.player = player;
		//story.checkID(index);
		currentSlide = this.story.getSlide(index);
	}

	/**
	 * 
	 * @param story
	 *            Load a story
	 * @param name
	 *            String to represent name of the player to initialize the
	 *            Player
	 * @param index
	 *            the starting index
	 */
	public StoryManager(Story story, String name, int index) {
		this(story, new Player(name), index);
	}

	public Story getStory() {
		return story;
	}

	public Player getPlayer() {
		return player;
	}

	public Slide getCurrentSlide() {
		return currentSlide;
	}

	public List<Option> getCurrentSlideVisibleOptions() {
		return currentSlide.getVisibleOptions(player);
	}

	public void applyOption(Option option) {
		if (!option.isFeasible(player)) {
			throw new IllegalArgumentException("That option is not feasible");
		} else {
			option.applyEffects(player);
			currentSlide = story.getSlide(option.getNextSlideIndex());

		}
	}

	public String toString() {
		return "StoryManager is current at index " + currentSlide.getId();
	}
}
