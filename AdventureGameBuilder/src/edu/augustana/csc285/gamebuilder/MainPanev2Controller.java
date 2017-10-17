package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import edu.augustana.csc285.game.datamodel.*;

public class MainPanev2Controller {
	private ItemLibrary itemLibrary;
	@FXML
	private Button selectImage;
	@FXML
	private Button selectMusic;
	@FXML
	private Button editOptions;
	@FXML
	private Button saveButton;
	@FXML
	private Button backButton;
	@FXML
	private TextArea slideDescription;
	@FXML
	private TextField slideId;
	@FXML
	private TableView optionView;
	@FXML
	private ImageView imageView;
	@FXML
	private MenuItem LoadLibrary;
	
	private Story story;
	private Slide currentSlide;
	
	public ItemLibrary getItemLibrary() {
		return itemLibrary;
	}

	public void setItemLibrary(ItemLibrary itemLibrary) {
		this.itemLibrary = itemLibrary;
	}

	@FXML
	private void initialize() {
		currentSlide = new Slide();
		

	}

	@FXML
	private void handleLoadLibrary() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemLibrary.fxml"));
		Parent root = loader.load();
		
		
		ItemLibraryController controller = loader.getController();	
		controller.initData(this);
	    
        Scene scene = new Scene(root);
    
        stage.setTitle("Item Library");
        stage.setScene(scene);
        stage.show();
	}
	
	
	/**
	 * @return the loadLibrary
	 */
	public MenuItem getLoadLibrary() {
		return LoadLibrary;
	}

	/**
	 * @param loadLibrary the loadLibrary to set
	 */
	public void setLoadLibrary(MenuItem loadLibrary) {
		LoadLibrary = loadLibrary;
	}

	@FXML
	private void handleSlideId() {
		// String Id = slideIdField.getText()
		// currentSlide.setId(text);
	}

	@FXML
	private void handleEditOptionsButton(ActionEvent event) throws IOException {
		
	}

	@FXML
	private void handleAddItem(ActionEvent event) throws IOException {
		
	}

	@FXML
	private void handleImageButton() {
		File imageFile = getFileFromUser();
		currentSlide.setImage(imageFile.getPath());
	}

	@FXML
	public void handleSlideDescription() {

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
	public void handleImage() {

	}

	@FXML
	public void handleMusic() {

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
		// Story.slides.get(currentSlide.getId());
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
