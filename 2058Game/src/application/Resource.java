package application;

public class Resource {
	
	private static int nextID = 0;
	private int resourceID;
	private String name;
	private String description;
	private int budget;
	private int labourHours;
	private int materailsUnits;
	private int permitTokens;
	private int communityTrustPoints;
	private int equipmentEfficencyPoints;
	private int technicalExpertise;
	private int timeUnits;
	private int communityEngagement;


	public Resource(String name) {
		
		this.resourceID = nextID++;
		this.name = name;
		//this.description = description; 
		
		this.budget = 10000;
		this.labourHours = 50;
		this.materailsUnits = 8;
		this.permitTokens = 2;
		this.communityTrustPoints = 10;
		this.equipmentEfficencyPoints = 4;
		this.technicalExpertise = 4;
		this.timeUnits = 12;
		this.communityEngagement = 2;
	}

	
	public Resource () {
		
	}
	
	public int getResourceID() {
		return this.resourceID;
	}
	
	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public int getBudget() {
		return this.budget;

	}

	public int getLabourHours() {
		return this.labourHours;

	}

	public int getMaterialUnits() {
		return this.materailsUnits;

	}

	public int getPermitTokens() {
		return this.permitTokens;

	}

	public int getCommunityTrustPoints() {
		return this.communityTrustPoints;

	}

	public int getEquipmentEfficiencyPoints() {
		return this.equipmentEfficencyPoints;

	}

	public int getTechnicalExpertise() {
		return this.technicalExpertise;

	}

	public int getTimeUnits() {
		return this.timeUnits;

	}

	public int getCommunityEngagement() {
		return this.communityEngagement;

	}

	public void setDescription(String s) {
		this.description = s;
	}

	public void setBudget(int i) {
		this.budget = i;
	}

	public void setLabourHours(int i) {
		this.labourHours = i;
	}

	public void setMaterialUnits(int i) {
		this.materailsUnits = i;
	}

	public void setPermitTokens(int i) {
		this.permitTokens = i;
	}

	public void setCommunityTrustPoints(int i) {
		this.communityTrustPoints = i;
	}

	public void setEquipmentEffiencyPoints(int i) {
		this.equipmentEfficencyPoints = i;
	}

	public void setTechnicalExpertise(int i) {
		this.technicalExpertise = i;
	}

	public void setTimeUnits(int i) {
		this.timeUnits = i;
	}

	public void setCommunityEngagement(int i) {
		this.communityEngagement = i;
	}

}
