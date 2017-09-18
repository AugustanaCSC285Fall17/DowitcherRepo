
public class OptionController {
	private Option option;
	private Player player;
	public static final int INDEX_FOR_ADD = 0;
	public static final int INDEX_FOR_SUBTRACT = 1;
	public static final int INDEX_FOR_QUALIFY = 2;

	public OptionController(Option option, Player player) {
		this.option = option;
		this.player = player;
	}

	public OptionController(OptionController other) {
		this.option = other.option;
		this.player = other.player;
	}
	/**
	 * 
	 * @return true if inventory and properties of player are greater than the one in option
	 */
	public boolean isVisible() {
		Inventory qualifyInventory = option.getOptionInventory()[INDEX_FOR_QUALIFY];
		PlayerProperties qualifyPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_QUALIFY];
		if (player.getInventory().checkInventory(qualifyInventory)
				&& player.getProperties().checkProperties(qualifyPlayerProperties)) {
			return true;
		} else {
			return false;
		}
	}
	public void applyOption() {
		Inventory addInventory = option.getOptionInventory()[INDEX_FOR_ADD];
		PlayerProperties addPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_ADD];
		Inventory subtractInventory = option.getOptionInventory()[INDEX_FOR_SUBTRACT];
		PlayerProperties subtractPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_SUBTRACT];
		player.getInventory().addInventory(addInventory);
		player.getProperties().addProperties(addPlayerProperties);
		player.getInventory().subtractInventory(subtractInventory);
		player.getProperties().subtractProperties(subtractPlayerProperties);
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
