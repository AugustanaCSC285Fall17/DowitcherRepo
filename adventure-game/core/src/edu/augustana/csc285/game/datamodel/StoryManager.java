package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public class StoryManager {
	private Story story;
	private Player player;
	private int currentSlideIndex;

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
		story.checkID(index);
		this.currentSlideIndex = index;
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

	public int getCurrentSlideIndex() {
		return currentSlideIndex;
	}

	/**
	 * 
	 * @return a copy of the current slide in story that manager is on for
	 *         displaying purpose
	 * @throws IllegalArgumentException
	 *             if index is invalid which should not happen
	 */
	public Slide getCurrentSlide() {
		return story.getSlide(currentSlideIndex);
	}

	/**
	 * 
	 * @param optionIndex
	 *            is the index for the option in the list of options in the
	 *            current slide
	 * @return Option object corresponding to the optionIndex
	 */
	public Option getCurrentSlideOption(int optionIndex) {
		return getCurrentSlide().getOption(optionIndex);
	}

	/**
	 * post: perform the option on the player and change the properties and
	 * inventory correspondingly
	 * 
	 * @param optionIndex
	 *            the index of the option in the current slide
	 * @throws: IllegalArgumentException
	 *              if option is not visible since the player doesn't satisfy
	 *              the visibility requirement
	 */
	public void chooseOption(int optionIndex) {
		if (!checkVisible(optionIndex)) {
			throw new IllegalArgumentException("Option is not visible");
		} else {
			Option option = getCurrentSlideOption(optionIndex);
			OptionController.applyOption(option, player);
			currentSlideIndex = option.getNextSlideIndex();
		}
	}

	/**
	 * 
	 * @param optionIndex
	 *            the index of the option in the current slide
	 * @return true if an option is visible to the player
	 */
	public boolean checkVisible(int optionIndex) {
		Option option = getCurrentSlideOption(optionIndex);
		return OptionController.isVisible(option, player);
	}

	public String toString() {
		return "StoryManager is current at index " + currentSlideIndex;
	}
}
