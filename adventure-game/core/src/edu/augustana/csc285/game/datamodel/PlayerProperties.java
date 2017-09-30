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

public class PlayerProperties {
	private Map<Integer,Property> properties;

	/**
	 * post: usually used to create a new copy of the properties
	 * 
	 * @param other
	 *            is the PlayerProperties
	 */
	public PlayerProperties(PlayerProperties other) {
		properties = new HashMap<Integer,Property>(other.properties);
	}

	public PlayerProperties(int health, int morale, int gold, int day, int gender) {
		properties = new HashMap<Integer,Property>();
		properties.put(PropertyType.HEALTH.getID(),new Property(PropertyType.HEALTH, health));
		properties.put(PropertyType.MORALE.getID(),new Property(PropertyType.MORALE, morale));
		properties.put(PropertyType.GOLD.getID(),new Property(PropertyType.GOLD, gold));
		properties.put(PropertyType.DAY.getID(),new Property(PropertyType.DAY, day));
		properties.put(PropertyType.GENDER.getID(),new Property(PropertyType.GENDER, gender));
	}

	/**
	 * post: create a default PlayerProperties, should only be used at beginning
	 * of game
	 */
	public PlayerProperties() {
		this(PropertyType.HEALTH.getValue(), PropertyType.MORALE.getValue(), PropertyType.GOLD.getValue(),
				PropertyType.DAY.getValue(), PropertyType.GENDER.getValue());
	}
	
	public void changePropertyQuantity(Property property,int newQuantity) {
		if (newQuantity<0) {
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
		}
		else {
			return temp.getQuantity();
		}
	}

	/**
	 * 
	 * @param other:
	 *            other properties set, usually in the option to see if it is
	 *            visible
	 * @return: true if this has greater quantity in each element than other
	 */
	public boolean checkProperties(PlayerProperties other) {
		if (other.properties != null) {
			for (PropertyType property : other.properties.keySet()) {
				Integer temp = other.properties.get(property);
				if (temp != null) {
					if (properties.get(property) == null || temp > properties.get(property)) {
						return false;
					}
				}
			}
			return true;
		}
		return true;
	}

	/**
	 * @param other:
	 *            other properties set, usually in the option to update player
	 *            properties post: add properties to this by the properties in
	 *            other
	 */
	public void addProperties(PlayerProperties other) {
		if (other != null && other.properties != null) {
			for (PropertyType property : other.properties.keySet()) {
				Integer temp1 = other.properties.get(property);
				Integer temp2 = properties.get(property);
				if (temp1 != null) {
					temp1 += temp2;
				} else {
					temp1 = temp2;
				}
				properties.put(property, temp1);
			}
		}
	}

	/**
	 * 
	 * @param other:
	 *            other properties set, usually in the option to update player
	 *            properties post: subtract properties from this by the
	 *            properties in other
	 * @throws IllegalArgumentException
	 *             if there is not enough in this to subtract from
	 */
	public void subtractProperties(PlayerProperties other) {
		if (!checkProperties(other)) {
			throw new IllegalArgumentException("other properties is greater than this properties");
		} else {
			if (other != null && other.properties != null) {
				for (PropertyType property : other.properties.keySet()) {
					Integer temp1 = other.properties.get(property);
					Integer temp2 = properties.get(property);
					properties.put(property, temp1 - temp2);
				}
			}
		}
	}

	public String toString() {
		String output = "";
		Set<PropertyType> propertySet = properties.keySet();
		for (PropertyType index : propertySet) {
			output = "The index is: " + index + "\t The vaues is: " + properties.get(index);
		}

		return output;
	}
}
