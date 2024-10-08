package application;

public class Discovery{

	private int discoveryID;
	private String title;
	private String description;
	private String resourceGained;
	private int quantity;

	public Discovery(int discoveryID, String title, String description, String resourceGained, int quantity) {
		this.title = title;
		this.discoveryID = discoveryID;
		this.description = description;
		this.resourceGained = resourceGained;
		this.quantity = quantity;

	}
	
	public int getDiscoveryID() {
		return discoveryID;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getResourceGained() {
		return resourceGained;
	}

   public int getQuantity() {
	   return quantity;
   }



}
