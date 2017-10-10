package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.event.MouseEvent;
import java.io.File;

public class MainPaneController {

	@FXML private TextField uselessTextField;
	@FXML private Button uselessButton;
	
	// JavaFX initialize method, called after this Pane is created.
	@FXML
	private void initialize() {
		// Slide slide = new Slide();
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
		new Alert(AlertType.INFORMATION,"Placeholder for screen to be created").showAndWait();
	}
	
    @FXML
    void saveChanges() {
    	//save the edited screen
    	new Alert(AlertType.INFORMATION,"All Changes Saved").showAndWait();
    }	
	
	
	/**
	 * Returns an array containing the story files
	 * Uses Java I/O for compatability with gamebuilder
	 */
	private static File[] getStoryFiles() {
		return new File("core/storyData").listFiles();
	}

}
