package application;

public class Setback {

	private int setbackID;
	private String title;
	private String description;
	private String consequence;
	private String resourceLost;
	private int quantity;

	public Setback(int setbackID, String title, String description, String consequence, String resourceLost, int quantity) {
		this.setbackID = setbackID;
		this.title = title;
		this.description = description;
		this.consequence = consequence;
		this.resourceLost = resourceLost;
		this.quantity = quantity;

	}

	public int getSetbackID() {
		return setbackID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getConsequence() {
		return consequence;
	}

	public String getResourceLost() {
		return resourceLost;
	}

	public int getQuantity() {
		return quantity;
	}

}
