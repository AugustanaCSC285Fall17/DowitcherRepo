package edu.augustana.csc285.gamebuilder.effect;

import edu.augustana.csc285.game.datamodel.Property;
import edu.augustana.csc285.game.datamodel.PropertyType;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.gamebuilder.Helper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlayerStatEffectController extends GeneralEffectController {
	@FXML
	private ChoiceBox<PropertyType> statChoiceEffect;
	@FXML
	private ChoiceBox<EffectOperation> statOperationEffect;
	@FXML
	private TextField statQuantityEffect;
	@FXML
	private Button addStatEffect;

	public void updateFields() {
		if (effect != null) {
			PropertyEffect playerStatEffect = (PropertyEffect) effect;
			statChoiceEffect.getSelectionModel().select(playerStatEffect.getProperty().getType());
			statOperationEffect.getSelectionModel().select(playerStatEffect.getOperation());
			statQuantityEffect.setText(playerStatEffect.getProperty().getQuantity() + "");
		}
	}

	@FXML
	private void initialize() {
		this.statChoiceEffect.setItems(FXCollections.observableArrayList(PropertyType.values()));
		this.statOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.values()));
	}

	@FXML
	private void handleAddStatEffect() {
		PropertyType stat = this.statChoiceEffect.getValue();
		EffectOperation operation = this.statOperationEffect.getValue();
		String quantityString = this.statQuantityEffect.getText();
		if (checkCondition()) {
			Property property = new Property(stat, Integer.parseInt(quantityString));
			Effect tempEffect = new PropertyEffect(property, operation);
			this.helpAddEffect(addStatEffect, tempEffect);
		}

	}

	@Override
	boolean checkCondition() {

		return (this.statChoiceEffect.getValue() != null && this.statOperationEffect.getValue() != null
				&& Helper.checkLegalInt(this.statQuantityEffect.getText()));
	}
}
