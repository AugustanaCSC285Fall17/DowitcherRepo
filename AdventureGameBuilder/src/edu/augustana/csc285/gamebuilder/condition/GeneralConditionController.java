package edu.augustana.csc285.gamebuilder.condition;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.*;
import edu.augustana.csc285.gamebuilder.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class GeneralConditionController {
	public OptionEditorController controller;
	public Condition condition;
	public ItemLibrary itemLibrary;
	public String visibleFeasible;
	public int index;

	public Condition getCondition() {
		return condition;
	}

	public ItemLibrary getItemLibrary() {
		return itemLibrary;
	}

	public abstract void updateFields();

	public void initDataTemplate(OptionEditorController controller, Condition condition, String visibleFeasible) {
		this.controller = controller;
		this.condition = condition;
		this.itemLibrary = controller.getController().getItemLibrary();
		this.visibleFeasible = visibleFeasible;
		updateFields();
	}

	// For editing exist item condition
	public void initData(OptionEditorController controller, Condition condition, String visibleFeasible, int index) {
		this.initDataTemplate(controller, condition, visibleFeasible);
		this.index = index;
	}

	// For creating new condition
	public void initData(OptionEditorController controller, String visibleFeasible) {
		this.initDataTemplate(controller, null, visibleFeasible);
	}

	// To help add the new condition to the option
	public void helpAddCondition(Button button, Condition condition) {
		if (visibleFeasible.equalsIgnoreCase("Visible")) {
			if (this.condition == null) {
				controller.getOption().addVisibleCondition(condition);
			} else {
				controller.getOption().setVisibleCondition(index, condition);
			}
			controller.updateVisibleConditionListView();
		} else {
			if (this.condition == null) {
				controller.getOption().addFeasibleCondition(condition);
			} else {
				controller.getOption().setFeasibleCondition(index, condition);
			}
			controller.updateFeasibleConditionListView();
		}
		closeStage(button);
	}

	abstract boolean checkCondition();

	void closeStage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}
}
