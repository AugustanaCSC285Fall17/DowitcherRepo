package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.ConditionOperation;
import edu.augustana.csc285.game.datamodel.condition.ItemCondition;
import edu.augustana.csc285.game.datamodel.effect.EffectOperation;
import edu.augustana.csc285.game.datamodel.effect.ItemEffect;

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
	private Button deleteOptions;
	@FXML
	private Button saveButton;
	@FXML
	private Button deleteButton;
	@FXML
	private TextArea slideDescription;
	@FXML
	private TextField slideId;
	@FXML
	private TableView<Option> optionView;
	@FXML
	private TableColumn<Option, String> nextSlideView;
	@FXML
	private TableColumn<Option, String> optionDescView;
	@FXML
	private ImageView imageView;
	@FXML
	private MenuItem LoadStory;
	@FXML
	private MenuItem loadLibrary;
	@FXML
	private MenuItem closeButton;
	@FXML
	private MenuItem deleteMenuItem;
	@FXML
	private MenuItem aboutButton;
	@FXML
	private ChoiceBox<String> slideSelection;
	@FXML
	private Button newSlideButton;
	@FXML
	private MenuItem saveStory;
	@FXML
	private TextField slideTitle;
	
	
	private Story currentStory;
	private Slide currentSlide;
	private Path storyJSONPath;
	private File JSONFile;

	@FXML
	private void initialize() throws FileNotFoundException {
		currentStory = new Story("1");
		slideSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if (newValue != null) {
						if (!newValue.equals(oldValue)) {
							System.out.println("Listener: " + newValue);
							currentSlide = currentStory.getSlide(newValue);
							updateFields();
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// slideSelection.setValue(newValue);
			}
		});
		updateFields();
		this.slideDescription.setWrapText(true);

		//TODO set the sorting on the optionView
	}

	public void updateFields() throws FileNotFoundException {
		if (currentSlide != null) {
			nextSlideView.setCellValueFactory(new PropertyValueFactory<Option, String>("nextSlideIndex"));
			optionDescView.setCellValueFactory(new PropertyValueFactory<Option, String>("desc"));
			if (!currentSlide.getOptions().isEmpty() && currentSlide.getOptions() != null) {
				optionView.setItems(FXCollections.observableArrayList(currentSlide.getOptions()));
			} else {
				optionView.setItems(FXCollections.emptyObservableList());
			}
			slideId.setText(currentSlide.getId());
			slideDescription.setText(currentSlide.getDesc());
			slideTitle.setText(currentSlide.getTitle());
			ObservableList<String> choiceboxSlideID = FXCollections.observableArrayList(currentStory.getSlideIds());
			// ObservableList<String> choiceboxSlideID =
			// FXCollections.observableArrayList();
			// for (Slide index : currentStory.getSlides()) {
			// choiceboxSlideID.add(index.getId());
			// }
			slideSelection.setItems(choiceboxSlideID);
			if (currentSlide.getImage() == null || currentSlide.getImage().equals("")) {
				imageView.setVisible(false);
			} else {
				imageView.setVisible(true);
				InputStream input = new FileInputStream("image/slide/" + currentSlide.getImage());
				imageView.setImage(new Image(input));
			}
		}
	}

	@FXML
	private void handleSaveStory() throws FileNotFoundException {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		saveStory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				configureFileChooser("Saving Story", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showSaveDialog(stage);
				if (file != null) {
					saveFile(currentStory.toJSON(), file);
				}
			}
		});

	}

	private static void configureFileChooser(String title, FileChooser fileChooser,
			FileChooser.ExtensionFilter filter) {
		FileChooser.ExtensionFilter[] filters = { filter };
		configureFileChooser(title, fileChooser, filters);
	}

	private static void configureFileChooser(String title, FileChooser fileChooser,
			FileChooser.ExtensionFilter[] filter) {
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(Arrays.asList(filter));
	}

	private void saveFile(String content, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	private void handleSelectImage() throws IOException {
		// Slide Image button

		File imageFile = getFileFromUser();
		Path path = Paths.get(imageFile.getName());

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
		Path path = Paths.get(musicFile.getName());
		if (Files.notExists(path)) {
			Files.createFile(path);
			Files.copy(Paths.get(musicFile.getPath()), path, StandardCopyOption.REPLACE_EXISTING);
		}
		currentSlide.setMusic(musicFile.getName());
	}

	@FXML
	private void handleEditOptions() throws IOException {
		if (!this.optionView.getSelectionModel().isEmpty()) {
			int index = optionView.getSelectionModel().getSelectedIndex();
			this.helperForOption(currentSlide.getOption(index), index);
		}
	}

	@FXML
	private void handleAddOptions() throws IOException {
		this.helperForOption(null, -1);
	}

	private void helperForOption(Option option, int index) throws IOException {
		Stage dialog = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionEditorPane.fxml"));
		Parent root = loader.load();
		OptionEditorController controller = loader.getController();
		if (option != null) {
			controller.initData(this, option, index);
			dialog.setTitle("Edit option");
		} else {
			controller.initData(this);
			dialog.setTitle("Create new option");
		}
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(this.editOptions.getScene().getWindow());
		dialog.showAndWait();
		this.updateFields();
	}

	@FXML
	private void handleSaveButton() throws IOException {
		currentSlide.setDesc(slideDescription.getText());
		currentSlide.setId(slideId.getText());
		currentSlide.setTitle(slideTitle.getText());
		currentStory.addSlideOverride(currentSlide);
		updateFields();
	}

	@FXML
	private void handleDeleteButton() throws FileNotFoundException {
		// Back Button, discard changes OR save temp slide
		// Dunno what this does anyway
		if (currentSlide != null) {
			if (currentStory.contains(currentSlide.getId())) {
				currentStory.removeSlide(currentSlide.getId());
			}
			currentSlide = new Slide(null, null, null, null);
			updateFields();
		}
	}

	@FXML
	private void handleSlideDescription() {

		Text text = new Text(slideDescription.getText());
		HBox root = new HBox();
		root.getChildren().add(text);

	}

	/**Assumes ArrayList<Option> options is in the same order as the table
	 * 
	 * @throws FileNotFoundException
	 */
	@FXML
	private void handleDeleteOptions() throws FileNotFoundException {
//		List<Option> optionsToRemove = optionView.getSelectionModel().getSelectedItems();
		System.out.println(optionView.getSelectionModel().getSelectedIndex());
		if(optionView.getSelectionModel().getSelectedIndex() != -1) {
			ArrayList<Option> currentOptions = currentSlide.getOptions();
			currentOptions.remove(optionView.getSelectionModel().getSelectedIndex());
			currentSlide.setOptions(currentOptions);
		}
		updateFields();
	}
	
	// Source from
	// http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
	@FXML
	private void handleLoadStory() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		this.LoadStory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				configureFileChooser("Loading Story", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					readStory(file);
				}
			}
		});
	}

	private void readStory(File file) {
		String storyString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = reader.readLine()) != null)
				storyString += line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.currentStory = Story.fromJSON(storyString);
		this.slideSelectionHelper();

	}

	private void slideSelectionHelper() {
		if (currentStory != null) {
			ArrayList<String> slideIDStringList = currentStory.getSlideIds();
			this.slideSelection.setItems(FXCollections.observableArrayList(slideIDStringList));
		}
	}

	@FXML
	private void handleLoadLibrary() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemLibrary.fxml"));
		Parent root = loader.load();

		ItemLibraryController controller = loader.getController();
		controller.initData(this);

		Scene scene = new Scene(root);
		stage.initOwner(this.addOptions.getScene().getWindow());
		stage.initModality(Modality.WINDOW_MODAL);
		stage.setTitle("Item Library");
		stage.setScene(scene);
		stage.showAndWait();
		this.itemLibrary = controller.getItemLibrary();
		System.out.println(this.itemLibrary);
	}

	@FXML
	private void handleCloseButton() {
		Platform.exit();
	}

	@FXML
	private void handleDeleteMenuItem() {
		imageView.setImage(null);
	}

	@FXML
	private void handleAboutButton() {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("About GameBuilder");
		a.setHeaderText("Application to help with creating/editing slides for the Swedish Immigration Trail.");
		a.getDialogPane().setPrefSize(480, 120);
		a.showAndWait();
	}

	@FXML
	private void handleNewSlideButton() throws FileNotFoundException {
		currentSlide = new Slide(null, null, null, null);
		updateFields();
	}
	// End of handle Methods

	public Story getStory() {
		return currentStory;
	}

	public Slide getCurrentSlide() {
		return currentSlide;
	}

	public ItemLibrary getItemLibrary() {
		return itemLibrary;
	}

	/**
	 * @param itemLibrary
	 */
	public void setItemLibrary(ItemLibrary itemLibrary) {
		this.itemLibrary = itemLibrary;
	}

	/**
	 * Opens the file browser and allows the user to select a file
	 * 
	 * @return the file the user selected
	 */
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
	private MenuItem getLoadLibrary() {
		return loadLibrary;
	}

	/**
	 * @deprecated
	 */
	private void populateSlideChoice() {
		ArrayList<Slide> slides = (ArrayList<Slide>) currentStory.getSlides();
		for (Slide index : slides) {
			slideSelection.getItems().add(index.getId());
		}
	}

	/*
	 * public static void addTextLimiter(final TextField tf, final int maxLength) {
	 * tf.textProperty().addListener(new ChangeListener<String>() {
	 * 
	 * @Override public void changed(final ObservableValue<? extends String> ov,
	 * final String oldValue, final String newValue) { if (tf.getText().length() >
	 * maxLength) { String s = tf.getText().substring(0, maxLength); tf.setText(s);
	 * } } }); }
	 */

	/**
	 * @param loadLibrary
	 *            the loadLibrary to set
	 */
	private void setLoadLibrary(MenuItem loadLibrary) {
		this.loadLibrary = loadLibrary;
	}

}
