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
	private Inventory[] optionInventory = new Inventory[3];
	// optionPlayerProperties: addProperties, subtractProperties,
	// qualifyProperties
	private PlayerProperties[] optionPlayerProperties = new PlayerProperties[3];
	private String image;
	private String sound;

	private static final int INDEX_FOR_ADD = 0;
	private static final int INDEX_FOR_SUBTRACT = 1;
	private static final int INDEX_FOR_QUALIFY = 2;

	public Option(int nextSlideIndex) {
		this.nextSlideIndex = nextSlideIndex;
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

	public Inventory[] getOptionInventory() {
		return optionInventory;
	}

	public void setOptionInventory(Inventory[] optionInventory) {
		if (optionInventory.length != 3) {
			throw new IllegalArgumentException("optionInventory needs to have size of 3");
		}
		this.optionInventory = optionInventory;
	}

	public PlayerProperties[] getOptionPlayerProperties() {
		return optionPlayerProperties;
	}

	public void setOptionPlayerProperties(PlayerProperties[] optionPlayerProperties) {
		if (optionPlayerProperties.length != 3) {
			throw new IllegalArgumentException("optionPlayerProperties needs to have size of 3");
		}
		this.optionPlayerProperties = optionPlayerProperties;
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
