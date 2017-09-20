package edu.augustana.csc285.game.datamodel;

public class StoryManager {
	private Story story;
	private Player player;
	private int index;
	public StoryManager(Story story,Player player,int index) {
		this.story = story;
		this.player = player;
		this.index = index;
	}
	public Slide getCurrentSlide() {
		return new Slide (story.getSlide(index));
	}
	public void chooseOption(int optionIndex) {
		Option option = getCurrentSlide().getOption(optionIndex);
		OptionController.
	}
}
