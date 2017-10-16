package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import edu.augustana.csc285.game.datamodel.Slide;
import edu.augustana.csc285.game.datamodel.Story;


public class MainPanev2Controller {

	@FXML
	private TextArea descriptionField;
	@FXML
	private TextField slideID;
	@FXML
	private Button saveButton;
	@FXML
	private Button editOptionsButton;
	@FXML
	private Button backButton;
	@FXML
	private Button addItem;
	@FXML
	private Button imageButton;
	@FXML
	private Button musicButton;
	@FXML
	private Button previewButton;
	@FXML
	private Story story;
	private Slide currentSlide;

	@FXML
	private void initialize() {
		//currentSlide = new Slide();

	}

	@FXML
	private void handleSlideId() {
		//String Id = slideIdField.getText()
		//currentSlide.setId(text);
	}

	@FXML
	private void handleEditOptionsButton(ActionEvent event) throws IOException{
		Parent advanced_options_parent = FXMLLoader.load((getClass().getResource("AdvancedOption.fxml")));
		Scene advanced_options_scene = new Scene(advanced_options_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    app_stage.setScene(advanced_options_scene );
	    app_stage.show();
	}
	
	@FXML
	private void handleAddItem(ActionEvent event) throws IOException{
		Parent item_library_parent = FXMLLoader.load((getClass().getResource("ItemLibrary.fxml")));
		Scene item_library_scene = new Scene(item_library_parent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    app_stage.setScene(item_library_scene );
	    app_stage.show();
	}
	
	@FXML
	private void handleImageButton() {
		File imageFile = getFileFromUser();
		currentSlide.setImage(imageFile.getPath());
	}

	@FXML
	private void handleSlideDescription() {
		String Description = descriptionField.getText();
		currentSlide.setDesc(Description);
		
	}
    @FXML
	private void handleMusicButton() {
		File musicFile = getFileFromUser();
		currentSlide.setMusic(musicFile.getPath());
	}
    
    @FXML
 	private void handleBackButton() {
 		
 	}
    
    @FXML
 	private void handleSaveButton() {
 		
 	}

    @FXML
	private void handlePreviewButton() {
		new Alert(AlertType.INFORMATION, currentSlide.toString()).showAndWait();
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
