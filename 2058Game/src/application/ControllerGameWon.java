package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerGameWon {

	@FXML
	AnchorPane pane1;

	@FXML
	Label label1, label2, label3, label4, completeLabel;

	@FXML
	ImageView image1;

	@FXML
	Button playButton, exitButton;

	Stage stage;
	Scene scene;

	private ControllerPlayerInfoScreen controllerPlayerInfoScreen = new ControllerPlayerInfoScreen();

	public void exitGame(ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	public void playAgain(ActionEvent event) throws IOException {
		ControllerPlayerSelect.selectedPlayers.clear(); // Clear the list for new players being selected
		ControllerGame.squareToPlayer.clear(); // Clear the squares of the players from previous game
		Player.nextID = 0; // Reset the counter for creating new players

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlFiles/PlayerSelectScreen.fxml"));
		Parent root = loader.load();
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		controllerPlayerInfoScreen.centerStageOnScreen();
		stage.show();

	}

}