package edu.augustana.csc285.gamebuilder.condition;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestingConditionApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("GenderCondition.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("ItemCondition.fxml"));
		Parent root = FXMLLoader.load(getClass().getResource("PlayerStatCondition.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Game Builder for Swedish Immigration Trail");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}