package edu.augustana.csc285.gamebuilder;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdvancedOptionApplicationTest extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdvancedOption.fxml"));
        Scene scene = new Scene(root);
    
        primaryStage.setTitle("Game Builder for Swedish Immigration Trail");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args); 
	}
}