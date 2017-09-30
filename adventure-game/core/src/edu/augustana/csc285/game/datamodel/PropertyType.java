package edu.augustana.csc285.game.datamodel;

/**
 * 
 * @author Dat Tran
 *
 */
public enum PropertyType {
	HEALTH(10,0), 
	MORALE(10,1), 
	GOLD(400,2), 
	DAY(0,3),
	GENDER(0,4);
	private int default_value;
	private int default_id;
	// this should not be used only to make it compilable
    private PropertyType(int value,int id) {
        this.default_value = value;
        this.default_id = id;
    }
    public int getValue() {
        return this.default_value;
    }
    public int getID() {
    	return this.default_id;
    }
	public String toString() {
		return this.name().toLowerCase();
	}
	public static void main (String [] args) {
		PropertyType temp = PropertyType.GOLD;
		System.out.println(temp.getValue());
		System.out.println(temp.getID());
	}
}
