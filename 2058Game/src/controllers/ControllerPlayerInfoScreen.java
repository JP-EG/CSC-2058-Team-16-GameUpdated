package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ControllerPlayerInfoScreen {
	@FXML 
	private TextField p1TextField, p2TextField, p3TextField, p4TextField;
	
	@FXML
	private ImageView p1ImageView, p2ImageView, p3ImageView, p4ImageView;
	
	@FXML
	private Button startButton, finishNamesButton;
	
	@FXML
	private Label choosePlayerLabel;
	
	private static Stage stage;
	private Scene scene;
	
	private Map<String, String> characterImageMap = new HashMap<>();
	
	public static ArrayList<String> names = new ArrayList<String>();
	
	public void initialize() {
		
		characterImageMap.put("character1", "/Images/character1.png");
		characterImageMap.put("character2", "/Images/character2.png");
		characterImageMap.put("character3", "/Images/character3.png");
		characterImageMap.put("character4", "/Images/character4.png");
		characterImageMap.put("character5", "/Images/character5.png");
		characterImageMap.put("character6", "/Images/character6.png");
		characterImageMap.put("character7", "/Images/character7.png");
		characterImageMap.put("character8", "/Images/character8.png");
		characterImageMap.put("character9", "/Images/character9.png");
		characterImageMap.put("character10", "/Images/character10.png");
		characterImageMap.put("character11", "/Images/character11.png");
		characterImageMap.put("character12", "/Images/character12.png");
		
		setPlayersVisible();
		
	}
	
	public void centerStageOnScreen() {
	    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	    double centerX = primaryScreenBounds.getMinX() + (primaryScreenBounds.getWidth() / 2);
	    double centerY = primaryScreenBounds.getMinY() + (primaryScreenBounds.getHeight() / 2);

	    stage.setX(centerX - (stage.getWidth() / 2));
	    stage.setY(centerY - (stage.getHeight() / 2));
	}
	
	public void switchToGame(ActionEvent event) throws IOException {
		getPlayerNames();
        // Pass selectedPlayers to the ControllerGame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlFiles/Game.fxml"));
        Parent root = loader.load();
        ControllerGame gameController = loader.getController();
        gameController.initializeGame();
        gameController.createPlayers(ControllerPlayerSelect.selectedPlayers);
        gameController.initialize(ControllerPlayerSelect.selectedPlayers);
        gameController.initializeDiceRollButtonImage();
        gameController.createAssignPlayerLabels();
        gameController.createTurnPlayerLabels();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        centerStageOnScreen();
        stage.show();
    }
	
	public void setPlayersVisible() {
		
		if(ControllerPlayerSelect.selectedPlayers.size() == 2) {
			p1ImageView.setVisible(false);
			p4ImageView.setVisible(false);
			p1TextField.setVisible(false);
			p4TextField.setVisible(false);
			
			String character1 = ControllerPlayerSelect.selectedPlayers.get(0);
			String imagePath1 = characterImageMap.get(character1);
			p2ImageView.setImage(new Image(imagePath1));
			String character2 = ControllerPlayerSelect.selectedPlayers.get(1);
			String imagePath2 = characterImageMap.get(character2);
			p3ImageView.setImage(new Image(imagePath2));
			
		}else if(ControllerPlayerSelect.selectedPlayers.size() == 3) {
			p4ImageView.setVisible(false);
			p4TextField.setVisible(false);
			
			String character1 = ControllerPlayerSelect.selectedPlayers.get(0);
			String imagePath1 = characterImageMap.get(character1);
			p1ImageView.setImage(new Image(imagePath1));
			String character2 = ControllerPlayerSelect.selectedPlayers.get(1);
			String imagePath2 = characterImageMap.get(character2);
			p2ImageView.setImage(new Image(imagePath2));
			String character3 = ControllerPlayerSelect.selectedPlayers.get(2);
			String imagePath3 = characterImageMap.get(character3);
			p3ImageView.setImage(new Image(imagePath3));
		} else {
			String character1 = ControllerPlayerSelect.selectedPlayers.get(0);
			String imagePath1 = characterImageMap.get(character1);
			p1ImageView.setImage(new Image(imagePath1));
			String character2 = ControllerPlayerSelect.selectedPlayers.get(1);
			String imagePath2 = characterImageMap.get(character2);
			p2ImageView.setImage(new Image(imagePath2));
			String character3 = ControllerPlayerSelect.selectedPlayers.get(2);
			String imagePath3 = characterImageMap.get(character3);
			p3ImageView.setImage(new Image(imagePath3));
			String character4 = ControllerPlayerSelect.selectedPlayers.get(3);
			String imagePath4 = characterImageMap.get(character4);
			p4ImageView.setImage(new Image(imagePath4));
		}
	}

	public void checkPlayersNames(ActionEvent event) throws IOException {
		
	    finishNamesButton.setVisible(true);

	    if (ControllerPlayerSelect.selectedPlayers.size() == 2) {
	        if (p2TextField.getText().isEmpty() || p3TextField.getText().isEmpty() || p2TextField.getText().equals(p3TextField.getText())) {
	        	choosePlayerLabel.setStyle("-fx-background-color: red");
	            choosePlayerLabel.setText("Choose non-empty and unique name!");
	        } else {
	            finishNamesButton.setVisible(false);
	            p2TextField.setDisable(true);
	            p3TextField.setDisable(true);
	            choosePlayerLabel.setStyle("-fx-background-color: null");
	            choosePlayerLabel.setText("Well Done!");
	        }
	    } else if (ControllerPlayerSelect.selectedPlayers.size() == 3) {
	        if (p1TextField.getText().isEmpty() || p2TextField.getText().isEmpty() || p3TextField.getText().isEmpty() ||
	                p1TextField.getText().equals(p2TextField.getText()) ||
	                p1TextField.getText().equals(p3TextField.getText()) ||
	                p2TextField.getText().equals(p3TextField.getText())) {
	        	choosePlayerLabel.setStyle("-fx-background-color: red");
	            choosePlayerLabel.setText("Choose non-empty and unique name!");
	        } else {
	            finishNamesButton.setVisible(false);
	            p1TextField.setDisable(true);
	            p2TextField.setDisable(true);
	            p3TextField.setDisable(true);
	            choosePlayerLabel.setStyle("-fx-background-color: null");
	            choosePlayerLabel.setText("Well Done!");
	        }
	    } else {
	        if (p1TextField.getText().isEmpty() || p2TextField.getText().isEmpty() || p3TextField.getText().isEmpty() || p4TextField.getText().isEmpty() ||
	                p1TextField.getText().equals(p2TextField.getText()) ||
	                p1TextField.getText().equals(p3TextField.getText()) ||
	                p1TextField.getText().equals(p4TextField.getText()) ||
	                p2TextField.getText().equals(p3TextField.getText()) ||
	                p2TextField.getText().equals(p4TextField.getText()) ||
	                p3TextField.getText().equals(p4TextField.getText())) {
	        	choosePlayerLabel.setStyle("-fx-background-color: red");
	            choosePlayerLabel.setText("Choose non-empty and unique name!");
	        } else {
	            finishNamesButton.setVisible(false);
	            p1TextField.setDisable(true);
	            p2TextField.setDisable(true);
	            p3TextField.setDisable(true);
	            p4TextField.setDisable(true);
	            choosePlayerLabel.setStyle("-fx-background-color: null");
	            choosePlayerLabel.setText("Well Done!");
	        }
	    }
	}
	
	public void getPlayerNames() {
		String name1;
		String name2;
		String name3;
		String name4;
		if (ControllerPlayerSelect.selectedPlayers.size() == 2) {
			name1 = p2TextField.getText().strip();
			name2 = p3TextField.getText().strip();
			names.add(name1);
			names.add(name2);
		} else if (ControllerPlayerSelect.selectedPlayers.size() == 3) {
			name1 = p1TextField.getText().strip();
			name2 = p2TextField.getText().strip();
			name3 = p3TextField.getText().strip();
			names.add(name1);
			names.add(name2);
			names.add(name3);
		} else {
			name1 = p1TextField.getText().strip();
			name2 = p2TextField.getText().strip();
			name3 = p3TextField.getText().strip();
			name4 = p4TextField.getText().strip();
			names.add(name1);
			names.add(name2);
			names.add(name3);
			names.add(name4);
		}
	}
}
