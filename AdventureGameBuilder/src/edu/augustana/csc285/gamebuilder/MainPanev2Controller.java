package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;

import edu.augustana.csc285.game.datamodel.Slide;
import edu.augustana.csc285.game.datamodel.Story;

public class MainPanev2Controller {

	@FXML private TextField descriptionField;
	@FXML private ChoiceBox slideChoiceBox;
	@FXML private Button imageButton;
	@FXML private Button musicButton;
	@FXML private Button slideStatusButton;
	@FXML private Button saveChangesButton;
	@FXML private Button descriptionButton;
	//private Story story;
	private Slide currentSlide;
	
	// JavaFX initialize method, called after this Pane is created.
	@FXML
	private void initialize() {
		currentSlide = new Slide();
		
	}
	
	@FXML
	public void handleSlideId(String text) {
		currentSlide.setId(text);
	}
	
	@FXML
	public void handleSlideDescription() {
		
	}
	
	@FXML
	public void handleImage() {
		
	}

	@FXML
	public void handleMusic() {
		
	}
	
	
	
	private File getFileFromUser() {
		FileChooser fileBrowser = new FileChooser();
		return fileBrowser.showOpenDialog(null);
	}
	

	
	
	/**
	 * Returns an array containing the story files
	 * Uses Java I/O for compatability with gamebuilder
	 */
	private static File[] getStoryFiles() {
		return new File("core/storyData").listFiles();
	}

}
