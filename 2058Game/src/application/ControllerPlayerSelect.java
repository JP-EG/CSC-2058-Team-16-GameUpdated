package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerPlayerSelect {

	@FXML
	private Label choosePlayerLabel;

	@FXML
	private Pane pane;

	@FXML
	private Button characterSelectButton;

	@FXML
	private ImageView character1, character2, character3, character4, character5, character6, character7, character8,
			character9, character10, character11, character12;

	@FXML
	private Rectangle rectangle1, rectangle2, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7, rectangle8,
			rectangle9, rectangle10, rectangle11, rectangle12;

	private int counter;

	private Stage stage;
	private Scene scene;
	
	// Add a field to store the selected players
    public static List<String> selectedPlayers = new ArrayList<>();

	// Image character1Image = new
	// Image(getClass().getResourceAsStream("/Images/character2.png"));

    public void switchToPIS(ActionEvent event) throws IOException {
        // Pass selectedPlayers to the ControllerGame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlFiles/PlayerInfoScreen.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        ControllerGameIntro.centerStageOnScreen();
        stage.show();
    }

	/*
	 * selectedPlayer method selects and un-selects if a player is chosen in the
	 * player board with the help of the toggleVisibility method
	 */
	public void selectedPlayer(MouseEvent e) {
		ImageView clickedImageView = (ImageView) e.getSource();
		

		switch (clickedImageView.getId()) {
		case "character1":
			toggleVisibility(rectangle1, clickedImageView.getId());
			break;
		case "character2":
			toggleVisibility(rectangle2, clickedImageView.getId());
			break;
		case "character3":
			toggleVisibility(rectangle3, clickedImageView.getId());
			break;
		case "character4":
			toggleVisibility(rectangle4, clickedImageView.getId());
			break;
		case "character5":
			toggleVisibility(rectangle5, clickedImageView.getId());
			break;
		case "character6":
			toggleVisibility(rectangle6, clickedImageView.getId());
			break;
		case "character7":
			toggleVisibility(rectangle7, clickedImageView.getId());
			break;
		case "character8":
			toggleVisibility(rectangle8, clickedImageView.getId());
			break;
		case "character9":
			toggleVisibility(rectangle9, clickedImageView.getId());
			break;
		case "character10":
			toggleVisibility(rectangle10, clickedImageView.getId());
			break;
		case "character11":
			toggleVisibility(rectangle11, clickedImageView.getId());
			break;
		case "character12":
			toggleVisibility(rectangle12, clickedImageView.getId());
			break;
		}
		nextButton();
	}

	/*
	 * This method checks if a rectangle is visible on not
	 */
	public void toggleVisibility(Rectangle rectangle, String character) {
		// Toggle the visibility of the rectangle
		boolean currentVisibility = rectangle.isVisible();
		rectangle.setVisible(!rectangle.isVisible());

		// Update the counter based on the visibility state
		if (!currentVisibility) {
			// If the rectangle is becoming visible, increment the counter
			selectedPlayers.add(character);
			counter++;
		} else {
			// If the rectangle is becoming invisible, decrement the counter
			selectedPlayers.remove(character);
			counter--;
		}
		
	}
	
	private void nextButton() {

		if (counter >= 2 && counter <= 4) {
			choosePlayerLabel.setVisible(false);
			characterSelectButton.setDisable(false);
			characterSelectButton.setVisible(true);
		} else {
			choosePlayerLabel.setVisible(true);
			characterSelectButton.setDisable(true);
			characterSelectButton.setVisible(false);
		}

	}

}
