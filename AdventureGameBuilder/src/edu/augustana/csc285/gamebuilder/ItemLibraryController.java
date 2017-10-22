package edu.augustana.csc285.gamebuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.augustana.csc285.game.datamodel.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ItemLibraryController {
	private ItemLibrary itemLibrary;
	private MainPanev2Controller controller;
	@FXML
	private ListView<String> itemList;
	@FXML
	private Button loadLibrary;
	@FXML
	private Button saveLibrary;
	@FXML
	private Button deleteItem;
	@FXML
	private Button loadItem;
	@FXML
	private TextField nameTextField;
	@FXML
	private TextField descTextField;
	@FXML
	private Button selectImage;
	@FXML
	private TextArea currentImageTextArea;
	@FXML
	private Button saveItem;

	public void initData(MainPanev2Controller controller) {
		this.controller = controller;
	}

	@FXML
	private void initialize() {
		if (controller.getItemLibrary() != null) {
			this.itemLibrary = controller.getItemLibrary();
			this.itemListHelper();
		}
	}

	@FXML // Source from
			// http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
	private void handleLoadLibrary() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		loadLibrary.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				configureFileChooser("Loading Library", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					readLibrary(file);
				}
			}
		});
	}

	private void readLibrary(File file) {
		String libraryString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = reader.readLine()) != null)
				libraryString += line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.itemLibrary = ItemLibrary.fromJSON(libraryString);
		this.itemListHelper();

	}

	// Source from http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
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

	@FXML
	private void handleSaveLibrary() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		saveLibrary.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				configureFileChooser("Saving Library", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showSaveDialog(stage);
				if (file != null) {
					saveFile(itemLibrary.toJSON(), file);
				}
			}
		});
	}

	// Source
	// http://java-buddy.blogspot.com/2012/05/save-file-with-javafx-filechooser.html
	private void saveFile(String content, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
		}

	}

	@FXML
	private void handleDeleteItem() {
		Item item = this.getCurrentItem();
		if (item != null) {
			itemLibrary.removeItem(this.getCurrentItem());
			itemListHelper();
		}
	}

	@FXML
	private void handleLoadItem() {
		Item item = this.getCurrentItem();
		if (item != null) {
			this.nameTextField.setText(item.getName());
			this.descTextField.setText(item.getDesc());
			this.currentImageTextArea.setText(item.getImage());
		}
		this.updateMainPaneLibrary();
	}

	@FXML
	private void handleSaveItem() {
		String name = nameTextField.getText();
		String desc = descTextField.getText();
		String image = currentImageTextArea.getText();
		if (Helper.checkLegalString(name)) {
			itemLibrary.addItem(name, desc, image);
			itemListHelper();
		}
		this.updateMainPaneLibrary();
	}

	@FXML
	private void handleSelectImage() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		selectImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				ExtensionFilter[] filters = { new FileChooser.ExtensionFilter("JPG", "*.jpg"),
						new FileChooser.ExtensionFilter("PNG", "*.png") };
				configureFileChooser("Select an image", fileChooser, filters);
				File file = fileChooser.showOpenDialog(stage);
				if (file != null) {
					currentImageTextArea.setText(file.getName());
				}
			}
		});

	}

	private void itemListHelper() {
		ArrayList<String> itemStringList = itemLibrary.getItemNameList();
		this.itemList.setItems(FXCollections.observableArrayList(itemStringList));
	}

	private Item getCurrentItem() {
		String currentItem = this.itemList.getSelectionModel().getSelectedItem();
		if (currentItem != null && currentItem != "") {
			return itemLibrary.getItem(currentItem);
		}
		return null;
	}

	private void updateMainPaneLibrary() {
		this.controller.setItemLibrary(this.itemLibrary);
	}

}
