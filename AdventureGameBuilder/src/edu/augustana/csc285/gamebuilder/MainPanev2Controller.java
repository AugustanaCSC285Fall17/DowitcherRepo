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

	@FXML
	private TextField descriptionField;
	@FXML
	private ChoiceBox slideChoiceBox;
	@FXML
	private Button imageButton;
	@FXML
	private Button musicButton;
	@FXML
	private Button slideStatusButton;
	@FXML
	private Button saveChangesButton;
	@FXML
	private Button descriptionButton;
	private Story story;
	private Slide currentSlide;
	// private Story story;
	// private Slide currentSlide;

	// JavaFX initialize method, called after this Pane is created.
	@FXML
	private void initialize() {
		currentSlide = new Slide();

	}

	@FXML
	public void handleSlideId(String text) {
		currentSlide.setId(text);
	}

	private void handleImageButton() {
		File imageFile = getFileFromUser();
		currentSlide.setImage(imageFile.getPath());
	}

	@FXML
	public void handleSlideDescription() {
		
	}

	private void handleMusicButton() {
		File musicFile = getFileFromUser();
		currentSlide.setMusic(musicFile.getPath());
	}

	@FXML
	public void handleImage() {

	}

	@FXML
	public void handleMusic() {

	}

	private void handleSlideStatusButton() {
		new Alert(AlertType.INFORMATION, currentSlide.toString()).showAndWait();
	}

	@FXML
	private void handleDescriptionButton() {
		currentSlide.setDesc(descriptionField.getText());
	}

	private File getFileFromUser() {
		FileChooser fileBrowser = new FileChooser();
		return fileBrowser.showOpenDialog(null);
	}

	@FXML
	private void handleSaveChangesButton() {
		// save the edited screen
		//Story.slides.get(currentSlide.getId());
		new Alert(AlertType.INFORMATION, "All Changes Saved").showAndWait();
	}

	/**
	 * Returns an array containing the story files Uses Java I/O for compatability
	 * with gamebuilder
	 */
	private static File[] getStoryFiles() {
		return new File("core/storyData").listFiles();
	}

}
