module DS_project3 {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	
	opens application to javafx.base, javafx.graphics, javafx.fxml;
	opens data to javafx.base, javafx.graphics, javafx.fxml; 
}
