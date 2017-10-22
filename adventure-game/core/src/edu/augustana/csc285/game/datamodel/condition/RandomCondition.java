package edu.augustana.csc285.game.datamodel.condition;

import java.util.Random;

import edu.augustana.csc285.game.datamodel.Player;

public class RandomCondition implements Condition {
	private double percentage;
	private Random rand;
	private String conditionType = "Random Condition";

	public RandomCondition() {

	}

	public RandomCondition(double percentage) {
		this.percentage = percentage;
		this.rand = new Random();
	}

	public String getConditionType() {
		return conditionType;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public boolean checkCondition(Player player) {
		double temp = rand.nextDouble();
		return (temp > (percentage / 100));
	}

	public String toString() {
		return conditionType + ": " + percentage;
	}

}
