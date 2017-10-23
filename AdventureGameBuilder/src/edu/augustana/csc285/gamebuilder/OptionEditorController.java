package edu.augustana.csc285.gamebuilder;

import java.io.IOException;
import java.util.Arrays;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.gamebuilder.condition.GeneralConditionController;
import edu.augustana.csc285.gamebuilder.effect.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Dat Tran
 *
 */
public class OptionEditorController {
	private MainPanev2Controller controller;
	// To do note you don't need story and slide since it is in controller
	private Option option;
	private int index;
	@FXML
	private TextField descTextField;
	@FXML
	private TextField toSlideTextField;
	@FXML
	private TextField rejectMessageTextField;
	@FXML
	private Label slidedCreatedLabel;
	@FXML
	private Button createOption;
	@FXML
	private Button newEffect;
	@FXML
	private Button editEffect;
	@FXML
	private Button deleteEffect;
	@FXML
	private ChoiceBox<String> effectTypeChoice;
	@FXML
	private Button newCondition;
	@FXML
	private Button editCondition;
	@FXML
	private Button deleteCondition;
	@FXML
	private ChoiceBox<String> visibleFeasibleChoice;
	@FXML
	private ChoiceBox<String> conditionTypeChoice;
	@FXML
	private ListView<Effect> effectListView;
	@FXML
	private ListView<Condition> visibleConditionListView;
	@FXML
	private ListView<Condition> feasibleConditionListView;

	public MainPanev2Controller getController() {
		return controller;
	}

	public Option getOption() {
		return option;
	}

