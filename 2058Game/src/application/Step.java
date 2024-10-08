package application;

import java.util.HashMap;
import java.util.Map;

public class Step {
	private int stepID = 0;
	private String cost1;
	private String cost2;
	private String reward1;
	private String reward2;
	private int quantity1;
	private int quantity2;
	private int rewardQuantity1;
	private int rewardQuantity2;

	
	public Step (String cost1, String cost2, int quantity1, int quantity2, String reward1, String reward2, int rewardQuantity1, int rewardQuantity2) {
		this.stepID ++;
		this.cost1 =cost1;
		this.cost2 = cost2;
		this.reward1 = reward1;
		this.reward2 = reward2;
		this.quantity1 = quantity1;
		this.quantity2 = quantity2;
		this.rewardQuantity1 = rewardQuantity1;
		this.rewardQuantity2 = rewardQuantity2;
	}
	
	public boolean isComplete() {
		if(quantity1 == 0 && quantity2 == 0) {
			return true;
		}
		return false;
	}
	
	public boolean hasResource() {
		return false;
	}
	public String getCost1() {
		return this.cost1;
	}
	
	public String getCost2() {
		return this.cost2;
	}
	
	public String getReward1() {
		return this.reward1;
	}
	
	public String getReward2() {
		return this.reward2;
	}
	
	public int getQuantity1() {
		return this.quantity1;
	}
	
	public int getQuantity2() {
		return this.quantity2;
	}
	
	public int getRewardQuantity1() {
		return this.rewardQuantity1;
	}
	
	public int getRewardQuantity2() {
		return this.rewardQuantity2;
	}
	
	public void setQuantity1(int num) {
		this.quantity1 = num;
	}
	
	public void setQuantity2(int num) {
		this.quantity2 = num;
	}
	
	
}