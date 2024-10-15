package controllers.ControllerHelpers;

import application.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AssignedPlayerResourceChecker {

    public Player currentPlayer;
    public Label playerAssignedInfoLabel;
    public Label playerAssignedInfoLabel2;
    public Button yesButton1;
    public Button noButton;

    public AssignedPlayerResourceChecker(Player currentPlayer, Label playerAssignedInfoLabel, Label playerAssignedInfoLabel2, Button yesButton1, Button noButton) {
        this.currentPlayer = currentPlayer;
        this.playerAssignedInfoLabel = playerAssignedInfoLabel;
        this.playerAssignedInfoLabel2 = playerAssignedInfoLabel2;
        this.yesButton1 = yesButton1;
        this.noButton = noButton;
    }

    public void checkAssignedPlayerHasEnoughResources(Player assignedTaskPlayer) {
        int requiredAmount1 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity1();
        int requiredAmount2 = currentPlayer.getCurrentSquare().getTask().getSteps().getQuantity2();
        String cost1 = currentPlayer.getCurrentSquare().getTask().getSteps().getCost1();
        String cost2 = currentPlayer.getCurrentSquare().getTask().getSteps().getCost2();
        
        playerAssignedInfoLabel2.setVisible(true);

        boolean hasEnoughResources = false;
        switch (currentPlayer.getCurrentSquare().getTask().getTaskID()) {
            case 1:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getLabourHours(), assignedTaskPlayer.getResource().getTimeUnits(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 2:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getBudget(), assignedTaskPlayer.getResource().getTimeUnits(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 3:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getPermitTokens(), assignedTaskPlayer.getResource().getBudget(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 4:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getBudget(), assignedTaskPlayer.getResource().getMaterialUnits(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 5:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getBudget(), assignedTaskPlayer.getResource().getTechnicalExpertise(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 6:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getMaterialUnits(), assignedTaskPlayer.getResource().getLabourHours(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 7:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints(), assignedTaskPlayer.getResource().getTechnicalExpertise(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 8:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getEquipmentEfficiencyPoints(), assignedTaskPlayer.getResource().getTimeUnits(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 9:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getBudget(), assignedTaskPlayer.getResource().getTechnicalExpertise(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 10:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getLabourHours(), assignedTaskPlayer.getResource().getCommunityEngagement(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 11:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getBudget(), assignedTaskPlayer.getResource().getCommunityEngagement(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
            case 12:
                hasEnoughResources = checkResources(assignedTaskPlayer.getResource().getTimeUnits(), assignedTaskPlayer.getResource().getCommunityTrustPoints(), requiredAmount1, requiredAmount2, cost1, cost2, assignedTaskPlayer.getName());
                break;
        }

        if (hasEnoughResources) {
            playerAssignedInfoLabel.setText(assignedTaskPlayer.getName() + " do you want to take this task and use your resources to help a friend? Press Yes");
            playerAssignedInfoLabel2.setText("Don't want this task? Press No or Close");
            yesButton1.setVisible(true);
            noButton.setVisible(true);
        } else {
            playerAssignedInfoLabel2.setText("Press Close");
        }
    }

    private boolean checkResources(int resource1, int resource2, int required1, int required2, String cost1, String cost2, String playerName) {
        if (resource1 < required1) {
            playerAssignedInfoLabel.setText(playerName + " doesn't have enough " + cost1 + ", amount needed = " + required1);
            return false;
        } else if (resource2 < required2) {
            playerAssignedInfoLabel.setText(playerName + " doesn't have enough " + cost2 + ", amount needed = " + required2);
            return false;
        }
        return true;
    }
}
