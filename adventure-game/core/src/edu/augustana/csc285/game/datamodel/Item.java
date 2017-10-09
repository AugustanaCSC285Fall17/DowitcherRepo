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
	// image is the location in asset
	private String image;

	public Item() {

	}

	public Item(String name, String desc, int quantity, String image) {
		this.name = name;
		this.desc = desc;
		this.quantity = quantity;
		this.image = image;
	}

	public Item(Item other) {
		this(other.name, other.desc, other.quantity, other.image);
	}

	/**
	 * set the quantity to 0 and has an image
	 * 
	 * @param name:
	 *            name of the item
	 * @param desc:
	 *            description of the item
	 * @param image:
	 *            location in asset
	 */
	public Item(String name, String desc, String image) {
		this(name, desc, 0, image);
	}

	/**
	 * set the quantity to the pass in value and set image to null
	 * 
	 * @param name:
	 *            name of the item
	 * @param desc:
	 *            description of the item
	 * @param image:
	 *            location in asset
	 */

	public Item(String name, String desc, int quantity) {
		this(name, desc, quantity, null);
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * @return: true if the item is the same type of item, always false if other
	 * is null
	 */
	public boolean sameType(Item other) {
		if (other == null) {
			return false;
		}
		return (other.name.equals(name));
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
		int temp = quantity + other.quantity;
		if (temp < 0) {
			temp = 0;
		}
		quantity = temp;
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
		return name + ": " + quantity;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
