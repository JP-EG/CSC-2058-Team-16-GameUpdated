package controllers.ControllerHelpers;

import application.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlayerResourceChecker {

    public Player currentPlayer;
    public Label taskResourceGained1;
    public Label taskResourceGained2;
    public Button yesButton;

    public PlayerResourceChecker(
        Player currentPlayer,
        Label taskResourceGained1,
        Label taskResourceGained2,
        Button yesButton)
    {
        this.currentPlayer = currentPlayer;
        this.taskResourceGained1 = taskResourceGained1;
        this.taskResourceGained2 = taskResourceGained2;
        this.yesButton = yesButton;
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
    
}
