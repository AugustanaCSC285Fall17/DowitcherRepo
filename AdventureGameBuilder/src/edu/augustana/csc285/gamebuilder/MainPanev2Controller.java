package edu.augustana.csc285.gamebuilder;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

import edu.augustana.csc285.game.datamodel.*;
import edu.augustana.csc285.game.datamodel.condition.ConditionOperation;
import edu.augustana.csc285.game.datamodel.condition.ItemCondition;
import edu.augustana.csc285.game.datamodel.effect.Effect;
import edu.augustana.csc285.game.datamodel.effect.EffectOperation;
import edu.augustana.csc285.game.datamodel.effect.ItemEffect;
import edu.augustana.csc285.gamebuilder.effect.GeneralEffectController;

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
	private MenuItem deleteButton;
	@FXML
	private MenuItem aboutButton;
	@FXML
	private ChoiceBox<String> slideSelection;
	@FXML
	private Button newSlideButton;
	@FXML
	private MenuItem saveLibrary;

	private Story currentStory;
	private Slide currentSlide;
	private Path storyJSONPath;
	private File JSONFile;

	@FXML
	private void initialize() {
		currentStory = new Story("0");
		Item sek = new Item("Sek", "Currency of Sweden", 1, null);
		Item officialPapers = new Item("Official papers", null, 1, null);
		Item forgedPapers = new Item("Forged papers", null, 1, null);
//		this.itemLibrary = new ItemLibrary();
//		itemLibrary.addItem(sek);
//		itemLibrary.addItem(officialPapers);
//		itemLibrary.addItem(forgedPapers);
		this.currentSlide = new Slide("GameData/SlideImages/slide9.jpg",
				"To start your journey, you need a few provisions. The first and foremost being your papers. You have $70 USD worth of Kronor (Swedish money)  right now. Do you:\n",
				null, "9");
		Option temp = new Option("Buy official papers from your home parish 80 SEK", null, null, "10", null, null);
		temp.addFeasibleCondition(new ItemCondition(new Item(sek, 80), ConditionOperation.GREATER_OR_EQUAL));
		temp.addEffect(new ItemEffect(new Item(officialPapers, 1), EffectOperation.PLUS));
		temp.addEffect(new ItemEffect(new Item(sek, 80), EffectOperation.MINUS));
		currentSlide.addOption(temp);
		temp = new Option("Buy forged papers 40 SEK", null, null, "10", null, null);
		temp.addFeasibleCondition(new ItemCondition(new Item(sek, 40), ConditionOperation.GREATER_OR_EQUAL));
		temp.addEffect(new ItemEffect(forgedPapers, EffectOperation.PLUS));
		temp.addEffect(new ItemEffect(new Item(sek, 40), EffectOperation.MINUS));
		currentSlide.addOption(temp);
		currentStory.addSlide(currentSlide);
		updateFields();
	}

	private void updateFields() {
		if (currentSlide != null) {
			this.nextSlideView.setCellValueFactory(new PropertyValueFactory<Option, String>("nextSlideIndex"));
			this.optionDescView.setCellValueFactory(new PropertyValueFactory<Option, String>("desc"));
			this.optionView.setItems(FXCollections.observableArrayList(currentSlide.getOptions()));
			this.slideId.setText(currentSlide.getId());
			this.slideDescription.setText(currentSlide.getDesc());
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

		/*
		 * Text inftx = new Text("Infrastructure"); StackPane pane = new
		 * StackPane();
		 * 
		 * pane.getChildren().add(path); pane.getChildren().add(inftx);
		 * 
		 * pane.setAlignment(Pos.CENTER);
		 */
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
		// Save Button
		// Files.createFile(storyJSONPath);
		// Files.copy(Paths.get(JSONFile.getPath()), storyJSONPath,
		// StandardCopyOption.REPLACE_EXISTING);
		saveSlide();

	}

	@FXML
	private void handleBackButton() {
		// Back Button, discard changes OR save temp slide
		// Dunno what this does anyway

	}

	@FXML
	private void handleSlideDescription() {

		Text text = new Text(slideDescription.getText());
		// text.setFont(Font.font ("Arial", 27));
		HBox root = new HBox();

		// root.getChildren().add(iv1);
		root.getChildren().add(text);

	}

	@FXML
	private void handleLoadStory() throws IOException {
		JSONFile = getFileFromUser();
		storyJSONPath = Paths.get(JSONFile.getName());

		if (Files.notExists(storyJSONPath)) {
			Files.createFile(storyJSONPath);
			Files.copy(Paths.get(JSONFile.getPath()), storyJSONPath, StandardCopyOption.REPLACE_EXISTING);
		}
		String jsonString = "";
		Scanner fileParse = new Scanner(JSONFile);
		while (fileParse.hasNextLine()) {
			jsonString = jsonString + fileParse.nextLine();
		}
		fileParse.close();
		currentStory = Story.fromJSON(jsonString);
		// currentSlide.setMusic(musicFile.getName());
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
	private void handleDeleteButton() {
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
	private void handleSlideSelectionChoiceBox() {
		populateSlideChoice();
<<<<<<< HEAD
=======

>>>>>>> 8b10b3ca9f87e6902cbeb8c3eb505df9af5dce6c
	}

	@FXML
	private void handleNewSlideButton() {
		clearMainPane();
<<<<<<< HEAD
=======

>>>>>>> 8b10b3ca9f87e6902cbeb8c3eb505df9af5dce6c
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

	public void setItemLibrary(ItemLibrary itemLibrary) {
		this.itemLibrary = itemLibrary;
	}

	private File getFileFromUser() {
		FileChooser fileBrowser = new FileChooser();
		return fileBrowser.showOpenDialog(null);
	}

	/**
	 * Returns an array containing the story files Uses Java I/O for
	 * compatability with gamebuilder
	 */
	private static File[] getStoryFiles() {
		return new File("core/storyData").listFiles();
	}

	/**
	 * Used when starting a new slide and don't want any previous fields to be
	 * visible
	 */
	private void clearMainPane() {
		currentSlide = new Slide();
		imageView.setImage(null);
		slideId.setText("");
		slideId.setPromptText("Enter Slide Id");
		slideDescription.setText("");
		slideId.setPromptText("Enter Slide Description");
		ObservableList<Option> options = FXCollections.emptyObservableList();
		optionView.setItems(options);

		// optionView.set
	}

	/**
	 * @return the loadLibrary
	 */
	private MenuItem getLoadLibrary() {
		return loadLibrary;
	}

	private void saveSlide() {
		currentSlide.setDesc(slideDescription.getText());
		currentSlide.setId(slideId.getText());
		currentStory.addSlide(currentSlide);

	}

	private void populateSlideChoice() {
		ArrayList<Slide> slides = (ArrayList<Slide>) currentStory.getSlides();
		for (Slide index : slides) {
			slideSelection.getItems().add(index.getId());
		}

	}

	@FXML
	private void handleSlideId() {

	}

	/**
	 * @param loadLibrary
	 *            the loadLibrary to set
	 */
	private void setLoadLibrary(MenuItem loadLibrary) {
		this.loadLibrary = loadLibrary;
	}

}
