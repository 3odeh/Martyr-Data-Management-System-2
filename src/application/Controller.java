package application;

import java.io.File;
import java.util.Optional;

import data.MyDoubleLinkedList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser.ExtensionFilter;

// This class for organizes the navigation between pages
public class Controller {

	// Attributes of Controller
	public static MyDoubleLinkedList data;
	public static Stage mainStage;
	private static StackPane stackPane;
	private static Scene scene;
	private static HomePane homePane;
	private static int topOfStack = 0;

	// This method to initialize the attributes and setting of program then start
	// the program
	public static void initController() {
		data = new MyDoubleLinkedList();
		stackPane = new StackPane();
		mainStage = new Stage();
		scene = new Scene(stackPane);
		homePane = new HomePane();

		stackPane.getChildren().add(homePane);
		mainStage.setScene(scene);
		mainStage.setHeight(700);
		mainStage.setWidth(1400);
		mainStage.setTitle("Martyr Program");
		mainStage.setResizable(false);
		mainStage.show();

		// This to handle if user does not save the changes
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				if (data.ifDataChanges)
					if(!handleChange()) {
						event.consume();
					}
			}
		});
	}

	// This method to add input pane on top of stack pane
	public static void pushNode(Pane startPane) {
		stackPane.getChildren().add(startPane);
		startPane.setStyle("-fx-background-color: #f4f4f4");
		topOfStack++;
	}

	// This method to remove the top of stack pane
	public static void popNode() {
		stackPane.getChildren().remove(topOfStack);
		topOfStack--;
	}

	// This method to replace input pane with top of stack pane
	public static void replaceNode(Pane startPane) {
		popNode();
		pushNode(startPane);
	}

	// This method to add new home pane object
	public static void notifyHomePane() {
		stackPane.getChildren().remove(homePane);
		homePane = new HomePane();
		stackPane.getChildren().add(0, homePane);
		data.ifDataChanges = true;
	}

	// This method to handle if user miss save the changes
	private static boolean handleChange() {
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText("You wanna save the changes?");
		ButtonType saveBtn = new ButtonType("Save Data");
		ButtonType closeBtn = new ButtonType("Close");
		ButtonType cancelBtn = new ButtonType("Cancel");
		a.getButtonTypes().clear();
		a.getButtonTypes().add(cancelBtn);
		a.getButtonTypes().add(closeBtn);
		a.getButtonTypes().add(saveBtn);
		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == saveBtn) {
			FileChooser saveFileChooser = new FileChooser();
			saveFileChooser.setTitle("Save File");
			saveFileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"),
					new ExtensionFilter("Excel File", "*.csv"));
			File file = saveFileChooser.showSaveDialog(Controller.mainStage);
			if (file != null) {
				try {
					if (!file.exists())
						file.createNewFile();
					data.printListToFile(file);
					return true;
				} catch (Exception e) {
					GeneralPanes.errorAlert("Please select other file");
					return handleChange();
				}
			} else {
				GeneralPanes.errorAlert("No selected file");
				return handleChange();
			}
		}else if (result.get() == cancelBtn) {
			return false;
		}
		return true;
	}

}
