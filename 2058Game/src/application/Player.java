package application;

import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Player {
	
	public static int nextID = 0; //start from 1 so it matches Players in the game e.g Player 1,2,3,4 not 0,1,2,3?
	private int ID;
	private String name;	
	private ImageView avatar;
	private int position;
	private boolean isChosen;
	
	private Square currentSquare;
	private ArrayList<String> tasks;
	private Resource resource;
	
	public Player(String name) {
		this.ID = nextID++;
		this.name = name;
		this.resource = null;
		this.currentSquare = null;
		this.tasks = new ArrayList<>();
	}
	
	public Player() {
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsChosen() {
		return this.isChosen;
	}
	
	public void setIsChosen(boolean TF) {
		this.isChosen = TF;
	}
	
	public void setAvatar(ImageView avatar) {
		this.avatar = avatar;
	}
	
	public int getSquareNumber(Square sq) {
		return currentSquare.getSquareID();
	}
	
	public void setCurrentSquare(Square sq) {
		this.currentSquare = sq;
	}
	
	public Square getCurrentSquare() {
		return currentSquare;
	}
	
	public void addTask(String task) {
	    tasks.add(task);
	}

	public ArrayList<String> getTasks() {
		return tasks;
	}

	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public Resource getResource() {
		return this.resource;
	}

	public ImageView getAvatar() {
		return this.avatar;
	}

	public int getPosition() {
		return this.position;
	}

	public void setPosition(int newPosition) {
		this.position = newPosition;
		
	}
}

