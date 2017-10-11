package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public class Player {
	private String name;
	private Inventory inventory;
	private Gender gender;
	private boolean gameOver;
	private PlayerProperties properties;

	public Player() {

	}

	public Player(String name, Inventory inventory, PlayerProperties properties, boolean gameOver, Gender gender) {
		this.name = name;
		this.inventory = inventory;
		this.properties = properties;
		this.gameOver = gameOver;
		this.gender = gender;
		PlayerHelper();
	}
	
	private void PlayerHelper() {
		Item sek = new Item("Sek", "Currency of Sweden", 1000, null);
		Item dollar = new Item("Dollar", "Currency of USA", 1000, null);
		inventory.addItem(sek);
		inventory.addItem(dollar);
	}

	public Player(String name) {
		this(name, new Inventory(), new PlayerProperties(), false, Gender.UNKNOWN);
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public PlayerProperties getProperties() {
		return properties;
	}

	public void setProperties(PlayerProperties properties) {
		this.properties = properties;
	}

	public String toString() {
		return "Player " + name + "\nInventory:\n" + inventory + "\nProperties:\n" + properties;
	}
}
