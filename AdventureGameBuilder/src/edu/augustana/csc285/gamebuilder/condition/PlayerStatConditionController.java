package edu.augustana.csc285.gamebuilder.condition;

import edu.augustana.csc285.game.datamodel.Property;
import edu.augustana.csc285.game.datamodel.PropertyType;
import edu.augustana.csc285.game.datamodel.condition.Condition;
import edu.augustana.csc285.game.datamodel.condition.ConditionOperation;
import edu.augustana.csc285.game.datamodel.condition.PropertyCondition;
import edu.augustana.csc285.gamebuilder.Helper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlayerStatConditionController extends GeneralConditionController {
	@FXML
	private ChoiceBox<PropertyType> statChoiceCondition;
	@FXML
	private ChoiceBox<ConditionOperation> statOperationCondition;
	@FXML
	private TextField statQuantityCondition;
	@FXML
	private Button addStatCondition;

	public void updateFields() {
		if (this.getCondition() != null) {
			PropertyCondition playerStatCondition = (PropertyCondition) this.getCondition();
			statChoiceCondition.getSelectionModel().select(playerStatCondition.getProperty().getType());
			statOperationCondition.getSelectionModel().select(playerStatCondition.getOperation());
			statQuantityCondition.setText(playerStatCondition.getProperty().getQuantity() + "");
		}

	}

	@FXML
	private void initialize() {
		this.statChoiceCondition.setItems(FXCollections.observableArrayList(PropertyType.values()));
		this.statOperationCondition.setItems(FXCollections.observableArrayList(ConditionOperation.values()));
	}

	@FXML
	private void handleAddStatCondition() {
		PropertyType stat = this.statChoiceCondition.getValue();
		ConditionOperation operation = this.statOperationCondition.getValue();
		String quantityString = this.statQuantityCondition.getText();
		if (checkCondition()) {
			Property property = new Property(stat, Integer.parseInt(quantityString));
			Condition tempCondition = new PropertyCondition(property, operation);
			this.helpAddCondition(addStatCondition, tempCondition);
		}

	}

	@Override
	boolean checkCondition() {

		return (this.statChoiceCondition.getValue() != null && this.statOperationCondition.getValue() != null
				&& Helper.checkLegalInt(this.statQuantityCondition.getText()));
	}
}
