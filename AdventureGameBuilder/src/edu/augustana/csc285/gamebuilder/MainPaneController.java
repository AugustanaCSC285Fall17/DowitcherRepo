package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainPaneController {

	@FXML private TextField uselessTextField;
	@FXML private Button uselessButton;
	
	// JavaFX initialize method, called after this Pane is created.
	@FXML
	private void initialize() {
	}
	
	@FXML
	private void handleUselessButton() {
		Alert alert = new Alert(AlertType.INFORMATION,"This button is useless!");
		alert.showAndWait();
	}

	@FXML
	private void handleMenuFileClose() {
		// TODO: eventually offer option to save before closing? 
		Platform.exit();
	}

	@FXML
	private void handleMenuHelpAbout() {
		new Alert(AlertType.INFORMATION,"Placeholder for about screen").showAndWait();
	}

}
