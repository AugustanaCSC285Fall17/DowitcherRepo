package edu.augustana.csc285.gamebuilder.condition;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.condition.Condition;
import edu.augustana.csc285.game.datamodel.condition.ConditionOperation;
import edu.augustana.csc285.game.datamodel.condition.ItemCondition;
import edu.augustana.csc285.gamebuilder.Helper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ItemConditionController extends GeneralConditionController {
	@FXML
	private ChoiceBox<Item> itemChoiceCondition;
	@FXML
	private ChoiceBox<ConditionOperation> itemOperationCondition;
	@FXML
	private TextField itemQuantityCondition;
	@FXML
	private Button addItemCondition;

	public void updateFields() {
		if (itemLibrary != null) {
			this.itemChoiceCondition.setItems(FXCollections.observableArrayList(getItemLibrary().getItems()));
			this.itemOperationCondition.setItems(FXCollections.observableArrayList(ConditionOperation.values()));
		}
		if (this.getCondition() != null) {
			ItemCondition itemCondition = (ItemCondition) this.getCondition();
			itemChoiceCondition.getSelectionModel().select(itemCondition.getItem());
			itemOperationCondition.getSelectionModel().select(itemCondition.getOperation());
			itemQuantityCondition.setText(itemCondition.getItem().getQuantity() + "");
		}
	}

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleAddItemCondition() {
		Item item = new Item(this.itemChoiceCondition.getValue());
		ConditionOperation operation = this.itemOperationCondition.getValue();
		String quantityString = this.itemQuantityCondition.getText();
		if (checkCondition()) {
			item.setQuantity(Integer.parseInt(quantityString));
			Condition tempCondition = new ItemCondition(item, operation);
			this.helpAddCondition(addItemCondition, tempCondition);
		}
	}

	@Override
	boolean checkCondition() {
		return (this.itemChoiceCondition.getValue() != null && this.itemOperationCondition.getValue() != null
				&& Helper.checkLegalInt(this.itemQuantityCondition.getText()));
	}
}
