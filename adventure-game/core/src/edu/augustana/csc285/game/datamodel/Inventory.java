import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
	private HashMap<Integer, Item> collection;

	public Inventory(HashMap<Integer, Item> collection) {
		setCollection(collection);
	}

	public Inventory(Inventory other) {
		this.collection = other.collection;
	}

	public Inventory() {
		collection = new HashMap<Integer, Item>();
	}

	public HashMap<Integer, Item> getCollection() {
		return collection;
	}

	public void setCollection(HashMap<Integer, Item> collection) {
		this.collection = collection;
	}

	/*
	 * post: add item to the inventory
	 */
	public void addItem(Item item) {
		Item temp = collection.get(item.getID());
		// if item already exist in Inventory
		if (temp != null) {
			temp.addItem(item);
			collection.put(temp.getID(), temp);
		} else {
			collection.put(item.getID(), new Item(item));
		}
	}

	/*
	 * post: remove item from the inventory, make no change if the item is not
	 * in collection
	 */
	public void removeItem(Item item) {
		collection.remove(item.getID());
	}

	/*
	 * post: add Inventory other to this inventory if inventory other is empty,
	 * do nothing
	 */
	public void addInventory(Inventory other) {
		if (other != null && other.collection != null) {
			for (int id : other.collection.keySet()) {
				Item temp1 = collection.get(id);
				Item temp2 = collection.get(id);
				if (temp1 != null) {
					temp1.addItem(temp2);
				} else {
					temp1 = temp2;
				}
				collection.put(id, temp1);
			}
		}
	}

	/*
	 * post: subtract Inventory other from this do nothing if other is empty
	 * 
	 * @ throw: IllegalArgumentException if there is at least one item with not
	 * enough quantity
	 * 
	 */
	public void subtractInventory(Inventory other) {
		if (!checkInventory(other)) {
			throw new IllegalArgumentException("There is not enough items");
		} else {
			if (other != null && other.collection != null) {
				for (int id : other.collection.keySet()) {
					Item temp1 = collection.get(id);
					Item temp2 = other.collection.get(id);
					temp1.subtractItem(temp2);
					collection.put(id, temp1);
				}
			}
		}
	}

	/*
	 * @return: true if this has greater quantity in every item than Inventory
	 * other, also true if Inventory other is null
	 */
	public boolean checkInventory(Inventory other) {
		if (other != null) {
			for (int id : other.collection.keySet()) {
				Item temp1 = collection.get(id);
				Item temp2 = other.collection.get(id);
				if (temp1 == null) {
					return false;
				} else {
					if (!temp1.compareQuantity(temp2)) {
						return false;
					}
				}
			}
			return true;
		}
		return true;
	}

	public String toString() {
		/*
		 * ArrayList<String> str = new ArrayList<String>(); for (Item item :
		 * collection.values()) { str.add(item.getName()); }
		 */
		return collection.toString();
	}
}
