package edu.augustana.csc285.game.datamodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 
 * @author Dat Tran
 *
 */

import java.util.Set;
import java.util.TreeMap;

public class PlayerProperties {
	private Map<Integer, Property> properties;


	/**
	 * post: usually used to create a new copy of the properties
	 * 
	 * @param other
	 *            is the PlayerProperties
	 */
	public PlayerProperties(PlayerProperties other) {
		properties = new TreeMap<Integer, Property>(other.properties);
	}

	public PlayerProperties(int health, int morale, int gold, int day, int gender) {
		properties = new TreeMap<Integer, Property>();
		properties.put(PropertyType.HEALTH.getID(), new Property(PropertyType.HEALTH, health));
		properties.put(PropertyType.MORALE.getID(), new Property(PropertyType.MORALE, morale));
		properties.put(PropertyType.GOLD.getID(), new Property(PropertyType.GOLD, gold));
		properties.put(PropertyType.DAY.getID(), new Property(PropertyType.DAY, day));
		properties.put(PropertyType.GENDER.getID(), new Property(PropertyType.GENDER, gender));
	}

	/**
	 * post: create a default PlayerProperties, should only be used at beginning
	 * of game
	 */
	public PlayerProperties() {
		this(PropertyType.HEALTH.getValue(), PropertyType.MORALE.getValue(), PropertyType.GOLD.getValue(),
				PropertyType.DAY.getValue(), PropertyType.GENDER.getValue());
	}

	public void changePropertyQuantity(Property property, int newQuantity) {
		if (newQuantity < 0) {
			newQuantity = 0;
		}
		Property temp = new Property(property);
		temp.setQuantity(newQuantity);
		properties.put(temp.getID(), temp);
	}

	public int getPropertyQuantity(int propertyID) {
		Property temp = properties.get(propertyID);
		if (temp == null) {
			return 0;
		} else {
			return temp.getQuantity();
		}
	}

	public Property getProperty(int propertyID) {
		if (!properties.containsKey(propertyID)) {
			throw new IllegalArgumentException("PropertyID is not valid");
		} else {
			return properties.get(propertyID);
		}
	}

	public void addProperty(Property property) {
		if (property != null) {
			if (properties.containsKey(property.getID())) {
				Property original = properties.get(property.getID());
				int newQuantity = original.getQuantity() + property.getQuantity();
				if (newQuantity < 0) {
					newQuantity = 0;
				}
				original.setQuantity(newQuantity);
			} else {
				Property temp = new Property(property);
				if (temp.getQuantity() < 0) {
					temp.setQuantity(0);
				}
				properties.put(temp.getID(), temp);
			}
		}
	}

	public String toString() {
		String output = "";
		for (Property temp : properties.values()) {
			output += temp.toString() + "\n";
		}
		return output;
	}
}
