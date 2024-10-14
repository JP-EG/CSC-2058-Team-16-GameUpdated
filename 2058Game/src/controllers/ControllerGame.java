package controllers;

import java.awt.MenuBar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import application.Discovery;
import application.Game;
import application.Player;
import application.Resource;
import application.Setback;
import application.Square;
import application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerGame extends Main {
	
	@FXML
	private MenuBar endGameBar;

	@FXML
	private Button diceRollButton, closeButton, assignButton, yesButton, p1AssignTaskButton,p2AssignTaskButton, p3AssignTaskButton,
	endGame, p4AssignTaskButton,assignPlayerCloseButton, objectivesButton, gameWonButton, yesButton1, noButton, endGameButton;

	@FXML
	private Pane square1, square2, square3, square4, square5, square6, square7, square8, square9, square10, square11,
			square12, square13, square14, square15, square16, square17, square18, square19, square20, popUpPane, assignTaskPane,
			onTopOfPopUpUIPane, tasksObjectivePane, turnPane;

	@FXML
	public Label turnPlayer1, turnPlayer2, turnPlayer3, turnPlayer4, budgetLabel, labourHoursLabel, materialUnitsLabel,
			permitTokensLabel, communityTrustPointsLabel, equipmentEfficiencyPointsLabel, technicalExpertiseLabel,
			timeUnitsLabel, communityEngagementLabel, popupLabel, numberRolledLabel, taskLandedOnLabel,
			taskDescriptionLabel, taskChoiceLabel, taskResourceRequiredLost1, taskResourceRequiredLost2,
			taskResourceGained1, taskResourceGained2, assignLabelText, closeLabelText, playerAssignedLabel,
			playerAssignedInfoLabel, playerAssignedInfoLabel2;

	@FXML
	private Rectangle borderLabel;

	@FXML
	private ImageView playerTurnImage, playerImage1_1, playerImage1_2, playerImage1_3, playerImage1_4, playerImage2_1,
			playerImage2_2, playerImage2_3, playerImage2_4, playerImage3_1, playerImage3_2, playerImage3_3,
			playerImage3_4, playerImage4_1, playerImage4_2, playerImage4_3, playerImage4_4, playerImage5_1,
			playerImage5_2, playerImage5_3, playerImage5_4, playerImage6_1, playerImage6_2, playerImage6_3,
			playerImage6_4, playerImage7_1, playerImage7_2, playerImage7_3, playerImage7_4, playerImage8_1,
			playerImage8_2, playerImage8_3, playerImage8_4, playerImage9_1, playerImage9_2, playerImage9_3,
			playerImage9_4, playerImage10_1, playerImage10_2, playerImage10_3, playerImage10_4, playerImage11_1,
			playerImage11_2, playerImage11_3, playerImage11_4, playerImage12_1, playerImage12_2, playerImage12_3,
			playerImage12_4, playerImage13_1, playerImage13_2, playerImage13_3, playerImage13_4, playerImage14_1,
			playerImage14_2, playerImage14_3, playerImage14_4, playerImage15_1, playerImage15_2, playerImage15_3,
			playerImage15_4, playerImage16_1, playerImage16_2, playerImage16_3, playerImage16_4, playerImage17_1,
			playerImage17_2, playerImage17_3, playerImage17_4, playerImage18_1, playerImage18_2, playerImage18_3,
			playerImage18_4, playerImage19_1, playerImage19_2, playerImage19_3, playerImage19_4, playerImage20_1,
			playerImage20_2, playerImage20_3, playerImage20_4, objectiveIcon;

	@FXML
	private ListView<String> tasksListView;

	private List<String> allSelectedPlayers = new ArrayList<>();

	private Map<String, String> characterImageMap = new HashMap<>();

	public static Map<Integer, ArrayList<Player>> squareToPlayer = new HashMap<>();

	private List<Player> players = new ArrayList<>();
	
	private List<Task> tasks = new ArrayList<>();
	
	private ObservableList<String> itemsPlayer1 = FXCollections.observableArrayList();

	private ObservableList<String> itemsPlayer2 = FXCollections.observableArrayList();
	
	private ObservableList<String> itemsPlayer3 = FXCollections.observableArrayList();

	private ObservableList<String> itemsPlayer4 = FXCollections.observableArrayList();

	private static Player currentPlayer;

	public static Game game;

	public Square[] squares;

	public int numberRolled;
	
	private Player assignedPlayer = new Player();
	
	private boolean assignedPlayerChosen;
	
	private Stage stage;
	private Scene scene;
	
	private ControllerPlayerInfoScreen controllerPlayerInfoScreen = new ControllerPlayerInfoScreen();
	
	// private Square initialSq = game.getBoard().getSquare(0);

	public void initialize(List<String> selectedPlayers) {
		initPlayers(selectedPlayers);
	}

	public void initializeGame() {

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

		game = new Game(1, "Game", "The game");
		this.squares = game.getSquares();
		this.tasks = game.getTasks();
	}

	public void initializeDiceRollButtonImage() {
		ImageView rollDiceButtonImage = new ImageView(new Image("/RoleDice/dice.png"));

	    // Set the fit width and fit height to control the image size
	    rollDiceButtonImage.setFitWidth(110);
	    rollDiceButtonImage.setFitHeight(80);

	    diceRollButton.setGraphic(rollDiceButtonImage);
	}

	public void initPlayers(List<String> selectedPlayers) {
		allSelectedPlayers = selectedPlayers;
		ArrayList<Player> playersOnInitialSquare = null;

		for (int i = 0; i < allSelectedPlayers.size(); i++) {
			String character = allSelectedPlayers.get(i);
			ImageView playerImageView = getPlayerImageView(i + 1); // PlayerImage1, PlayerImage2, ...
			String imagePath = characterImageMap.get(character);
			if (i == 0) {
				playerTurnImage.setImage(new Image(imagePath));
			}
			playerImageView.setImage(new Image(imagePath));
			// Set the initial square for each player using squareToPlayer map
			if (i == 0) {
				playersOnInitialSquare = new ArrayList<>(); // Assuming square ID 1 is the initial square
			}
			playersOnInitialSquare.add(players.get(i));
			squareToPlayer.put(0, playersOnInitialSquare);
		}
		updateResourcesPane(currentPlayer);
	}

	public void createPlayers(List<String> selectedPlayers) {
		allSelectedPlayers = selectedPlayers;
		for (int i = 0; i < allSelectedPlayers.size(); i++) {
			Player player = new Player(ControllerPlayerInfoScreen.names.get(i)); // changed so its player 1 instead of 0
			Resource resource = new Resource("Resource" + i);
			player.setResource(resource);
			player.setPosition(0);
			players.add(player); // Add each one to the 'players' list to be accessed by other functions
			player.setCurrentSquare(squares[0]);
		}
		currentPlayer = players.get(0); // Start with player 0
	}
	
	public void createTurnPlayerLabels() {
		if(allSelectedPlayers.size() == 2) {
			turnPlayer1.setText(ControllerPlayerInfoScreen.names.get(0));
			turnPlayer2.setText(ControllerPlayerInfoScreen.names.get(1));
			turnPlayer3.setVisible(false);
			turnPlayer4.setVisible(false);
		} else if (allSelectedPlayers.size() == 3) {
			turnPlayer1.setText(ControllerPlayerInfoScreen.names.get(0));
			turnPlayer2.setText(ControllerPlayerInfoScreen.names.get(1));
			turnPlayer3.setText(ControllerPlayerInfoScreen.names.get(2));
			turnPlayer4.setVisible(false);
		} else {
			turnPlayer1.setText(ControllerPlayerInfoScreen.names.get(0));
			turnPlayer2.setText(ControllerPlayerInfoScreen.names.get(1));
			turnPlayer3.setText(ControllerPlayerInfoScreen.names.get(2));
			turnPlayer4.setText(ControllerPlayerInfoScreen.names.get(3));
		}

	}
	
	public void createAssignPlayerLabels() {
		if(allSelectedPlayers.size() == 2) {
			p1AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(0));
			p2AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(1));
			p3AssignTaskButton.setVisible(false);
			p4AssignTaskButton.setVisible(false);
		} else if (allSelectedPlayers.size() == 3) {
			p1AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(0));
			p2AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(1));
			p3AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(2));
			p4AssignTaskButton.setVisible(false);
		} else {
			p1AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(0));
			p2AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(1));
			p3AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(2));
			p4AssignTaskButton.setText(ControllerPlayerInfoScreen.names.get(3));
		}
	}

	public void setSquareImage() {

		int currentPosition = currentPlayer.getPosition();

		// Calculate the new position based on the dice roll, considering the board size
		// (20 squares)
		int newPosition = currentPosition % 20;

		String character = allSelectedPlayers.get(currentPlayer.getID());
		ImageView squareImageView = getSquareImageView(newPosition + 1, currentPlayer.getID());
		String imagePath = characterImageMap.get(character);
		squareImageView.setImage(new Image(imagePath));

	}

	private void removeSquareImage() {

		int currentPosition = currentPlayer.getPosition();

		// Calculate the new position based on the dice roll, considering the board size
		// (20 squares)
		int newPosition = currentPosition % 20;

		ImageView squareImageView = getSquareImageView(newPosition + 1, currentPlayer.getID());
		squareImageView.setImage(null);

	}

	private ImageView getSquareImageView(int squareNumber, int playerId) {
		switch (squareNumber) {
		case 1:
			return getPlayerImageView(playerId, playerImage1_1, playerImage1_2, playerImage1_3, playerImage1_4);
		case 2:
			return getPlayerImageView(playerId, playerImage2_1, playerImage2_2, playerImage2_3, playerImage2_4);
		case 3:
			return getPlayerImageView(playerId, playerImage3_1, playerImage3_2, playerImage3_3, playerImage3_4);
		case 4:
			return getPlayerImageView(playerId, playerImage4_1, playerImage4_2, playerImage4_3, playerImage4_4);
		case 5:
			return getPlayerImageView(playerId, playerImage5_1, playerImage5_2, playerImage5_3, playerImage5_4);
		case 6:
			return getPlayerImageView(playerId, playerImage6_1, playerImage6_2, playerImage6_3, playerImage6_4);
		case 7:
			return getPlayerImageView(playerId, playerImage7_1, playerImage7_2, playerImage7_3, playerImage7_4);
		case 8:
			return getPlayerImageView(playerId, playerImage8_1, playerImage8_2, playerImage8_3, playerImage8_4);
		case 9:
			return getPlayerImageView(playerId, playerImage9_1, playerImage9_2, playerImage9_3, playerImage9_4);
		case 10:
			return getPlayerImageView(playerId, playerImage10_1, playerImage10_2, playerImage10_3, playerImage10_4);
		case 11:
			return getPlayerImageView(playerId, playerImage11_1, playerImage11_2, playerImage11_3, playerImage11_4);
		case 12:
			return getPlayerImageView(playerId, playerImage12_1, playerImage12_2, playerImage12_3, playerImage12_4);
		case 13:
			return getPlayerImageView(playerId, playerImage13_1, playerImage13_2, playerImage13_3, playerImage13_4);
		case 14:
			return getPlayerImageView(playerId, playerImage14_1, playerImage14_2, playerImage14_3, playerImage14_4);
		case 15:
			return getPlayerImageView(playerId, playerImage15_1, playerImage15_2, playerImage15_3, playerImage15_4);
		case 16:
			return getPlayerImageView(playerId, playerImage16_1, playerImage16_2, playerImage16_3, playerImage16_4);
		case 17:
			return getPlayerImageView(playerId, playerImage17_1, playerImage17_2, playerImage17_3, playerImage17_4);
		case 18:
			return getPlayerImageView(playerId, playerImage18_1, playerImage18_2, playerImage18_3, playerImage18_4);
		case 19:
			return getPlayerImageView(playerId, playerImage19_1, playerImage19_2, playerImage19_3, playerImage19_4);
		case 20:
			return getPlayerImageView(playerId, playerImage20_1, playerImage20_2, playerImage20_3, playerImage20_4);
		default:
			throw new IllegalArgumentException("Invalid square number: " + squareNumber);
		}
	}

	private ImageView getPlayerImageView(int playerId, ImageView... imageViews) {
		switch (playerId) {
		case 0:
			return imageViews[0];
		case 1:
			return imageViews[1];
		case 2:
			return imageViews[2];
		case 3:
			return imageViews[3];
		default:
			throw new IllegalArgumentException("Invalid player ID: " + playerId);
		}
	}

	public ImageView getPlayerImageView(int playerId) {
		switch (playerId) {
		case 1:
			return playerImage1_1;
		case 2:
			return playerImage1_2;
		case 3:
			return playerImage1_3;
		case 4:
			return playerImage1_4;
		default:
			throw new IllegalArgumentException("Invalid player ID: " + playerId);
		}
	}

	public void addToTasksPane(Player player) {

		if (player != null) {
			
			if(player.getID() == 0) {
				itemsPlayer1.add(currentPlayer.getCurrentSquare().getTask().getTaskName());
			} else if (player.getID() == 1) {
				itemsPlayer2.add(currentPlayer.getCurrentSquare().getTask().getTaskName());
			} else if (player.getID() == 2) {
				itemsPlayer3.add(currentPlayer.getCurrentSquare().getTask().getTaskName());
			} else if (player.getID() == 3) {
				itemsPlayer4.add(currentPlayer.getCurrentSquare().getTask().getTaskName());
			}
		} else {
			System.out.println("Current player is null. Cannot update tasks pane.");
		}
	}
	
	public void updateTasksPane(Player player) {

		if (player != null) {
			
			if(player.getID() == 0) {
				tasksListView.setItems(itemsPlayer1);
			} else if (player.getID() == 1) {
				tasksListView.setItems(itemsPlayer2);
			} else if (player.getID() == 2) {
				tasksListView.setItems(itemsPlayer3);
			} else if (player.getID() == 3) {
				tasksListView.setItems(itemsPlayer4);
			}
		} else {
			System.out.println("Current player is null. Cannot update tasks pane.");
		}
	}

	public void updateResourcesPane(Player player) {
		if (player != null) {
			budgetLabel.setText(Integer.toString(player.getResource().getBudget()));
			labourHoursLabel.setText(Integer.toString(player.getResource().getLabourHours()));
			materialUnitsLabel.setText(Integer.toString(player.getResource().getMaterialUnits()));
			permitTokensLabel.setText(Integer.toString(player.getResource().getPermitTokens()));
			communityTrustPointsLabel.setText(Integer.toString(player.getResource().getCommunityTrustPoints()));
			equipmentEfficiencyPointsLabel
					.setText(Integer.toString(player.getResource().getEquipmentEfficiencyPoints()));
			technicalExpertiseLabel.setText(Integer.toString(player.getResource().getTechnicalExpertise()));
			timeUnitsLabel.setText(Integer.toString(player.getResource().getTimeUnits()));
			communityEngagementLabel.setText(Integer.toString(player.getResource().getCommunityEngagement()));
		} else {
			System.out.println("Current player is null. Cannot update tasks pane.");
		}
	}
	
	public boolean areAllTasksComplete(){
        for (Task task : tasks) {
            if (task.getSteps().isComplete() == false) {
                return false;
            }
        }
        
        return true;
    }

	public Player getPlayerById(int playerID) {
		for (Player player : players) {
			if (player.getID() == playerID) {
				return player;
			}
		}
		return null;
	}

	public void setSquareToPlayer(int diceRoll) {

		int currentPosition = currentPlayer.getPosition();

		// Calculate the new position based on the dice roll, considering the board size
		// (20 squares)
		int newPosition = (currentPosition + diceRoll) % 20;

		// Get the list of players on the new position
		ArrayList<Player> playersOnSquare = squareToPlayer.get(newPosition);

		// Check if playersOnSquare is null (not initialized for that square)
		if (playersOnSquare == null) {
			playersOnSquare = new ArrayList<>();
		}

		// Add the player to the list and update the map
		playersOnSquare.add(currentPlayer);
		squareToPlayer.put(newPosition, playersOnSquare);

		ArrayList<Player> playersOnPreviousSquare = squareToPlayer.get(currentPosition);
		if (playersOnPreviousSquare != null && playersOnPreviousSquare.contains(currentPlayer)) {
			playersOnPreviousSquare.remove(currentPlayer);
			squareToPlayer.put(currentPosition, playersOnPreviousSquare);
		}
		
		// Update the player's position
		currentPlayer.setPosition(newPosition);
		currentPlayer.setCurrentSquare(squares[currentPlayer.getPosition()]);
				
		if (currentPlayer.getCurrentSquare().getName() == "Start") {
			taskLandedOnLabel.setVisible(false);
			taskDescriptionLabel.setVisible(false);
			taskResourceRequiredLost1.setVisible(false);
			numberRolledLabel.setText("You landed on Start!");
		} else {
			numberRolledLabel
			.setText(currentPlayer.getName() + ", You have rolled: " + diceRoll);
			taskLandedOnLabel
			.setText("You have landed on: " + currentPlayer.getCurrentSquare().getName());
			popUpUISetup();
			updateResourcesPane(currentPlayer);
			updateTasksPane(currentPlayer); 
			
		}
	}

	public void popUpUISetup() {

		Task task = currentPlayer.getCurrentSquare().getTask();
		Discovery discovery = currentPlayer.getCurrentSquare().getDiscovery();
		Setback setback = currentPlayer.getCurrentSquare().getSetback();

		Discovery discovery1 = discovery;
		Setback setback1 = setback;
		Task task1 = task;
		
 		 if (task != null) {
 			if (task.getSteps().isComplete()) {
 				taskResourceRequiredLost1.setVisible(true);
				taskDescriptionLabel.setText("Description: " + task.getDescription());
				taskResourceRequiredLost1.setText("Task Complete!");
 			} else if(task.getSteps().getQuantity1() == 0) {
 				assignButton.setVisible(true);
				yesButton.setVisible(true);
				taskResourceRequiredLost2.setVisible(true);
				taskChoiceLabel.setVisible(true);
				taskResourceGained2.setVisible(true);
				assignLabelText.setVisible(true);
				closeLabelText.setVisible(true);

 				taskDescriptionLabel.setText("Description: " + task.getDescription());
				
				taskResourceRequiredLost2.setText("The amount of "
						+ task.getSteps().getCost2() + " needed is: "
						+ task.getSteps().getQuantity2());
				taskChoiceLabel.setText("Do you want to contribute your resources to help a friend complete his task? Press Yes");
				taskResourceGained2.setText("Your reward for completing this task is: "
						+ task.getSteps().getRewardQuantity2() + " "
						+ task.getSteps().getReward2());
				assignLabelText.setText("Skip this task and assign it to a different player? Press Assign");
				closeLabelText.setText("Reject this task? Press Close");
				checkPlayerHasEnoughResources();
				updateTasksPane(currentPlayer);
				updateResourcesPane(currentPlayer);
			} else {
				assignButton.setVisible(true);
				yesButton.setVisible(true);
				taskChoiceLabel.setVisible(true);
				assignLabelText.setVisible(true);
				closeLabelText.setVisible(true);
				
				taskDescriptionLabel
				.setText("Description: " + task.getDescription());
				if(task.getSteps().getQuantity1() != 0) {
	 				taskResourceRequiredLost1.setVisible(true);
					taskResourceGained1.setVisible(true);
					taskResourceRequiredLost1.setText("The amount of "
							+ task.getSteps().getCost1() + " needed is: "
							+ task.getSteps().getQuantity1());
					taskChoiceLabel.setText("Take this task? Press Yes");
					taskResourceGained1.setText("Your reward for completing this task is: "
							+ task.getSteps().getRewardQuantity1() + " "
							+ task.getSteps().getReward1());
					assignLabelText.setText("Skip this task and assign it to a different player? Press Assign");
					closeLabelText.setText("Reject this task? Press Close");
				} else if(task.getSteps().getQuantity2() != 0) {
					taskResourceRequiredLost2.setVisible(true);
					taskResourceGained2.setVisible(true);
					taskResourceRequiredLost2.setText("The amount of "
							+ task.getSteps().getCost2() + " needed is: "
							+ task.getSteps().getQuantity2());
					taskChoiceLabel.setText("Take this task? Press Yes");
					taskResourceGained2.setText("Your reward for completing this task is: "
							+ task.getSteps().getRewardQuantity2() + " "
							+ task.getSteps().getReward2());
					assignLabelText.setText("Skip this task and assign it to a different player? Press Assign");
					closeLabelText.setText("Reject this task? Press Close");
				}
				checkPlayerHasEnoughResources();
				updateTasksPane(currentPlayer);
				updateResourcesPane(currentPlayer);
			}
		} else if (discovery != null) {
			int quantityGained = 0;
			taskResourceRequiredLost2.setVisible(true);
			taskDescriptionLabel
					.setText("Title: " + discovery.getTitle());
			taskResourceRequiredLost1
					.setText("Description: " + discovery.getDescription());
			taskResourceRequiredLost2.setText(
					"Resource Gained: " + discovery.getQuantity()
							+ " " + discovery.getResourceGained());
			switch (discovery.getDiscoveryID()) {
			case (1):
				quantityGained = currentPlayer.getResource().getBudget() + discovery.getQuantity();
				if (quantityGained < 0) {
					taskResourceGained1.setVisible(true);
					taskResourceGained1.setText("You don't have enough "
							+ discovery.getResourceGained() + ", the amount needed = " + discovery.getQuantity());
				} else {
					currentPlayer.getResource().setBudget(quantityGained);
				}
				break;
			case (2):
				quantityGained = currentPlayer.getResource().getMaterialUnits() + discovery.getQuantity();
				if (quantityGained < 0) {
					taskResourceGained1.setVisible(true);
					taskResourceGained1.setText("You don't have enough "
							+ discovery.getResourceGained() + ", the amount needed = " + discovery.getQuantity());
				} else {
					currentPlayer.getResource().setMaterialUnits(quantityGained);
				}
				break;
			case (3):
				quantityGained = currentPlayer.getResource().getCommunityTrustPoints() + discovery.getQuantity();
				if (quantityGained < 0) {
					taskResourceGained1.setVisible(true);
					taskResourceGained1.setText("You don't have enough "
							+ discovery.getResourceGained() + ", the amount needed = " + discovery.getQuantity());
				} else {
					currentPlayer.getResource().setCommunityTrustPoints(quantityGained);
				}
				break;
			case (4):
				quantityGained = currentPlayer.getResource().getBudget() + discovery.getQuantity();
				if (quantityGained < 0) {
					taskResourceGained1.setVisible(true);
					taskResourceGained1.setText("You don't have enough "
							+ discovery.getResourceGained() + ", the amount needed = " + discovery.getQuantity());
				} else {
					currentPlayer.getResource().setBudget(quantityGained);
				}
				break;
			}
		} else if (setback != null) {
			int requiredAmount1 = 0;
			int quantityLost = 0;
			taskResourceRequiredLost1.setVisible(true);
			taskDescriptionLabel
					.setText("Description: " + setback.getDescription());
			taskResourceRequiredLost1
					.setText("Resource Lost: " + setback.getQuantity()
							+ " " + setback.getResourceLost());

			switch (setback.getSetbackID()) {
			case (1):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1) {
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setEquipmentEffiencyPoints(0);
				} else {
					quantityLost = currentPlayer.getResource().getEquipmentEfficiencyPoints() - setback.getQuantity();
					currentPlayer.getResource().setEquipmentEffiencyPoints(quantityLost);
				}
				break;
			case (2):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {					
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setBudget(0);
				} else {
					quantityLost = currentPlayer.getResource().getBudget() - setback.getQuantity();
					currentPlayer.getResource().setBudget(quantityLost);
				}
				break;
			case (3):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount1) {					
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setTimeUnits(0);
				} else {
					quantityLost = currentPlayer.getResource().getTimeUnits() - setback.getQuantity();
					currentPlayer.getResource().setTimeUnits(quantityLost);
				}
				break;
			case (4):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getTechnicalExpertise() < requiredAmount1) {					
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setTechnicalExpertise(0);

				} else {
					quantityLost = currentPlayer.getResource().getTechnicalExpertise() - setback.getQuantity();
					currentPlayer.getResource().setTechnicalExpertise(quantityLost);
				}
				break;
			case (5):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getCommunityEngagement() < requiredAmount1) {					
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setCommunityEngagement(0);
				} else {
					quantityLost = currentPlayer.getResource().getCommunityEngagement() - setback.getQuantity();
					currentPlayer.getResource().setCommunityEngagement(quantityLost);
				}
				break;
			case (6):
				requiredAmount1 = setback.getQuantity();
				if (currentPlayer.getResource().getMaterialUnits() < requiredAmount1) {					
					taskResourceRequiredLost2.setVisible(true);
					taskResourceRequiredLost2.setText("You don't have enough " + setback.getResourceLost()
							+ ", the amount needed = " + setback.getQuantity() + "\nDefault Value applied of 0");
					currentPlayer.getResource().setMaterialUnits(0);
				} else {
					quantityLost = currentPlayer.getResource().getMaterialUnits() - setback.getQuantity();
					currentPlayer.getResource().setMaterialUnits(quantityLost);
				}
				break;
			}
			
		}
		taskLandedOnLabel.setVisible(true);
		taskDescriptionLabel.setVisible(true);
	}

	public void setLabelsInvisible() {
		assignButton.setVisible(false);
		yesButton.setVisible(false);
		taskResourceRequiredLost1.setVisible(false);
		taskResourceRequiredLost2.setVisible(false);
		taskChoiceLabel.setVisible(false);
		taskResourceGained1.setVisible(false);
		taskResourceGained2.setVisible(false);
		assignLabelText.setVisible(false);
		closeLabelText.setVisible(false);
		playerAssignedLabel.setVisible(false);
		playerAssignedInfoLabel.setVisible(false);
		playerAssignedInfoLabel2.setVisible(false);
		onTopOfPopUpUIPane.setVisible(false);
		noButton.setVisible(false);
		yesButton1.setVisible(false);
	}

	public Player getCurrentPlayer() {
		return currentPlayer; // used so ControllerPopupUI can access the current player
	}

	public void switchToNextPlayer() {

		if (currentPlayer.getID() < players.size() - 1) {
			currentPlayer = players.get(currentPlayer.getID() + 1);
		} else {
			currentPlayer = players.get(0);
		}

		switch (currentPlayer.getID()) {
		case (0):
			turnPlayer1.setStyle("-fx-background-color: green");
			turnPlayer2.setStyle("-fx-background-color: #f03939");
			turnPlayer3.setStyle("-fx-background-color: #f03939");
			turnPlayer4.setStyle("-fx-background-color: #f03939");
			break;
		case (1):
			turnPlayer1.setStyle("-fx-background-color: #f03939");
			turnPlayer2.setStyle("-fx-background-color: green");
			turnPlayer3.setStyle("-fx-background-color: #f03939");
			turnPlayer4.setStyle("-fx-background-color: #f03939");

			break;
		case (2):
			turnPlayer1.setStyle("-fx-background-color: #f03939");
			turnPlayer2.setStyle("-fx-background-color: #f03939");
			turnPlayer3.setStyle("-fx-background-color: green");
			turnPlayer4.setStyle("-fx-background-color: #f03939");
			break;
		case (3):
			turnPlayer1.setStyle("-fx-background-color: #f03939");
			turnPlayer2.setStyle("-fx-background-color: #f03939");
			turnPlayer3.setStyle("-fx-background-color: #f03939");
			turnPlayer4.setStyle("-fx-background-color: green");
			break;
		}
		String character = allSelectedPlayers.get(currentPlayer.getID());
		String imagePath = characterImageMap.get(character);
		playerTurnImage.setImage(new Image(imagePath));
		// Example: Update the playerTurnImage in the UI
	}


	public int rollDice() {
		Random random = new Random();
		int numberRolled = random.nextInt(6) + 1;
		return numberRolled;
	}

	public void handleCloseButton(ActionEvent event) {
		popUpPane.setVisible(false);
		onTopOfPopUpUIPane.setVisible(false);
		switchToNextPlayer();
		updateResourcesPane(currentPlayer);
		updateTasksPane(currentPlayer);
		setLabelsInvisible();
	}
	
	public void checkPlayerHasEnoughResources() {
		int requiredAmount1 = 0;
		int requiredAmount2 = 0;
		requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
		requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();

		switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
		case (1):
			if (requiredAmount1 != 0) {
				taskResourceGained2.setVisible(false);
				if (currentPlayer.getResource().getLabourHours() < requiredAmount1) {
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount2) {
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}
			break;
		case (2):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}
			break;
		case (3):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getPermitTokens() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getBudget() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}
			break;
		case (4):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getMaterialUnits() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}
			break;
		case (5):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (6):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getMaterialUnits() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getLabourHours() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (7):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (8):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (9):
 			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (10):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getLabourHours() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (11):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getBudget() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (12):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getCommunityTrustPoints() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;
		case (13):
			if (requiredAmount1 != 0) {
				if (currentPlayer.getResource().getTimeUnits() < requiredAmount1) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
					yesButton.setDisable(true);
				}
			} else {
				if (currentPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
					taskResourceGained2.setVisible(false);
					taskResourceGained1.setText(
							"You don't have enough " + currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
									+ ", the amount needed = "
									+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
					yesButton.setDisable(true);
				}
			}

			break;

		}
	}

	public void yesButton() {

		int quantity1ToRemove = 0;
		int quantity2ToRemove = 0;
		int rewardQuantity1 = 0;
		int rewardQuantity2 = 0;

		switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
		case (1):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setLabourHours(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setTechnicalExpertise(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(0, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square2.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(0, currentPlayer.getCurrentSquare().getTask());
				square2.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (2):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getPermitTokens()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setPermitTokens(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(1, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square3.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(1, currentPlayer.getCurrentSquare().getTask());
				square3.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (3):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getPermitTokens()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setPermitTokens(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setBudget(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(2, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square5.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setBudget(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(2, currentPlayer.getCurrentSquare().getTask());
				square5.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (4):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getEquipmentEfficiencyPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setEquipmentEffiencyPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(3, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square6.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getMaterialUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setMaterialUnits(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(3, currentPlayer.getCurrentSquare().getTask());
				square6.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (5):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setBudget(rewardQuantity1);
				;
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(4, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square8.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getEquipmentEfficiencyPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setEquipmentEffiencyPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(4, currentPlayer.getCurrentSquare().getTask());
				square8.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (6):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getMaterialUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setMaterialUnits(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setTechnicalExpertise(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(5, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square9.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setLabourHours(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setLabourHours(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(5, currentPlayer.getCurrentSquare().getTask());
				square9.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (7):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getEquipmentEfficiencyPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setEquipmentEffiencyPoints(quantity1ToRemove);
				;
				rewardQuantity1 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(6, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square11.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setTechnicalExpertise(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(6, currentPlayer.getCurrentSquare().getTask());
				square11.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (8):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getEquipmentEfficiencyPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setEquipmentEffiencyPoints(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setLabourHours(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(7, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square12.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getTimeUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setTimeUnits(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(7, currentPlayer.getCurrentSquare().getTask());
				square12.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (9):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getPermitTokens()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setPermitTokens(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(8, currentPlayer.getCurrentSquare().getTask());
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				square14.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setLabourHours(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(8, currentPlayer.getCurrentSquare().getTask());
				square14.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (10):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setLabourHours(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setBudget(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(9, currentPlayer.getCurrentSquare().getTask());
				square15.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(9, currentPlayer.getCurrentSquare().getTask());
				square15.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (11):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getMaterialUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setMaterialUnits(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(10, currentPlayer.getCurrentSquare().getTask());
				square17.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(10, currentPlayer.getCurrentSquare().getTask());
				square17.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (12):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setTimeUnits(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(11, currentPlayer.getCurrentSquare().getTask());
				square18.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getCommunityTrustPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setCommunityTrustPoints(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getCommunityEngagement()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setCommunityEngagement(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(11, currentPlayer.getCurrentSquare().getTask());
				square18.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (13):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = currentPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				currentPlayer.getResource().setTimeUnits(quantity1ToRemove);
				rewardQuantity1 = currentPlayer.getResource().getTimeUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				currentPlayer.getResource().setTimeUnits(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(12, currentPlayer.getCurrentSquare().getTask());
				square20.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				currentPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
				addToTasksPane(currentPlayer);
			} else if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2() != 0){
				quantity2ToRemove = currentPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				currentPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = currentPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				currentPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(12, currentPlayer.getCurrentSquare().getTask());
				square20.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		}
		yesButton.setDisable(true);
		popUpPane.setVisible(false);
		switchToNextPlayer();
		updateTasksPane(currentPlayer);
		updateResourcesPane(currentPlayer);
	}

	public void yesButtonAssignPlayer() {

		int quantity1ToRemove = 0;
		int quantity2ToRemove = 0;
		int rewardQuantity1 = 0;
		int rewardQuantity2 = 0;

		switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
		case (1):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setLabourHours(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setTechnicalExpertise(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(0, currentPlayer.getCurrentSquare().getTask());
				square2.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());
			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(0, currentPlayer.getCurrentSquare().getTask());
				square2.setStyle("-fx-background-color: green; -fx-border-color: black;");
			}
			break;
		case (2):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getPermitTokens()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setPermitTokens(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(1, currentPlayer.getCurrentSquare().getTask());
				square3.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(1, currentPlayer.getCurrentSquare().getTask());
				square3.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (3):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getPermitTokens()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setPermitTokens(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setBudget(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(2, currentPlayer.getCurrentSquare().getTask());
				square5.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setBudget(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(2, currentPlayer.getCurrentSquare().getTask());
				square5.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (4):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getEquipmentEfficiencyPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setEquipmentEffiencyPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(3, currentPlayer.getCurrentSquare().getTask());
				square6.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getMaterialUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setMaterialUnits(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(3, currentPlayer.getCurrentSquare().getTask());
				square6.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (5):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setBudget(rewardQuantity1);
				;
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(4, currentPlayer.getCurrentSquare().getTask());
				square8.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getEquipmentEfficiencyPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setEquipmentEffiencyPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(4, currentPlayer.getCurrentSquare().getTask());
				square8.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (6):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getMaterialUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setMaterialUnits(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setTechnicalExpertise(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(5, currentPlayer.getCurrentSquare().getTask());
				square9.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setLabourHours(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setLabourHours(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(5, currentPlayer.getCurrentSquare().getTask());
				square9.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (7):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getEquipmentEfficiencyPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setEquipmentEffiencyPoints(quantity1ToRemove);
				;
				rewardQuantity1 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(6, currentPlayer.getCurrentSquare().getTask());
				square11.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getTechnicalExpertise()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setTechnicalExpertise(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(6, currentPlayer.getCurrentSquare().getTask());
				square11.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (8):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getEquipmentEfficiencyPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setEquipmentEffiencyPoints(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setLabourHours(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(7, currentPlayer.getCurrentSquare().getTask());
				square12.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTimeUnits(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getTimeUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setTimeUnits(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(7, currentPlayer.getCurrentSquare().getTask());
				square12.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (9):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getPermitTokens()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setPermitTokens(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(8, currentPlayer.getCurrentSquare().getTask());
				square14.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getTechnicalExpertise()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setTechnicalExpertise(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getLabourHours()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setLabourHours(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(8, currentPlayer.getCurrentSquare().getTask());
				square14.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (10):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getLabourHours()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setLabourHours(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setBudget(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(9, currentPlayer.getCurrentSquare().getTask());
				square15.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(9, currentPlayer.getCurrentSquare().getTask());
				square15.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (11):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getBudget()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setBudget(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getMaterialUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setMaterialUnits(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(10, currentPlayer.getCurrentSquare().getTask());
				square17.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getBudget()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setBudget(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(10, currentPlayer.getCurrentSquare().getTask());
				square17.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (12):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setTimeUnits(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(11, currentPlayer.getCurrentSquare().getTask());
				square18.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getCommunityTrustPoints()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setCommunityTrustPoints(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getCommunityEngagement()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setCommunityEngagement(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(11, currentPlayer.getCurrentSquare().getTask());
				square18.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		case (13):
			if (currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1() != 0) {
				quantity1ToRemove = assignedPlayer.getResource().getTimeUnits()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
				assignedPlayer.getResource().setTimeUnits(quantity1ToRemove);
				rewardQuantity1 = assignedPlayer.getResource().getTimeUnits()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity1();
				assignedPlayer.getResource().setTimeUnits(rewardQuantity1);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity1(0);
				tasks.set(12, currentPlayer.getCurrentSquare().getTask());
				square20.setStyle("-fx-background-color: orange; -fx-border-color: black;");
				assignedPlayer.addTask(currentPlayer.getCurrentSquare().getTask().getTaskName());

			} else {
				quantity2ToRemove = assignedPlayer.getResource().getCommunityEngagement()
						- currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
				assignedPlayer.getResource().setCommunityEngagement(quantity2ToRemove);
				rewardQuantity2 = assignedPlayer.getResource().getCommunityTrustPoints()
						+ currentPlayer.getCurrentSquare().getTask().getSteps().getRewardQuantity2();
				assignedPlayer.getResource().setCommunityTrustPoints(rewardQuantity2);
				currentPlayer.getCurrentSquare().getTask().getSteps().setQuantity2(0);
				tasks.set(12, currentPlayer.getCurrentSquare().getTask());
				square20.setStyle("-fx-background-color: green; -fx-border-color: black;");

			}
			break;
		}
		addToTasksPane(assignedPlayer);
		updateResourcesPane(assignedPlayer);
		yesButton.setDisable(true);
		popUpPane.setVisible(false);
		switchToNextPlayer();
		updateTasksPane(currentPlayer);
		updateResourcesPane(currentPlayer);
	}
	
	public void checkAssignedPlayerHasEnoughResources(Player assignedTaskPlayer) {
		int requiredAmount1 = 0;
		int requiredAmount2 = 0;

		switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
		case (1):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (2):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (3):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getPermitTokens() < requiredAmount1
					|| assignedTaskPlayer.getResource().getBudget() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getPermitTokens() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getBudget() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (4):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1
					|| assignedTaskPlayer.getResource().getMaterialUnits() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getMaterialUnits() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (5):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (6):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getMaterialUnits() < requiredAmount1
					|| assignedTaskPlayer.getResource().getLabourHours() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getMaterialUnits() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (7):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (8):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (9):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTechnicalExpertise() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (10):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1
					|| assignedTaskPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (11):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1
					|| assignedTaskPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getBudget() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getCommunityEngagement() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (12):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount1
					|| assignedTaskPlayer.getResource().getCommunityTrustPoints() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getCommunityTrustPoints() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
				playerAssignedInfoLabel2.setVisible(true);
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		case (13):
			requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
			requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
			playerAssignedInfoLabel2.setVisible(true);
			if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1
					|| assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
				playerAssignedInfoLabel.setVisible(true);
				if (assignedTaskPlayer.getResource().getLabourHours() < requiredAmount1) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost1()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1());
				} else if (assignedTaskPlayer.getResource().getTimeUnits() < requiredAmount2) {
					playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " doesn't have enough "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getCost2()
							+ ", the amount needed = "
							+ currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2());
				}
		        playerAssignedInfoLabel2.setText("Press Close");
			} else {
		        noButton.setVisible(true);
		        yesButton1.setVisible(true);
		        playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
		        playerAssignedInfoLabel2.setText("Dont want this task? Press No or Close");
			}
			break;
		}
		
	}
	
	public void assignButton(ActionEvent event) {
	    onTopOfPopUpUIPane.setVisible(true);

	    int numSelectedPlayers = allSelectedPlayers.size();
	    List<Button> assignButtons = Arrays.asList(p1AssignTaskButton, p2AssignTaskButton, p3AssignTaskButton, p4AssignTaskButton);

	    for (int i = 0; i < assignButtons.size(); i++) {
	        Button button = assignButtons.get(i);

	        if (i < numSelectedPlayers) {
	            int assignedPlayerIndex = (currentPlayer.getID() + i) % numSelectedPlayers;
	            
	            // Exclude the current player's button
	            if (assignedPlayerIndex != currentPlayer.getID()) {
	                button.setVisible(true);
	                button.setText(ControllerPlayerInfoScreen.names.get(assignedPlayerIndex));
	            } else {
	                button.setVisible(false);
	            }
	        } else {
	            button.setVisible(false);
	        }
	    }
	}
	
	
	public void noButton() {
		popUpPane.setVisible(false);
		onTopOfPopUpUIPane.setVisible(false);
		switchToNextPlayer();
		updateResourcesPane(currentPlayer);
		updateTasksPane(currentPlayer);
		setLabelsInvisible();
	}
	
	public void assignPlayerToTaskButton(ActionEvent event) {
	    Button clickedButton = (Button) event.getSource();
	    int assignedPlayerIndex = Arrays.asList(p1AssignTaskButton, p2AssignTaskButton, p3AssignTaskButton, p4AssignTaskButton)
	            .indexOf(clickedButton);

	    int playerNum = 0;
	    if (assignedPlayerIndex >= 0 && assignedPlayerIndex < allSelectedPlayers.size()) {
	    	for(int i = 0; i < ControllerPlayerInfoScreen.names.size(); i++){
	    		if (clickedButton.getText() == ControllerPlayerInfoScreen.names.get(i)) {
	    			playerNum = i;
	    		}
	    	}
	    	
	    	assignedPlayer = players.get(playerNum);
	    	assignedPlayerChosen = true;
	    	playerAssignedLabel.setVisible(true);
	        playerAssignedInfoLabel.setVisible(true);
	        playerAssignedLabel.setText("You have chosen to give the task to: " + assignedPlayer.getName());
	        checkAssignedPlayerHasEnoughResources(assignedPlayer);	    
	    }
	    p1AssignTaskButton.setDisable(true);
	    p2AssignTaskButton.setDisable(true);
	    p3AssignTaskButton.setDisable(true);
	    p4AssignTaskButton.setDisable(true);
	   
	}
	
	public void showObjectives() {
		tasksObjectivePane.setVisible(true);
		
	}
	
	public void dontShowObjectives() {
		tasksObjectivePane.setVisible(false);
	}
	
	public void endGame() {
		System.exit(0);
	}
	
	public void switchToPopUp() {
		if (!areAllTasksComplete()) {
			yesButton.setDisable(false);
			popUpPane.setVisible(true);
			 p1AssignTaskButton.setDisable(false);
			 p2AssignTaskButton.setDisable(false);
			 p3AssignTaskButton.setDisable(false);
		     p4AssignTaskButton.setDisable(false);
			setLabelsInvisible();
			// Get the updated number rolled after the user closes the popup
			int numberRolled = rollDice();
			int temp = numberRolled;
			int newPosition = temp += currentPlayer.getPosition();
			removeSquareImage();
			setSquareToPlayer(numberRolled);
			setSquareImage();
			updateResourcesPane(currentPlayer);
			updateTasksPane(currentPlayer);
			
		} else {
			diceRollButton.setVisible(false);
			diceRollButton.setDisable(true);
			gameWonButton.setVisible(true);
		}
	}
	
	
	public void switchToGameWon(ActionEvent event) throws IOException {
	        // Pass selectedPlayers to the ControllerGame
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmlFiles.GameWon.fxml"));
	        Parent root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        controllerPlayerInfoScreen.centerStageOnScreen();
	        stage.show();
	    }
}


