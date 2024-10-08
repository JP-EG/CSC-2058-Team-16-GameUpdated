package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ControllerGameIntro {
	
	@FXML 
	private Button playButton;
	
	public static Stage stage;
	public Scene scene;
	
	public static void centerStageOnScreen() {
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	    double centerX = primaryScreenBounds.getMinX() + (primaryScreenBounds.getWidth() / 2);
	    double centerY = primaryScreenBounds.getMinY() + (primaryScreenBounds.getHeight() / 2);

	    stage.setX(centerX - (stage.getWidth() / 2));
	    stage.setY(centerY - (stage.getHeight() / 2));
	}
	
	public void switchToPlayerSelect(ActionEvent event) throws IOException {
        // Pass selectedPlayers to the ControllerGame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlFiles/PlayerSelectScreen.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        centerStageOnScreen();
        stage.show();
    }




}