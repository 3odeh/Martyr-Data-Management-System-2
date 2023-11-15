package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// To initialize the Controller
			Controller.initController();
		} catch (Exception e) {
			GeneralPanes.errorAlert("Error!");
		}
	}

	public static void main(String[] args) {
		//To start program
		launch(args);
	}
}
