package controllers.ControllerHelpers;

import application.Player;
import application.Task;
import javafx.scene.layout.Pane;

import java.util.List;

public class YesButtonAssignPlayer {

    public Player currentPlayer;
    public Player assignedPlayer;
    public List<Task> tasks;
    public Pane square2, square3, square5, square6, square8, square9, square11, square12, square14, square15, square17, square18, square20;


    public YesButtonAssignPlayer(Player curenPlayer, Player assignPlayer, List<Task> task, Pane ...panes){
        this.currentPlayer = curenPlayer;
        this.assignedPlayer = assignPlayer;
        this.tasks = tasks;

        if (panes.length > 0) this.square2 = panes.length > 0 ? panes[0] : null;
        if (panes.length > 1) this.square3 = panes.length > 1 ? panes[1] : null;
        if (panes.length > 2) this.square5 = panes.length > 2 ? panes[2] : null;
        if (panes.length > 3) this.square6 = panes.length > 3 ? panes[3] : null;
        if (panes.length > 4) this.square8 = panes.length > 4 ? panes[4] : null;
        if (panes.length > 5) this.square9 = panes.length > 5 ? panes[5] : null;
        if (panes.length > 6) this.square11 = panes.length > 6 ? panes[6] : null;
        if (panes.length > 7) this.square12 = panes.length > 7 ? panes[7] : null;
        if (panes.length > 8) this.square14 = panes.length > 8 ? panes[8] : null;
        if (panes.length > 9) this.square15 = panes.length > 9 ? panes[9] : null;
        if (panes.length > 10) this.square17 = panes.length > 10 ? panes[10] : null;
        if (panes.length > 11) this.square18 = panes.length > 11 ? panes[11] : null;
        if (panes.length > 12) this.square20 = panes.length > 12 ? panes[12] : null;
        
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
	}
    
}
