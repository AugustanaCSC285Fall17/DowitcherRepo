package edu.augustana.csc285.game.datamodel;

import java.util.Comparator;

public class ComparatorForStringID implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		int i1 = Integer.parseInt(o1);
		int i2 = Integer.parseInt(o2);
		return (i1 - i2);
	}

}
