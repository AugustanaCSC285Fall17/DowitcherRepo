package edu.augustana.csc285.gamebuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import edu.augustana.csc285.game.datamodel.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdvancedOptionApplicationTest extends Application {

	public Stage showAdvancedOptionApplication() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdvancedOption.fxml"));

		Stage stage = new Stage(StageStyle.DECORATED);
		stage.setScene(new Scene((Pane) loader.load()));

		AdvancedOptionController controller = loader.<AdvancedOptionController>getController();

		// This is temporary to initialize a library
		String libraryString = "";
		try (Scanner scanner = new Scanner(new File("/adventure-game-core/assets/libraryData/testLibrary.json"))) {

			while (scanner.hasNext()) {
				libraryString += scanner.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ItemLibrary itemLibrary = ItemLibrary.fromJSON(libraryString);
		controller.initData(new Option(), itemLibrary);

		stage.show();
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = this.showAdvancedOptionApplication();
		primaryStage.show();
	}
}
