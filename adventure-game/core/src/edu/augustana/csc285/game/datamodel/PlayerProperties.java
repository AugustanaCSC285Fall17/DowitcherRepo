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
	private Map<String, Property> properties;

	/**
	 * post: usually used to create a new copy of the properties
	 * 
	 * @param other
	 *            is the PlayerProperties
	 */
	public PlayerProperties(PlayerProperties other) {
		properties = new TreeMap<String, Property>(other.properties);
	}

	public PlayerProperties(int health, int morale, int gold, int day) {
		properties = new TreeMap<String, Property>();
		properties.put(PropertyType.HEALTH.getID(), new Property(PropertyType.HEALTH, health));
		properties.put(PropertyType.MORALE.getID(), new Property(PropertyType.MORALE, morale));
		properties.put(PropertyType.GOLD.getID(), new Property(PropertyType.GOLD, gold));
		properties.put(PropertyType.DAY.getID(), new Property(PropertyType.DAY, day));
	}

	/**
	 * post: create a default PlayerProperties, should only be used at beginning
	 * of game
	 */
	public PlayerProperties() {
		this(PropertyType.HEALTH.getValue(), PropertyType.MORALE.getValue(), PropertyType.GOLD.getValue(),
				PropertyType.DAY.getValue());
	}

	public Map<String, Property> getProperties() {
		return properties;
	}

	public void changePropertyQuantity(Property property, int newQuantity) {
		if (newQuantity < 0) {
			newQuantity = 0;
		}
		Property temp = new Property(property);
		temp.setQuantity(newQuantity);
		properties.put(temp.getID(), temp);
	}

	public int getPropertyQuantity(String propertyID) {
		Property temp = properties.get(propertyID);
		if (temp == null) {
			return 0;
		} else {
			return temp.getQuantity();
		}
	}

	public Property getProperty(String propertyID) {
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
