package edu.augustana.csc285.gamebuilder;

import java.awt.Desktop;
import java.awt.Image;
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
import javafx.stage.Stage;

public class LoadStoryController {
	private StoryLibrary storyLibrary;
	private MainPanev2Controller controller;
	@FXML
	private ListView<String> storyList;
	@FXML
	private Button loadLibrary;
	@FXML
	private Button saveLibrary;
	@FXML
	private Button deleteStory;
	@FXML
	private Button loadStory;
	@FXML
	private TextField startingIndex;
	@FXML
	private Button selectDefaultMusic;
	@FXML
	private Button saveStory;

	public void initData(MainPanev2Controller controller) {
		this.controller = controller;
	}
	
	@FXML
	private void handleLoadLibrary() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		loadLibrary.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e){
				configureFileChooser("Loading Library", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showOpenDialog(stage);
				if(file != null) {
					readLibrary(file);
				}
			}
		});
	}
	
	@FXML
	private void handleSaveLibrary() {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		saveLibrary.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				configureFileChooser("Saving Library", fileChooser, new FileChooser.ExtensionFilter("JSON", "*.json"));
				File file = fileChooser.showSaveDialog(stage);
				if (file != null) {
					saveFile(storyLibrary.toJSON(), file);
				}
			}
		});
	}
	
	@FXML
	private void handleDeleteStory() {
		
	}
	
	@FXML
	private void handleLoadStory() {
		
	}
	
	@FXML
	private void handleSelectDefaultMusic() {
		
	}
	
	@FXML
	private void handleSaveStory() {
		
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
	
	private void readLibrary(File file) {
		String libraryString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;
			while ((line = reader.readLine()) != null)
				libraryString += line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.storyLibrary = StoryLibrary.fromJSON(libraryString);
		this.storyListHelper();
	}
	
	private void storyListHelper() {
		ArrayList<String> storyStringList = storyLibrary.getStoryNameList();
		this.storyList.setItems(FXCollections.observableArrayList(storyStringList));
	}

}
