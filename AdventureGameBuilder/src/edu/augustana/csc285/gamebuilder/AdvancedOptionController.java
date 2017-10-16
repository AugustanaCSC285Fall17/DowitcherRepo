package edu.augustana.csc285.gamebuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * 
 * @author Dat Tran
 *
 */
public class AdvancedOptionController {
	private Option option = new Option();
	private ItemLibrary itemLibrary;
	@FXML
	private TextField nameEffectTextField;
	@FXML
	private Button addNameEffect;
	@FXML
	private ChoiceBox<String> genderChoiceEffect;
	@FXML
	private Button addGenderEffect;
	@FXML
	private Button addItemEffect;
	@FXML
	private ChoiceBox<String> itemChoiceEffect;
	@FXML
	private ChoiceBox<String> itemOperationEffect;
	@FXML
	private TextField itemQuantityEffect;
	@FXML
	private Button addStatEffect;
	@FXML
	private ChoiceBox<String> statChoiceEffect;
	@FXML
	private ChoiceBox<String> statOperationEffect;
	@FXML
	private TextField statQuantityEffect;
	@FXML
	private ChoiceBox<String> genderChoiceCondition;
	@FXML
	private Button addVisibleGenderCondition;
	@FXML
	private Button addFeasibleGenderCondition;
	@FXML
	private ChoiceBox<String> itemChoiceCondition;
	@FXML
	private ChoiceBox<String> itemOperationCondition;
	@FXML
	private TextField itemQuantityCondition;
	@FXML
	private Button addVisibleItemCondition;
	@FXML
	private Button addFeasibleItemCondition;
	@FXML
	private ChoiceBox<String> statChoiceCondition;
	@FXML
	private ChoiceBox<String> statOperationCondition;
	@FXML
	private TextField statQuantityCondition;
	@FXML
	private Button addVisibleStatCondition;
	@FXML
	private Button addFeasibleStatCondition;
	@FXML
	private TextField percentageCondition;
	@FXML
	private Button addVisibleRandomCondition;
	@FXML
	private Button addFeasibleRandomCondition;

	@FXML
	public void initialize() {

		// For testing purpose only
		String libraryString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("testLibrary.json")))) {

			String line;
			while ((line = reader.readLine()) != null)
				libraryString += line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.itemLibrary = ItemLibrary.fromJSON(libraryString);

		this.genderChoiceEffect.setItems(FXCollections.observableArrayList(Gender.getGenders()));
		this.itemChoiceEffect.setItems(FXCollections.observableArrayList(itemLibrary.getItemNameList()));
		this.itemOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));
		this.statChoiceEffect.setItems(FXCollections.observableArrayList(PropertyType.getPropertyTypeList()));
		this.statOperationEffect.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));

		this.genderChoiceCondition.setItems(FXCollections.observableArrayList(Gender.getGenders()));
		this.itemChoiceCondition.setItems(FXCollections.observableArrayList(itemLibrary.getItemNameList()));
		this.itemOperationCondition
				.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));
		this.statChoiceCondition.setItems(FXCollections.observableArrayList(PropertyType.getPropertyTypeList()));
		this.statOperationCondition
				.setItems(FXCollections.observableArrayList(EffectOperation.getEffectOperationList()));

	}

	public void initData(Option option, ItemLibrary itemLibrary) {
		this.option = option;
		this.itemLibrary = itemLibrary;
	}

	@FXML
	private void handleAddNameEffect() {
		option.addEffect(new NameEffect(nameEffectTextField.getText()));
	}

	@FXML
	private void handleAddGenderEffect() {
		String genderChoice = this.genderChoiceEffect.getValue();
		if (this.checkLegalString(genderChoice)) {
			Gender gender = Gender.valueOf(genderChoice);
			option.addEffect(new GenderEffect(gender));
		}
	}

	@FXML
	private void handleAddItemEffect() {
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

	@FXML
	private void handleAddStatEffect() {
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

	@FXML
	private void handleAddVisibleGenderCondition() {
		GenderCondition condition = genderConditionHelper();
		if (condition != null) {
			option.addVisibleCondition(condition);
		}
	}

	@FXML
	private void handleAddFeasibleGenderCondition() {
		GenderCondition condition = genderConditionHelper();
		if (condition != null) {
			option.addFeasibleCondition(condition);
		}
	}

	private GenderCondition genderConditionHelper() {
		String genderChoice = this.genderChoiceCondition.getValue();
		if (this.checkLegalString(genderChoice)) {
			Gender gender = Gender.valueOf(genderChoice);
			return new GenderCondition(gender);
		}
		return null;
	}

	@FXML
	private void handleAddVisibleItemCondition() {
		ItemCondition condition = itemConditionHelper();
		if (condition != null) {
			option.addVisibleCondition(condition);
		}
	}

	@FXML
	private void handleAddFeasibleItemCondition() {
		ItemCondition condition = itemConditionHelper();
		if (condition != null) {
			option.addFeasibleCondition(condition);
		}
	}

	private ItemCondition itemConditionHelper() {
		String itemString = this.itemChoiceCondition.getValue();
		String operationString = this.itemOperationCondition.getValue();
		String quantityString = this.itemQuantityCondition.getText();
		if (this.checkLegalString(itemString) && this.checkLegalString(operationString)
				&& this.checkLegalInt(quantityString)) {
			Item item = itemLibrary.getItem(itemString, Integer.parseInt(quantityString));
			ConditionOperation operation = ConditionOperation.valueOf(operationString);
			return new ItemCondition(item, operation);
		}
		return null;
	}

	@FXML
	private void handleAddVisibleStatCondition() {
		PropertyCondition condition = statConditionHelper();
		if (condition != null) {
			option.addVisibleCondition(condition);
		}
	}

	@FXML
	private void handleAddFeasibleStatCondition() {
		PropertyCondition condition = statConditionHelper();
		if (condition != null) {
			option.addFeasibleCondition(condition);
		}
	}

	private PropertyCondition statConditionHelper() {
		String statString = this.statChoiceCondition.getValue();
		String operationString = this.statOperationCondition.getValue();
		String quantityString = this.statQuantityCondition.getText();
		if (this.checkLegalString(statString) && this.checkLegalString(operationString)
				&& this.checkLegalInt(quantityString)) {
			Property property = new Property(PropertyType.valueOf(statString), Integer.parseInt(quantityString));
			ConditionOperation operation = ConditionOperation.valueOf(operationString);
			return new PropertyCondition(property, operation);
		}
		return null;
	}

	@FXML
	private void handleAddVisibleRandomCondition() {
		RandomCondition condition = randomConditionHelper();
		if (condition != null) {
			option.addVisibleCondition(condition);
		}
	}

	@FXML
	private void handleAddFeasibleRandomCondition() {
		RandomCondition condition = randomConditionHelper();
		if (condition != null) {
			option.addFeasibleCondition(condition);
		}
	}

	private RandomCondition randomConditionHelper() {
		String percentage = this.percentageCondition.getText();
		if (this.checkLegalDouble(percentage)) {
			return new RandomCondition(Double.parseDouble(percentage));
		}
		return null;
	}

	private boolean checkLegalString(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkLegalInt(String str) {
		if (str != null && !str.equals("") && str.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkLegalDouble(String str) {
		if (str != null && !str.equals("") && str.matches("([0-9]*)\\.([0-9]*)")) {
			return true;
		} else {
			return false;
		}
	}
}
