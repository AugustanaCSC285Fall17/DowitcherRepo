package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public class Option {
	private String desc;
	private String transitionMessage;
	private String rejectMessage;
	private int nextSlideIndex;
	// optionInventory: addInventory,subtractInventory,qualifyInventory
	private Inventory[] optionInventory;
	// optionPlayerProperties: addProperties, subtractProperties,
	// qualifyProperties
	private PlayerProperties[] optionPlayerProperties;
	private String image;
	private String sound;

	private static final int SIZE = 3;
	private static final int INDEX_FOR_ADD = 0;
	private static final int INDEX_FOR_SUBTRACT = 1;
	private static final int INDEX_FOR_QUALIFY = 2;

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
	public Option(String desc, String transitionMessage, String rejectMessage, int nextSlideIndex, String image,
			String sound) {
		this.desc = desc;
		this.transitionMessage = transitionMessage;
		this.rejectMessage = rejectMessage;
		this.nextSlideIndex = nextSlideIndex;
		this.image = image;
		this.sound = sound;
		this.optionInventory = new Inventory[SIZE];
		this.optionPlayerProperties = new PlayerProperties[SIZE];
	}

	public Option(int nextSlideIndex) {
		this(null, null, null, nextSlideIndex, null, null);
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

	public int getNextSlideIndex() {
		return nextSlideIndex;
	}

	public void setNextSlideIndex(int nextSlideIndex) {
		this.nextSlideIndex = nextSlideIndex;
	}

	public Inventory getAddInventory() {
		return optionInventory[INDEX_FOR_ADD];
	}

	public Inventory getSubtractInventory() {
		return optionInventory[INDEX_FOR_SUBTRACT];
	}

	public Inventory getQualifyInventory() {
		return optionInventory[INDEX_FOR_QUALIFY];
	}

	public void setAddInventory(Inventory inventory) {
		optionInventory[INDEX_FOR_ADD] = inventory;
	}

	public void setSubtractInventory(Inventory inventory) {
		optionInventory[INDEX_FOR_SUBTRACT] = inventory;
	}

	public void setQualifyInventory(Inventory inventory) {
		optionInventory[INDEX_FOR_QUALIFY] = inventory;
	}

	public PlayerProperties getAddPlayerProperties() {
		return optionPlayerProperties[INDEX_FOR_ADD];
	}

	public PlayerProperties getSubtractPlayerProperties() {
		return optionPlayerProperties[INDEX_FOR_SUBTRACT];
	}

	public PlayerProperties getQualifyPlayerProperties() {
		return optionPlayerProperties[INDEX_FOR_QUALIFY];
	}

	public void setAddPlayerProperties(PlayerProperties properties) {
		optionPlayerProperties[INDEX_FOR_ADD] = properties;
	}

	public void setSubtractPlayerProperties(PlayerProperties properties) {
		optionPlayerProperties[INDEX_FOR_SUBTRACT] = properties;
	}

	public void setQualifyPlayerProperties(PlayerProperties properties) {
		optionPlayerProperties[INDEX_FOR_QUALIFY] = properties;
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

	public String toString() {
		String output = "Next Slide: " + nextSlideIndex + "\t";

		if (!(desc == null))
			output += "Desc: " + desc + "\t";
		if (!(transitionMessage == null))
			output += "Sucess Message: " + transitionMessage + "\t";
		if (!(rejectMessage == null))
			output += "Rejection Message:" + rejectMessage + "\t";
		if (!(optionInventory[INDEX_FOR_ADD] == null))
			output += "The Inventory to Add: " + optionInventory[INDEX_FOR_ADD] + "\t";
		if (!(optionInventory[INDEX_FOR_SUBTRACT] == null))
			output += "The Inventory to Subtract: " + optionInventory[INDEX_FOR_SUBTRACT] + "\t";
		if (!(optionInventory[INDEX_FOR_QUALIFY] == null))
			output += "The Inventory to Qualify: " + optionInventory[INDEX_FOR_QUALIFY] + "\t";
		if (!(optionPlayerProperties[INDEX_FOR_ADD] == null))
			output += "The Player Stats to Add: " + optionPlayerProperties[INDEX_FOR_ADD] + "\t";
		if (!(optionPlayerProperties[INDEX_FOR_SUBTRACT] == null) )
			output += "The Player Stats to Subtract: " + optionPlayerProperties[INDEX_FOR_SUBTRACT] + "\t";
		if (!(optionPlayerProperties[INDEX_FOR_QUALIFY] == null))
			output += "The Player Stats that Qualify: " + optionPlayerProperties[INDEX_FOR_QUALIFY] + "\t";
		if (!(image == null))
			output += output + "Image file: " + image + "\t";
		if (!(sound == null))
			output = output + "Music file: " + sound + "\t";

		return output;
	}

}
