package edu.augustana.csc285.gamebuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

import edu.augustana.csc285.game.datamodel.Item;
import edu.augustana.csc285.game.datamodel.ItemLibrary;
import edu.augustana.csc285.game.datamodel.Story;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class ItemLibraryController {

	private File libraryFile;
	private ItemLibrary library;
	private Item item;
	
	@FXML private ScrollBar libraryContents;
	@FXML private Button deleteItemButton;
	@FXML private Button loadItemButton;
	
	@FXML private Button loadLibraryButton;
	@FXML private Button saveLibraryButton;
	
	
	@FXML private TextField nameField;
	@FXML private TextArea descriptionArea;
	@FXML private TextArea imageArea;
	@FXML private Button imageSelectButton;
	@FXML private Button saveItemButton;
	
	@FXML 
	private void initialize() {
		
	}
	
	@FXML 
	private void loadLibrary() throws IOException{
		libraryFile = getFileFromUser();
		BufferedReader reader = new BufferedReader(new FileReader(libraryFile));
		
		String libraryJson = "";
		String nextLine = reader.readLine();
		while(nextLine != null) {
			libraryJson += nextLine;
		}		
		library = ItemLibrary.fromJSON(libraryJson);
		//TODO update scrollbar
 	}
	
	@FXML 
	private void saveLibrary() throws IOException {
		PrintStream stream = new PrintStream(libraryFile);
		stream.print(library.toJSON());
		new Alert(AlertType.INFORMATION, "All changes saved.").showAndWait();
	}
	
	@FXML
	private void handleDeleteItemButton() {
		//library.removeItem(item);
		//TODO remove from scrollbar
	}
	
	@FXML 
	private void handleLoadItemButton() {
	
	}
	
	@FXML
	private void handleImageSelectButton() {
		File imageFile = getFileFromUser();
		imageArea.setText(imageFile.getPath());
	}
	
	@FXML
	private void handleSaveItemButton() {
		item.setName(nameField.getText());
		item.setDesc(descriptionArea.getText());
		item.setImage(imageArea.getText());
	}
	
	
	@FXML 
	private File getFileFromUser() {
		FileChooser fileBrowser = new FileChooser();
		return fileBrowser.showOpenDialog(null);
	}
	
	
}
