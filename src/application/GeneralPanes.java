package application;

import java.util.Optional;

import data.MyDoubleLinkedList;
import data.MyDoubleNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// This class contain pane methods to use it in the other classes 
public class GeneralPanes {

	// This method return the location pane have the name of location and 2 buttons
	// for statistic and info of the location
	public static GridPane locationPane(MyDoubleNode current, MyDoubleLinkedList cu) {

		GridPane gp = new GridPane();
		Button infoBtn = new Button("Info");
		infoBtn.setPrefWidth(70);
		HBox hb = new HBox(infoBtn);
		hb.setAlignment(Pos.CENTER);

		Label name = new Label(current.getLocation());
		name.setStyle("-fx-font-weight: bold; -fx-font-size: 18;-fx-alignment:CENTER;");
		name.setMaxWidth(Double.MAX_VALUE);
		gp.add(name, 1, 1,3,1);
		gp.add(hb, 3, 2);

		gp.setVgap(15);
		gp.setHgap(15);
		gp.setPadding(new Insets(30));
		gp.setAlignment(Pos.CENTER);
		infoBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				LocationPane lp = new LocationPane(current);
				Controller.pushNode(lp);
			}
		});

		Button statBtn = new Button("Statistic");
		statBtn.setPrefWidth(70);
		gp.add(statBtn, 1, 2);
		statBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				StatisticPane sp = new StatisticPane(current);
				Controller.pushNode(sp);

			}
		});

		return gp;
	}

	// This method return the all location pane have all locations and 1 buttons
	// for info of the location
	public static GridPane locationPane(MyDoubleLinkedList cu) {

		GridPane gp = new GridPane();
		Button infoBtn = new Button("Info");
		infoBtn.setPrefWidth(70);

		Label name = new Label("All");
		name.setStyle("-fx-font-weight: bold; -fx-font-size: 18;-fx-alignment:CENTER;");
		name.setMaxWidth(Double.MAX_VALUE);
		gp.add(name, 1, 1, 3, 1);
		gp.add(infoBtn, 1, 2, 3, 2);

		gp.setVgap(15);
		gp.setHgap(15);
		gp.setPadding(new Insets(30, 60, 30, 60));
		infoBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				LocationPane lp = new LocationPane();
				Controller.pushNode(lp);
			}
		});

		return gp;
	}

	// This method have alert stage to show error message to the user
	public static void errorAlert(String message) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.showAndWait();
	}

	// This method have warning stage to show warning message to the user
	public static boolean warningMessage(String string) {
		Alert a = new Alert(AlertType.WARNING);
		a.setContentText(string);
		a.getButtonTypes().add(ButtonType.CANCEL);
		Optional<ButtonType> result = a.showAndWait();
		return (result.get() == ButtonType.OK);
	}

	// This method have alert stage to show a message to the user
	public static void completAlert(String message) {
		Alert a = new Alert(AlertType.CONFIRMATION);
		a.setContentText(message);
		a.showAndWait();
	}

}
