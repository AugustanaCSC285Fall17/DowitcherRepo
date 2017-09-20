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
		optionInventory = new Inventory[SIZE];
		optionPlayerProperties = new PlayerProperties[SIZE];
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
		String output = desc + ": Sucess Message: " + transitionMessage + "\tRejection Message:" + rejectMessage
				+ "\tNext Slide: " + nextSlideIndex;
		if (!(optionInventory[INDEX_FOR_ADD] == null)) {
			output = output + "\tThe Inventory to Add: " + optionInventory[INDEX_FOR_ADD].toString();
		}
		if (!(optionInventory[INDEX_FOR_SUBTRACT] == null)) {
			output = output + "\tThe Inventory to Subtract: " + optionInventory[INDEX_FOR_SUBTRACT].toString();
		}
		if (!(optionInventory[INDEX_FOR_QUALIFY] == null)) {
			output = output + "\tThe Inventory to Qualify: " + optionInventory[INDEX_FOR_QUALIFY].toString();
		}

		if (!(optionPlayerProperties[INDEX_FOR_ADD] == null)) {
			output = output + "\tThe Player Stats to Add: " + optionPlayerProperties[INDEX_FOR_ADD].toString();
		}
		if (!(optionPlayerProperties[INDEX_FOR_SUBTRACT] == null)) {
			output = output + "\tThe Player Stats to Subtract: "
					+ optionPlayerProperties[INDEX_FOR_SUBTRACT].toString();
		}
		if (!(optionPlayerProperties[INDEX_FOR_QUALIFY] == null)) {
			output = output + "\tThe Player Stats that Qualify: "
					+ optionPlayerProperties[INDEX_FOR_QUALIFY].toString();
		}
		output = output + "\t Image file: " + image;
		output = output + "\t Music file: " + sound;
		return output;
	}

}
