package edu.augustana.csc285.game.datamodel;

public class Property {
	private PropertyType type;
	private int quantity;
	private int id;

	public Property() {

	}

	public Property(Property other) {
		this(other.type, other.quantity);
	}

	// set the value of property to default
	public Property(PropertyType type) {
		this(type, type.getValue());
	}

	public Property(PropertyType type, int quantity) {
		this.type = type;
		this.quantity = quantity;
		this.id = type.getID();
	}

	public PropertyType getType() {
		return type;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getID() {
		return id;
	}

	public String toString() {
		return (type.toString() + " (id: " + id + "): " + quantity);
	}

}
