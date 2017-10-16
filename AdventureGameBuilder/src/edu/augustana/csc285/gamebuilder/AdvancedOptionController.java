package edu.augustana.csc285.gamebuilder;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdvancedOptionController {
	private Option option;
	private ItemLibrary itemLibrary;
	@FXML
	private TextField nameEffectTextField;
	@FXML
	private Button createNameEffect;
	@FXML
	private ChoiceBox<String> genderChoiceEffect;
	@FXML
	private Button createGenderEffect;
	@FXML
	private Button createItemEffect;
	@FXML
	private ChoiceBox<String> itemChoiceEffect;
	@FXML
	private ChoiceBox<String> itemOperationEffect;
	@FXML
	private TextField itemQuantityEffect;
	@FXML
	private Button createStatEffect;
	@FXML
	private ChoiceBox<String> statChoiceEffect;
	@FXML
	private ChoiceBox<String> statOperationEffect;
	@FXML
	private TextField statQuantityEffect;

	public void initialize() {
		this.genderChoiceEffect.setItems(FXCollections.observableArrayList(Gender.getGenders()));
		this.itemChoiceEffect.setItems(FXCollections.observableArrayList(itemLibrary.getItemNameList()));
		this.itemOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));
		this.statChoiceEffect.setItems(FXCollections.observableArrayList(PropertyType.getPropertyTypeList()));
		this.statOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));
	}

	public void initData(Option option, ItemLibrary itemLibrary) {
		this.option = option;
		this.itemLibrary = itemLibrary;
	}

	private void handleCreateNameEffect() {
		option.addEffect(new NameEffect(nameEffectTextField.getText()));
	}

	private void handleCreateGenderEffect() {
		String genderChoice = this.genderChoiceEffect.getValue();
		if (this.checkLegalString(genderChoice)) {
			Gender gender = Gender.valueOf(genderChoice);
			option.addEffect(new GenderEffect(gender));
		}
	}

	private void handleCreateItemEffect() {
		String itemChoiceString = this.itemChoiceEffect.getValue();
		String itemOperationString = this.itemOperationEffect.getValue();
		String itemQuantityString = this.itemQuantityEffect.getText();
		if (this.checkLegalString(itemChoiceString) && this.checkLegalString(itemOperationString)
				&& this.checkLegalInt(itemQuantityString)) {
			Item item = itemLibrary.getItem(itemChoiceString, Integer.parseInt(itemQuantityString));
			EffectOperation operation = EffectOperation.valueOf(itemOperationString);
			option.addEffect(new ItemEffect(item, operation));
		}
	}

	private void handleCreateStatEffect() {
		String statChoiceString = this.statChoiceEffect.getValue();
		String statOperationString = this.statOperationEffect.getValue();
		String statQuantityString = this.statQuantityEffect.getText();
		if (this.checkLegalString(statChoiceString) && this.checkLegalString(statOperationString)
				&& this.checkLegalInt(statQuantityString)) {
			Property property = new Property(PropertyType.valueOf(statChoiceString),
					Integer.parseInt(statQuantityString));
			EffectOperation operation = EffectOperation.valueOf(statOperationString);
			option.addEffect(new PropertyEffect(property, operation));
		}
	}

	private boolean checkLegalString(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkLegalInt(String str) {
		if (str.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}
}
