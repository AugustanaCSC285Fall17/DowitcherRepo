package edu.augustana.csc285.game.datamodel;

import java.util.Random;

public class RandomCondition implements Condition {
	private double percentage;
	private Random rand;

	public RandomCondition() {

	}

	public RandomCondition(double percentage) {
		this.percentage = percentage;
		this.rand = new Random();
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

}
