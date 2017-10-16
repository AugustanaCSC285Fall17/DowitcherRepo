package edu.augustana.csc285.gamebuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import edu.augustana.csc285.game.datamodel.ItemLibrary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameBuilderApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainPaneController.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Game Builder for Swedish Immigration Trail");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
//		String libraryString = "";
//		try (Scanner scanner = new Scanner(new File("testLibrary.json"))) {
//
//			while (scanner.hasNext()) {
//				libraryString += scanner.next();
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		ItemLibrary itemLibrary = ItemLibrary.fromJSON(libraryString);
//		System.out.println(itemLibrary);
		launch(args);
	}
}
