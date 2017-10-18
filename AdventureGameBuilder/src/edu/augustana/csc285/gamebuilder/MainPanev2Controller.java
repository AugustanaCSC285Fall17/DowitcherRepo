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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
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
	private Button addOptions;
	@FXML
	private Button saveButton;
	@FXML
	private Button backButton;
	@FXML
	private TextArea slideDescription;
	@FXML
	private TextField slideId;
	@FXML
	private TableView<?> optionView;
	@FXML
	private TableColumn nextSlideView;
	@FXML
	private TableColumn optionDescView;
	@FXML
	private ImageView imageView;
	@FXML
	private MenuItem LoadLibrary;
	@FXML
	private MenuItem closeButton;
	@FXML
	private MenuItem deleteButton;
	@FXML
	private MenuItem aboutButton;

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
	private void handleSelectImage() throws IOException {
		// Slide Image button
		
		File imageFile = getFileFromUser();
		Path path = Paths.get("GameData/SlideImages/" + imageFile.getName());
		
		if (Files.notExists(path)) {
			Files.createFile(path);
			Files.copy(Paths.get(imageFile.getPath()), path, StandardCopyOption.REPLACE_EXISTING);
		}
		currentSlide.setImage(imageFile.getName());
		InputStream input = new FileInputStream(imageFile.getPath());
		imageView.setImage(new Image(input));
	}

	@FXML
	private void handleSelectMusic() throws IOException {
		// Select Music button
		File musicFile = getFileFromUser();
		Path path = Paths.get("GameData/MusicFiles/" + musicFile.getName());
		
		if (Files.notExists(path)) {
			Files.createFile(path);
			Files.copy(Paths.get(musicFile.getPath()), path, StandardCopyOption.REPLACE_EXISTING);
		}
		currentSlide.setMusic(musicFile.getName());
	}

	@FXML
	private void handleEditOptions() throws IOException {
		// Edit Options button
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionPane.fxml"));
		Parent root = loader.load();

		OptionPaneController controller = loader.getController();
		//controller.initData(this);

		Scene scene = new Scene(root);

		stage.setTitle("Option editor");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void handleAddOptions() throws IOException{
		// Edit Options button
		
				currentSlide.addOption(new Option());
				
				
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionPane.fxml"));
				Parent root = loader.load();

				OptionPaneController controller = loader.getController();
				//controller.initData(this);
				

				Scene scene = new Scene(root);

				stage.setTitle("Option editor");
				stage.setScene(scene);
				stage.show();
		
				
				//nextSlideView.getColumns().add();
				//optionDescView.
				
	}
	@FXML
	private void handleSaveButton() {
		// Save Button

	}

	@FXML
	private void handleBackButton() {
		// Back Button, discard changes OR save temp slide

	}

	@FXML
	private void handleSlideDescription() {
		// slide description text area

	}

	@FXML
	private void handleSlideId() {
		// slide ID text field

	}

	@FXML
	private void handleOptionView() {

	}

	@FXML
	private void handleImageView() {

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

	@FXML
	private void handleCloseButton() {

	}

	@FXML
	private void handleDeleteButton() {

	}

	@FXML
	private void handleAboutButton() {

	}
	// End of handle Methods

	private File getFileFromUser() {
		FileChooser fileBrowser = new FileChooser();
		return fileBrowser.showOpenDialog(null);
	}

	/**
	 * Returns an array containing the story files Uses Java I/O for compatability
	 * with gamebuilder
	 */
	private static File[] getStoryFiles() {
		return new File("core/storyData").listFiles();
	}

	/**
	 * @return the loadLibrary
	 */
	public MenuItem getLoadLibrary() {
		return LoadLibrary;
	}

	/**
	 * @param loadLibrary
	 *            the loadLibrary to set
	 */
	public void setLoadLibrary(MenuItem loadLibrary) {
		LoadLibrary = loadLibrary;
	}

}
