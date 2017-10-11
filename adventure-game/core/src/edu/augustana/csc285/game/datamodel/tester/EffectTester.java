package edu.augustana.csc285.game.datamodel.tester;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.game.datamodel.effect.EffectOperation;

public class EffectTester {
	public static void main(String[] args) {
		Player p = new Player("Dat");
		Item sek = new Item("Sek", "Currency of Sweden", 1, null);
		Effect lose20Sek = new ItemEffect(new Item(sek, 20), EffectOperation.MINUS);
		System.out.println(p.getInventory().getItemQuantity(sek));
		EffectOperation subtract = EffectOperation.MINUS;
		System.out.println(p.getInventory().getCollection().contains(sek));
		p.getInventory().changeItemQuantity(sek, 800);
		System.out.println(p.getInventory());
		System.out.println(p);
	}
}
