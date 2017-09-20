package edu.augustana.csc285.game.datamodel;
/**
 * 
 * @author Dat Tran
 *
 */
public class OptionController {
	public static final int INDEX_FOR_ADD = 0;
	public static final int INDEX_FOR_SUBTRACT = 1;
	public static final int INDEX_FOR_QUALIFY = 2;

	/**
	 * 
	 * @return true if inventory and properties of player are greater than the
	 *         one in option
	 */
	public static boolean isVisible(Option option, Player player) {
		Inventory qualifyInventory = option.getOptionInventory()[INDEX_FOR_QUALIFY];
		PlayerProperties qualifyPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_QUALIFY];
		if (player.getInventory().checkInventory(qualifyInventory)
				&& player.getProperties().checkProperties(qualifyPlayerProperties)) {
			return true;
		} else {
			return false;
		}
	}

	public static void applyOption(Option option, Player player) {
		Inventory addInventory = option.getOptionInventory()[INDEX_FOR_ADD];
		PlayerProperties addPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_ADD];
		Inventory subtractInventory = option.getOptionInventory()[INDEX_FOR_SUBTRACT];
		PlayerProperties subtractPlayerProperties = option.getOptionPlayerProperties()[INDEX_FOR_SUBTRACT];
		player.getInventory().addInventory(addInventory);
		player.getProperties().addProperties(addPlayerProperties);
		player.getInventory().subtractInventory(subtractInventory);
		player.getProperties().subtractProperties(subtractPlayerProperties);
	}

}