	@FXML
	private void initialize() {
		// To test
		// this.controller = new MainPanev2Controller();
		// ItemLibrary itemLibrary = new ItemLibrary();
		// Item sek = new Item("Sek", "Currency of Sweden", 1, null);
		// Item dollar = new Item("Dollar", "Currency of USA", 1, null);
		// Item medicine = new Item("Medicine", null, 1, null);
		// controller.setItemLibrary(itemLibrary);
		// this.option = new Option("Medicine", null, null, "11", null, null);
		// this.option.addFeasibleCondition(new ItemCondition(medicine,
		// ConditionOperation.SMALLER));
		// this.option.addFeasibleCondition(new GenderCondition(Gender.MALE));
		// this.option.addVisibleCondition(new ItemCondition(medicine,
		// ConditionOperation.SMALLER));
		// this.option.addEffect(new GenderEffect(Gender.MALE));
		// this.option.addEffect(new NameEffect("Dat"));
		// this.updateFields();

		// This is beginning of real stuff
		String[] effectTypes = { "Name Effect", "Gender Effect", "Item Effect", "Player Stat Effect" };
		String[] conditionTypes = { "Gender Condition", "Item Condition", "Player Stat Condition", "Random Condition" };
		String[] visibilityTypes = { "Visible", "Feasible" };
		this.effectTypeChoice.setItems(FXCollections.observableArrayList(Arrays.asList(effectTypes)));
		this.conditionTypeChoice.setItems(FXCollections.observableArrayList(Arrays.asList(conditionTypes)));
		this.visibleFeasibleChoice.setItems(FXCollections.observableArrayList(Arrays.asList(visibilityTypes)));
		this.toSlideTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateSlideCreated();
			}
		});
		this.visibleConditionListView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldItem, newItem) -> {
					if (newItem != null) {
						this.feasibleConditionListView.getSelectionModel().clearSelection();
					}
				});

		this.feasibleConditionListView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldItem, newItem) -> {
					if (newItem != null) {
						this.visibleConditionListView.getSelectionModel().clearSelection();
					}
				});
	}

	public void updateFields() {
		this.updateBasicInformation();
		this.updateEffectListView();
		this.updateFeasibleConditionListView();
		this.updateVisibleConditionListView();
	}

	// To edit existing option
	public void initData(MainPanev2Controller controller, Option option, int index) {
		this.controller = controller;
		this.option = option;
		this.index = index;
		this.updateFields();
	}

	// To create a new option
	public void initData(MainPanev2Controller controller) {
		initData(controller, new Option(), -1);
	}

	@FXML
	private void handleCreateOption() {
		if (this.checkConditionForBasicInformation()) {
			option.setDesc(this.descTextField.getText());
			option.setNextSlideIndex(this.toSlideTextField.getText());
			if (this.rejectMessageTextField.getText() != null) {
				option.setRejectMessage(this.rejectMessageTextField.getText());
			}
			if (index == -1) {
				this.controller.getCurrentSlide().addOption(option);
			} else {
				this.controller.getCurrentSlide().setOption(index, option);
			}
			this.closeStage(createOption);
		}
	}

	@FXML
	private void handleNewEffect() throws IOException {
		if (Helper.checkLegalString(this.effectTypeChoice.getSelectionModel().getSelectedItem())) {
			this.helperForEffect(null, this.effectTypeChoice.getSelectionModel().getSelectedItem(), 0);
		}
	}

	@FXML
	private void handleDeleteEffect() {
		if (!this.effectListView.getSelectionModel().isEmpty()) {
			option.removeEffect(this.effectListView.getSelectionModel().getSelectedIndex());
			this.updateEffectListView();
		}
	}

	@FXML
	private void handleEditEffect() throws IOException {
		if (!this.effectListView.getSelectionModel().isEmpty()) {
			int index = effectListView.getSelectionModel().getSelectedIndex();
			Effect currentEffect = this.option.getEffect(index);
			this.helperForEffect(currentEffect, currentEffect.getEffectType(), index);
		}
	}

	private void helperForEffect(Effect effect, String effectType, int index) throws IOException {
		Stage dialog = new Stage();
		String resource = "/edu/augustana/csc285/gamebuilder/effect/";
		resource += effectType.replaceAll(" ", "");
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource + ".fxml"));
		Parent root = loader.load();
		GeneralEffectController controller = loader.getController();
		if (effect != null) {
			controller.initData(this, effect, index);
			dialog.setTitle("Edit " + effectType);
		} else {
			controller.initData(this);
			dialog.setTitle("Create new " + effectType);
		}
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(editEffect.getScene().getWindow());
		dialog.showAndWait();
	}

	@FXML
	private void handleNewCondition() throws IOException {
		if (!this.visibleFeasibleChoice.getSelectionModel().isEmpty()
				&& !this.conditionTypeChoice.getSelectionModel().isEmpty()) {
			String visibleFeasible = this.visibleFeasibleChoice.getSelectionModel().getSelectedItem();
			String conditionType = this.conditionTypeChoice.getSelectionModel().getSelectedItem();
			this.helperForCondition(null, conditionType, visibleFeasible, 0);
		}
	}

	@FXML
	private void handleEditCondition() throws IOException {
		if (!this.visibleConditionListView.getSelectionModel().isEmpty()) {
			int index = this.visibleConditionListView.getSelectionModel().getSelectedIndex();
			String visibleFeasible = "Visible";
			Condition condition = this.option.getVisibleCondition(index);
			this.helperForCondition(condition, condition.getConditionType(), visibleFeasible, index);
		} else if (!this.feasibleConditionListView.getSelectionModel().isEmpty()) {
			int index = this.feasibleConditionListView.getSelectionModel().getSelectedIndex();
			String visibleFeasible = "Feasible";
			Condition condition = this.option.getFeasibleCondition(index);
			this.helperForCondition(condition, condition.getConditionType(), visibleFeasible, index);
		}
	}

	@FXML
	private void handleDeleteCondition() {
		if (!this.visibleConditionListView.getSelectionModel().isEmpty()) {
			option.removeVisibleCondition(this.visibleConditionListView.getSelectionModel().getSelectedIndex());
			this.updateVisibleConditionListView();
		} else if (!this.feasibleConditionListView.getSelectionModel().isEmpty()) {
			option.removeFeasibleCondition(this.feasibleConditionListView.getSelectionModel().getSelectedIndex());
			this.updateFeasibleConditionListView();
		}
	}

	private void helperForCondition(Condition condition, String conditionType, String visibleFeasible, int index)
			throws IOException {
		Stage dialog = new Stage();
		String resource = "/edu/augustana/csc285/gamebuilder/condition/";
		resource += conditionType.replaceAll(" ", "");
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource + ".fxml"));
		Parent root = loader.load();
		GeneralConditionController controller = loader.getController();
		if (condition != null) {
			controller.initData(this, condition, visibleFeasible, index);
			dialog.setTitle("Edit " + conditionType);
		} else {
			controller.initData(this, visibleFeasible);
			dialog.setTitle("Create new " + conditionType);
		}
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(this.editCondition.getScene().getWindow());
		dialog.showAndWait();
	}

	// Update the value in list view
	public void updateEffectListView() {
		if (this.option != null) {
			ObservableList<Effect> effects = FXCollections.observableArrayList(this.option.getEffects());
			this.effectListView.setItems(effects);
		}
	}

	public void updateVisibleConditionListView() {
		if (this.option != null) {
			ObservableList<Condition> visibleConditions = FXCollections
					.observableArrayList(this.option.getVisibleConditions());
			this.visibleConditionListView.setItems(visibleConditions);
		}
	}

	public void updateFeasibleConditionListView() {
		if (this.option != null) {
			ObservableList<Condition> feasibleConditions = FXCollections
					.observableArrayList(this.option.getFeasibleConditions());
			this.feasibleConditionListView.setItems(feasibleConditions);
		}
	}

	private void updateBasicInformation() {
		if (this.option != null) {
			this.descTextField.setText(option.getDesc());
			this.toSlideTextField.setText(option.getNextSlideIndex());
			this.rejectMessageTextField.setText(option.getRejectMessage());
		}
	}

	private void updateSlideCreated() {
		String toSlideString = this.toSlideTextField.getText();
		if (Helper.checkLegalInt(toSlideString)) {
			this.slidedCreatedLabel.setText(String.valueOf(this.controller.getStory().contains(toSlideString)));
		}
	}

	private boolean checkConditionForBasicInformation() {
		return Helper.checkLegalString(this.descTextField.getText())
				&& Helper.checkLegalInt(this.toSlideTextField.getText());
	}

	private void closeStage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}
}
