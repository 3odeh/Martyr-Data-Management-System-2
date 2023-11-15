package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import data.Martyr;
import data.MyDoubleNode;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.CharacterStringConverter;

//This class is a location screen that display the statistic of locations
public class StatisticPane extends BorderPane {

	// This constructor make a object and add the component and statistic of
	// selected location to the pane
	public StatisticPane(MyDoubleNode current) {

		// The top of the Screen (border pane)
		Button backBtn = new Button("Back");
		this.setMargin(backBtn, new Insets(30));
		this.setTop(backBtn);
		// This action for back button, To return to the home page
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Controller.popNode();
			}
		});

		// The left of the Screen (border pane)
		Button leftBtn = new Button("Left");
		leftBtn.setPrefSize(50, 50);
		this.setMargin(leftBtn, new Insets(50));
		this.setAlignment(leftBtn, Pos.CENTER);
		this.setLeft(leftBtn);

		// This action for left button, To display the previous location
		leftBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				StatisticPane sp = new StatisticPane(current.getPre());
				Controller.replaceNode(sp);
			}
		});
		
	

		// The right of the Screen (border pane)
		Button rightBtn = new Button("Right");
		rightBtn.setPrefSize(50, 50);
		this.setMargin(rightBtn, new Insets(50));
		this.setAlignment(rightBtn, Pos.CENTER);
		this.setRight(rightBtn);

		// This action for right button, To display the next location
		rightBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				StatisticPane sp = new StatisticPane(current.getNext());
				Controller.replaceNode(sp);
			}
		});

		// The center of the Screen (border pane)
		Label locationLabel = new Label(current.getLocation());
		locationLabel.setFont(new Font("Arial", 30));
		VBox centerVBox = new VBox(locationLabel);
		centerVBox.setAlignment(Pos.CENTER);
		centerVBox.setSpacing(20);
		this.setCenter(centerVBox);

		// This if to handle if there are data or not
		if (current.getDateTree().isNotEmpty()) {
			
			// if there are data it will add in the center under the title
			VBox centerScrollVBox = new VBox();
			
			// This code for show the statistic of first AVL tree
			Label avl1Label = new Label("The first AVL");
			avl1Label.setFont(new Font("Arial", 26));
			Label heigh1Label = new Label("The Height = " + current.getMartyrTree().getHeight());
			heigh1Label.setFont(new Font("Arial", 20));
			VBox levelVBox = new VBox();
			levelVBox.setAlignment(Pos.CENTER);
			levelVBox.setSpacing(5);
			levelVBox.setPadding(new Insets(10));
			// This to add martyr in avl tree with level by level 
			current.getMartyrTree().addDataLevelByLevel(levelVBox);
			
			
			// This code for show the statistic of second AVL tree
			Label avl2Label = new Label("The second AVL");
			avl2Label.setFont(new Font("Arial", 26));
			Label heigh2Label = new Label("The Height = " + current.getDateTree().getHeight());
			heigh2Label.setFont(new Font("Arial", 20));
			Date date = current.getDateTree().getDateOfMaxNum();
			Label dateLabel = new Label(
					" The date of maximum number of martyrs = " + ((date == null) ? "No Data" : new SimpleDateFormat("MM/dd/yyyy").format(date)));
			dateLabel.setFont(new Font("Arial", 20));
			// Make a table view of martyr
			TableView<Martyr> tv = new TableView<Martyr>();
			// Make a column name of martyr
			TableColumn<Martyr, String> nameCol = new TableColumn<Martyr, String>("Name");
			nameCol.setCellValueFactory(new PropertyValueFactory<Martyr, String>("name"));
			nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
			nameCol.setSortable(false);			
			// Make a column age of martyr
			TableColumn<Martyr, String> ageCol = new TableColumn<Martyr, String>("Age");
			// This to customize the insert data to the column
			ageCol.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Martyr, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Martyr, String> data) {
							if (data.getValue().getAge() < 0)
								return new SimpleStringProperty("No Data");
							return new SimpleStringProperty("" + data.getValue().getAge());
						}
					});
			ageCol.setSortable(false);
			ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
			// Make a date of death column
			TableColumn<Martyr, String> deathDateCol = new TableColumn<Martyr, String>("Date of death");
			deathDateCol.setCellValueFactory(new PropertyValueFactory<Martyr, String>("simpleDateOfDeath"));
			deathDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
			deathDateCol.setSortable(false);      
			// Make a gender column
			TableColumn<Martyr, Character> genderCol = new TableColumn<Martyr, Character>("Gender");
			genderCol.setCellValueFactory(new PropertyValueFactory<Martyr, Character>("gender"));
			genderCol.setCellFactory(TextFieldTableCell.forTableColumn(new CharacterStringConverter()));
			genderCol.setSortable(false);
			// Add all columns to the table view
			tv.getColumns().addAll(nameCol, ageCol, genderCol, deathDateCol);
			tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tv.setEditable(false);
			tv.setMinHeight(300);
			// Add all martyr in this location
			current.getDateTree().addDataRevToTableView(tv);

			// add all statistic component under the title 
			centerScrollVBox.getChildren().addAll(avl1Label, heigh1Label,levelVBox,avl2Label, heigh2Label, dateLabel,tv);
			centerScrollVBox.setAlignment(Pos.CENTER);
			centerScrollVBox.setSpacing(10);
			centerScrollVBox.setPadding(new Insets(50));
			ScrollPane scrollPane = new ScrollPane(centerScrollVBox);
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			scrollPane.setMinHeight(500);
			centerVBox.getChildren().add(scrollPane);
		} else {
			// When their is no data (martyr) in current location
			Label noData = new Label("No martye added");
			noData.setFont(new Font("Arial", 20));
			noData.setTextFill(Color.RED);
			centerVBox.getChildren().add(noData);
		}

	}

}
