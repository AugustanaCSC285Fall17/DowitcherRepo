package edu.augustana.csc285.gamebuilder.condition;

import edu.augustana.csc285.game.datamodel.condition.Condition;
import edu.augustana.csc285.game.datamodel.condition.RandomCondition;
import edu.augustana.csc285.gamebuilder.Helper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RandomConditionController extends GeneralConditionController {
	@FXML
	private TextField percentageCondition;
	@FXML
	private Button addRandomCondition;

	public void updateFields() {
		if (getCondition() != null) {
			RandomCondition randomCondition = (RandomCondition) getCondition();
			percentageCondition.setText(randomCondition.getPercentage() + "");
		}
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddRandomCondition() {
		String percentage = this.percentageCondition.getText();
		if (checkCondition()) {
			Condition tempCondition = new RandomCondition(Double.parseDouble(percentage));
			this.helpAddCondition(addRandomCondition, tempCondition);
		}

	}

	@Override
	boolean checkCondition() {
		return Helper.checkLegalDouble(this.percentageCondition.getText());
	}
}
