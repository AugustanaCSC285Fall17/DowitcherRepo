package edu.augustana.csc285.gamebuilder.effect;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.gamebuilder.Helper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ItemEffectController extends GeneralEffectController {
	@FXML
	private ChoiceBox<Item> itemChoiceEffect;
	@FXML
	private ChoiceBox<EffectOperation> itemOperationEffect;
	@FXML
	private TextField itemQuantityEffect;
	@FXML
	private Button addItemEffect;

	public void updateFields() {
		if (itemLibrary != null) {
			this.itemChoiceEffect.setItems(FXCollections.observableArrayList(itemLibrary.getItems()));
			this.itemOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.values()));
		}
		if (effect != null) {
			ItemEffect itemEffect = (ItemEffect) effect;
			itemChoiceEffect.getSelectionModel().select(itemEffect.getItem());
			itemOperationEffect.getSelectionModel().select(itemEffect.getOperation());
			itemQuantityEffect.setText(itemEffect.getItem().getQuantity() + "");
		}

	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddItemEffect() {
		Item item = new Item(this.itemChoiceEffect.getValue());
		EffectOperation operation = this.itemOperationEffect.getValue();
		String quantityString = this.itemQuantityEffect.getText();
		if (checkCondition()) {
			item.setQuantity(Integer.parseInt(quantityString));
			Effect tempEffect = new ItemEffect(item, operation);
			this.helpAddEffect(addItemEffect, tempEffect);
		}
	}

	@Override
	boolean checkCondition() {
		return (this.itemChoiceEffect.getValue() != null && this.itemOperationEffect.getValue() != null
				&& Helper.checkLegalInt(this.itemQuantityEffect.getText()));
	}
}
