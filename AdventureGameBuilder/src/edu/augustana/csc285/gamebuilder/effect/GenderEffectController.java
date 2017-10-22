package edu.augustana.csc285.gamebuilder.effect;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class GenderEffectController extends GeneralEffectController {
	@FXML
	private ChoiceBox<Gender> genderChoiceEffect;
	@FXML
	private Button addGenderEffect;

	public void updateFields() {
		if (this.effect != null) {
			GenderEffect genderEffect = (GenderEffect) this.effect;
			genderChoiceEffect.getSelectionModel().select(genderEffect.getGender());
		}
	}

	@FXML
	private void initialize() {
		this.genderChoiceEffect.setItems(FXCollections.observableArrayList(Gender.values()));
	}

	@FXML
	private void handleAddGenderEffect() {
		Gender genderChoice = this.genderChoiceEffect.getValue();
		if (checkCondition()) {
			Effect tempEffect = new GenderEffect(genderChoice);
			this.helpAddEffect(addGenderEffect, tempEffect);
		}
	}

	@Override
	boolean checkCondition() {
		return this.genderChoiceEffect.getValue() != null;
	}
}
