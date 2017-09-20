package edu.augustana.csc285.game.datamodel;
/**
 * 
 * @author Dat Tran
 *
 */
public class Item {
	private String name;
	private String desc;
	private int quantity;
	private int id;
	// image is the location in asset
	private String image;

	public Item(String name, String desc, int quantity, int id, String image) {
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.id = id;
		this.image = image;
	}
	public Item(Item other) {
		this(other.name,other.desc,other.quantity,other.id,other.image);
	}

	/**
	 * set the quantity to 0 and has an image
	 * 
	 * @param name:
	 *            name of the item
	 * @param desc:
	 *            description of the item
	 * @param id:
	 *            identification number of item
	 * @param image:
	 *            location in asset
	 */
	public Item(String name, String desc, int id, String image) {
		this(name, desc, 0, id, image);
	}

	/**
	 * set the quantity to the pass in value and set image to null
	 * 
	 * @param name:
	 *            name of the item
	 * @param desc:
	 *            description of the item
	 * @param id:
	 *            identification number of item
	 * @param image:
	 *            location in asset
	 */
	public Item(String name, String desc, int quantity, int id) {
		this(name, desc, quantity, id, null);
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getID() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setQty(int quantity) {
		this.quantity = quantity;
	}

	public void setID(int id) {
		this.id = id;
	}

	/*
	 * @return: true if the item is the same type of item, always false if other
	 * is null
	 */
	public boolean sameType(Item other) {
		if (other == null) {
			return false;
		}
		return (other.id == id);
	}

	/**
	 * @throws: IllegalArgumentException
	 *              if type doesn't match
	 * @param other:
	 *            The item to add to this post: add the quantity of other to
	 *            this
	 */

	public void addItem(Item other) {
		checkType(other);
		quantity += other.quantity;
	}

	/**
	 * @throws: IllegalStateException
	 *              if the quantity is negative after subtraction
	 * @throws: IllegalArgumentException
	 *              if type doesn't match
	 * @param other:
	 *            The item to subtract from this post: subtract the quantity of
	 *            other from this
	 */
	public void subtractItem(Item other) {
		if (!compareQuantity(other)) {
			throw new IllegalStateException("item " + id + "'s quantity cannot be negative");
		} else {
			quantity -= other.quantity;
		}
	}

	/**
	 * 
	 * @param other:
	 *            The other item needed to compare quantity with
	 * @return: true if this's quantity is greater
	 * @throws: IllegalArgumentException
	 *              if the type is not the same
	 */
	public boolean compareQuantity(Item other) {
		checkType(other);
		return (quantity >= other.quantity);
	}

	private void checkType(Item other) {
		if (!sameType(other)) {
			throw new IllegalArgumentException("Item is not the same type!");
		}
	}

	public String toString() {
		return "Item " + name + "(with ID: " + id + "): " + quantity;
	}
}
