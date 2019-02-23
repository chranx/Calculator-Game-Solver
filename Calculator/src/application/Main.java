package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage)
			throws IOException {

				Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
				Scene scene = new Scene(root);
				primaryStage.setTitle("Calculator");
				primaryStage.setScene(scene);
				primaryStage.setResizable(false);
				primaryStage.show();

			}
    public static void main(String[] args) {
        launch(args);
    }
}
