package edu.augustana.csc285.gamebuilder;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.*;
import edu.augustana.csc285.game.datamodel.effect.*;

public class OptionPaneController {

	private int currentOptionIndex;
	private Option currentOption;
	@FXML
	private ComboBox optionBox;
	@FXML
	private Button optionAddButton;
	@FXML
	private Button optionRemoveButton;
	@FXML
	private TextField optionTextField;

	private ArrayList<Condition> feasibleConditions;
	private ArrayList<Condition> visibleConditions;
	@FXML
	private ComboBox conditionBox;
	@FXML
	private Button conditionAddButton;
	@FXML
	private Button conditionRemoveButton;
	@FXML
	private Button conditionTypeBox;

	private ArrayList<Effect> effects;
	@FXML
	private ComboBox effectBox;
	@FXML
	private Button effectAddButton;
	@FXML
	private Button effectRemoveButton;
	@FXML
	private ChoiceBox effectTypeBox;

	private Slide slide;

	@FXML
	private void initialize(Slide slide) {
		this.slide = slide;
		this.currentOptionIndex = 0;
		this.currentOption = slide.getOption(currentOptionIndex);
		this.feasibleConditions = new ArrayList<Condition>(currentOption.getFeasibleConditions());
		this.visibleConditions = new ArrayList<Condition>(currentOption.getVisibleConditions());
		this.effects = new ArrayList<Effect>(currentOption.getEffects());
	}

	@FXML
	private void handleOptionAddButton() {
		slide.addOption(new Option());
	}

	@FXML
	private void handleOptionRemoveButton() {
		slide.removeOption(currentOptionIndex);
	}

	@FXML
	private void handleEffectAddButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Select Effect Type.");

		ButtonType genderButton = new ButtonType("Gender");
		ButtonType itemButton = new ButtonType("Item");
		ButtonType nameButton = new ButtonType("Name");
		ButtonType propertyButton = new ButtonType("Property");

		alert.getButtonTypes().setAll(genderButton, itemButton, nameButton, propertyButton);
		Optional<ButtonType> result = alert.showAndWait();
		ButtonType choice = result.get();

		if (choice == genderButton) {
			effects.add(new GenderEffect());
		} else if (choice == itemButton) {
			effects.add(new ItemEffect());
		} else if (choice == nameButton) {
			effects.add(new NameEffect());
		} else if (choice == propertyButton) {
			effects.add(new PropertyEffect());
		}
	}

	@FXML
	private void handleEffectRemoveButton() {

	}

}
