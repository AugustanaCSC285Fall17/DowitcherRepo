package edu.augustana.csc285.gamebuilder.condition;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class GenderConditionController extends GeneralConditionController {
	@FXML
	private ChoiceBox<Gender> genderChoiceCondition;
	@FXML
	private Button addGenderCondition;

	public void updateFields() {
		if (this.getCondition() != null) {
			GenderCondition genderCondition = (GenderCondition) this.getCondition();
			genderChoiceCondition.getSelectionModel().select(genderCondition.getGender());
		}
	}

	@FXML
	private void initialize() {
		this.genderChoiceCondition.setItems(FXCollections.observableArrayList(Gender.values()));
	}

	@FXML
	private void handleAddGenderCondition() {
		Gender genderChoice = this.genderChoiceCondition.getValue();
		if (checkCondition()) {
			Condition tempCondition = new GenderCondition(genderChoice);
			this.helpAddCondition(addGenderCondition, tempCondition);
		}
	}

	@Override
	boolean checkCondition() {
		return !this.genderChoiceCondition.getSelectionModel().isEmpty();
	}
}
