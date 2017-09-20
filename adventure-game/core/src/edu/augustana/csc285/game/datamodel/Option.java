package edu.augustana.csc285.game.datamodel;


public class Option {
	private String desc;
	private String transitionMessage;
	private String rejectMessage;
	private int nextSlideIndex;
	// optionInventory: addInventory,subtractInventory,qualifyInventory
	private Inventory[] optionInventory = new Inventory[3];
	// optionPlayerProperties: addProperties, subtractProperties, qualifyProperties
	private PlayerProperties[] optionPlayerProperties = new PlayerProperties[3];
	private String image;
	private String sound;
	
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
		if (optionInventory.length !=3) {
			throw new IllegalArgumentException("optionInventory needs to have size of 3");
		}
		this.optionInventory = optionInventory;
	}

	public PlayerProperties[] getOptionPlayerProperties() {
		return optionPlayerProperties;
	}

	public void setOptionPlayerProperties(PlayerProperties[] optionPlayerProperties) {
		if (optionPlayerProperties.length !=3) {
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
	
	
	
}
