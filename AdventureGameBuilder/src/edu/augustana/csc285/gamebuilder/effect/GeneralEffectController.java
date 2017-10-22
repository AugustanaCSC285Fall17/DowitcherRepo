package edu.augustana.csc285.gamebuilder.effect;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.effect.*;
import edu.augustana.csc285.gamebuilder.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public abstract class GeneralEffectController {
	public OptionEditorController controller;
	public Effect effect;
	public ItemLibrary itemLibrary;
	public int index;

	Effect getEffect() {
		return effect;
	}

	void initDataTemplate(OptionEditorController controller, Effect effect) {
		this.controller = controller;
		this.effect = effect;
		this.itemLibrary = controller.getController().getItemLibrary();
		updateFields();
	}

	public abstract void updateFields();

	// For editing exist item condition
	public void initData(OptionEditorController controller, Effect effect, int index) {
		this.initDataTemplate(controller, effect);
		this.index = index;
	}

	// For creating new condition
	public void initData(OptionEditorController controller) {
		this.initDataTemplate(controller, null);
	}

	// To help add the new condition to the option
	public void helpAddEffect(Button button, Effect effect) {
		if (this.effect == null) {
			controller.getOption().addEffect(effect);
		} else {
			System.out.println("Here");
			controller.getOption().setEffect(index, effect);
		}
		System.out.println(controller.getOption().getEffects());
		controller.updateEffectListView();
		closeStage(button);
	}

	abstract boolean checkCondition();

	public void closeStage(Button button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}
}
