package edu.augustana.csc285.gamebuilder.effect;

import edu.augustana.csc285.game.datamodel.ItemLibrary;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.gamebuilder.Helper;
import edu.augustana.csc285.gamebuilder.OptionEditorController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NameEffectController extends GeneralEffectController {
	@FXML
	private TextField nameEffectTextField;
	@FXML
	private Button addNameEffect;

	public void updateFields() {
		if (effect != null) {
			System.out.println("Here" + this.effect);
			NameEffect nameEffect = (NameEffect) effect;
			nameEffectTextField.setText(nameEffect.getName() + "");
		}
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddNameEffect() {
		String name = this.nameEffectTextField.getText();
		if (checkCondition()) {
			Effect tempEffect = new NameEffect(name);
			this.helpAddEffect(addNameEffect, tempEffect);
		}

	}

	@Override
	boolean checkCondition() {
		return Helper.checkLegalString(this.nameEffectTextField.getText());
	}
}
