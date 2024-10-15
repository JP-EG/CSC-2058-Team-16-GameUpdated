package controllers.ControllerHelpers;

import application.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CurrentPlayerResourceChecker {

    public Player currentPlayer;
    public Label taskResourceGained1;
    public Label taskResourceGained2;
    public Button yesButton;

    public CurrentPlayerResourceChecker(Player currentPlayer, Label taskResourceGained1, Label taskResourceGained2, Button yesButton) {
        this.currentPlayer = currentPlayer;
        this.taskResourceGained1 = taskResourceGained1;
        this.taskResourceGained2 = taskResourceGained2;
        this.yesButton = yesButton;
    }

    public void checkPlayerHasEnoughResources() {
        int requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
        int requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();

        boolean hasEnoughResource1 = checkResource1(requiredAmount1);
        boolean hasEnoughResource2 = checkResource2(requiredAmount2);

        if (!hasEnoughResource1) {
            disableYesButtonWithMessage(taskResourceGained1, currentPlayer.getCurrentSquare().getTask().getSteps().getCost1(), requiredAmount1);
        } else if (!hasEnoughResource2) {
            disableYesButtonWithMessage(taskResourceGained1, currentPlayer.getCurrentSquare().getTask().getSteps().getCost2(), requiredAmount2);
        }
    }

    private boolean checkResource1(int requiredAmount) {
        if (requiredAmount == 0) return true;

        switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
            case 1: return currentPlayer.getResource().getLabourHours() >= requiredAmount;
            case 2: case 4: case 5: case 9: case 11: return currentPlayer.getResource().getBudget() >= requiredAmount;
            case 3: return currentPlayer.getResource().getPermitTokens() >= requiredAmount;
            case 6: return currentPlayer.getResource().getMaterialUnits() >= requiredAmount;
            case 7: case 8: return currentPlayer.getResource().getEquipmentEfficiencyPoints() >= requiredAmount;
            case 10: case 13: return currentPlayer.getResource().getLabourHours() >= requiredAmount;
            case 12: return currentPlayer.getResource().getTimeUnits() >= requiredAmount;
            default: return false;
        }
    }

    private boolean checkResource2(int requiredAmount) {
        if (requiredAmount == 0) return true;

        switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
            case 1: case 8: case 13: return currentPlayer.getResource().getTimeUnits() >= requiredAmount;
            case 2: case 3: case 4: case 5: case 9: case 11: return currentPlayer.getResource().getTechnicalExpertise() >= requiredAmount;
            case 6: return currentPlayer.getResource().getLabourHours() >= requiredAmount;
            case 7: return currentPlayer.getResource().getTechnicalExpertise() >= requiredAmount;
            case 10: return currentPlayer.getResource().getCommunityEngagement() >= requiredAmount;
            case 12: return currentPlayer.getResource().getCommunityTrustPoints() >= requiredAmount;
            default: return false;
        }
    }

    private void disableYesButtonWithMessage(Label label, String resourceName, int requiredAmount) {
        taskResourceGained2.setVisible(false);
        label.setText("You don't have enough " + resourceName + ", the amount needed = " + requiredAmount);
        yesButton.setDisable(true);
    }
}
