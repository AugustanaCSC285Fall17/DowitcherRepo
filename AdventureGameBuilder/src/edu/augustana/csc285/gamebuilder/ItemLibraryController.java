package edu.augustana.csc285.gamebuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.augustana.csc285.game.datamodel.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
		// For testing purpose only
		String libraryString = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("testLibrary.json")))) {

			String line;
			while ((line = reader.readLine()) != null)
				libraryString += line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.itemLibrary = ItemLibrary.fromJSON(libraryString);
		this.itemListHelper();
	}

	@FXML
	private void handleDeleteItem() {
		itemLibrary.removeItem(this.getCurrentItem());
		itemListHelper();
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
		if (this.checkLegalString(name)) {
			itemLibrary.addItem(name, desc, image);
			itemListHelper();
		}
		this.updateMainPaneLibrary();
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
		// this.controller.setItemLibrary(this.itemLibrary);
	}

	private boolean checkLegalString(String str) {
		if (str == null || str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkLegalInt(String str) {
		if (str != null && !str.equals("") && str.matches("[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkLegalDouble(String str) {
		if (str != null && !str.equals("") && str.matches("([0-9]*)\\.([0-9]*)")) {
			return true;
		} else {
			return false;
		}
	}

}
