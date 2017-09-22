package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public class OptionController {

	/**
	 * 
	 * @return true if inventory and properties of player are greater than the
	 *         one in option
	 */
	public static boolean isVisible(Option option, Player player) {
		Inventory qualifyInventory = option.getQualifyInventory();
		PlayerProperties qualifyPlayerProperties = option.getQualifyPlayerProperties();
		return checkSatisfied(qualifyInventory, qualifyPlayerProperties, player);
	}

	/**
	 * 
	 * @param option
	 *            option to be applied to the player
	 * @param player:
	 *            the player to choose the option
	 */
	public static void applyOption(Option option, Player player) {
		Inventory subtractInventory = option.getSubtractInventory();
		PlayerProperties subtractPlayerProperties = option.getSubtractPlayerProperties();
		if (!canSubtract(option, player)) {
			throw new IllegalArgumentException("Cannot subtract because of quantity");
		} else {
			player.getInventory().subtractInventory(subtractInventory);
			player.getProperties().subtractProperties(subtractPlayerProperties);
			Inventory addInventory = option.getAddInventory();
			PlayerProperties addPlayerProperties = option.getAddPlayerProperties();
			player.getInventory().addInventory(addInventory);
			player.getProperties().addProperties(addPlayerProperties);
		}
	}

	/**
	 * 
	 * @param inventory:
	 *            Inventory to check in if the player has enough quantity
	 * @param properties:
	 *            PlayerProperties to check in if the player has enough quantity
	 * @param player:
	 *            the player to check
	 * @return true if the player has enough quantity
	 */
	private static boolean checkSatisfied(Inventory inventory, PlayerProperties properties, Player player) {
		if (player.getInventory().checkInventory(inventory) && player.getProperties().checkProperties(properties)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param option
	 * @param player
	 * @return true if the option inventory and properties in the subtract slot
	 *         can be subtracted from the player
	 */
	private static boolean canSubtract(Option option, Player player) {
		Inventory subtractInventory = option.getSubtractInventory();
		PlayerProperties subtractPlayerProperties = option.getSubtractPlayerProperties();
		return checkSatisfied(subtractInventory, subtractPlayerProperties, player);
	}

}
