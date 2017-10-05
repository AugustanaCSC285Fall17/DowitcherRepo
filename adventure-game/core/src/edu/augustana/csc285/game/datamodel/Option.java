package edu.augustana.csc285.game.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Dat Tran
 *
 */
public class Option {
	public static final String DEFAULT_REJECT_MESSAGE = "You are not allowed to choose this.";
	private String desc;
	private String transitionMessage;
	private String rejectMessage;
	private String nextSlideIndex;
	private List<Effect> effects;
	private List<Condition> visibleConditions;
	private List<Condition> feasibleConditions;
	private String image;
	private String sound;

	public Option() {

	}

	/**
	 * 
	 * @param desc:
	 *            String to describe the context of the option
	 * @param transitionMessage:
	 *            String to pop up when transition (optional)
	 * @param rejectMessage:
	 *            String to pop up when the option is reject (optional)
	 * @param nextSlideIndex:
	 *            int to represent the slide theat the option linked to (at the
	 *            moment it is required)
	 * @param image:
	 *            String to represent image for the option
	 * @param sound:
	 *            String to represent sound when the option is chose
	 */
	public Option(String desc, String transitionMessage, String rejectMessage, String nextSlideIndex, String image,
			String sound) {
		this.desc = desc;
		this.transitionMessage = transitionMessage;
		this.rejectMessage = rejectMessage;
		this.nextSlideIndex = nextSlideIndex;
		this.image = image;
		this.sound = sound;
		effects = new ArrayList<Effect>();
		visibleConditions = new ArrayList<Condition>();
		feasibleConditions = new ArrayList<Condition>();
	}

	public Option(String desc, String nextSlideIndex) {
		this(desc, null, DEFAULT_REJECT_MESSAGE, nextSlideIndex, null, null);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTransitionMessage() {
		return transitionMessage;
	}

	public void setTransitionMessage(String transitionMessage) {
		this.transitionMessage = transitionMessage;
	}

	public String getRejectMessage() {
		return rejectMessage;
	}

	public void setRejectMessage(String rejectMessage) {
		this.rejectMessage = rejectMessage;
	}

	public String getNextSlideIndex() {
		return nextSlideIndex;
	}

	public void setNextSlideIndex(String nextSlideIndex) {
		this.nextSlideIndex = nextSlideIndex;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public void addEffect(Effect effect) {
		effects.add(effect);
	}

	public void addVisibleCondition(Condition condition) {
		visibleConditions.add(condition);
	}

	public void addFeasibleCondition(Condition condition) {
		feasibleConditions.add(condition);
	}

	public void removeEffect(int index) {
		checkIndex(index, effects);
		effects.remove(index);
	}

	public void removeVisibleCondition(int index) {
		checkIndex(index, visibleConditions);
		visibleConditions.remove(index);
	}

	public void removeFeasibleCondition(int index) {
		checkIndex(index, feasibleConditions);
		feasibleConditions.remove(index);
	}

	// don't parameterize list
	public void checkIndex(int index, List list) {
		if (index < 0 || index >= list.size()) {
			throw new IllegalArgumentException("index: " + index + " is out of bound");
		}
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public List<Condition> getVisibleConditions() {
		return visibleConditions;
	}

	public List<Condition> getFeasibleConditions() {
		return feasibleConditions;
	}

	public boolean isVisible(Player player) {
		for (Condition condition : visibleConditions) {
			if (!condition.checkCondition(player)) {
				return false;
			}
		}
		return true;
	}

	public boolean isFeasible(Player player) {
		for (Condition condition : feasibleConditions) {
			if (!condition.checkCondition(player)) {
				return false;
			}
		}
		return true;
	}

	public void applyEffects(Player player) {
		for (Effect effect : effects) {
			effect.applyEffect(player);
		}
	}

	public String toString() {
		String output = "Next Slide: " + nextSlideIndex + "\t";

		if (!(desc == null))
			output += "Desc: " + desc + "\t";
		if (!(transitionMessage == null))
			output += "Sucess Message: " + transitionMessage + "\t";
		if (!(rejectMessage == null))
			output += "Rejection Message:" + rejectMessage + "\t";
		output += "\nEffects: \n";
		for (Effect effect : effects) {
			output += effect + "\n";
		}
		output += "Visible conditions: \n";
		for (Condition condition : visibleConditions) {
			output += condition + "\n";
		}
		output += "Feasible conditions: \n";
		for (Condition condition : feasibleConditions) {
			output += condition + "\n";
		}
		if (!(image == null))
			output += output + "Image file: " + image + "\t";
		if (!(sound == null))
			output = output + "Music file: " + sound + "\t";

		return output;
	}

}
