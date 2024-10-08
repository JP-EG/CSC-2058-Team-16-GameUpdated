package application;

import java.util.List;

public class Square {

	public static int nextID = 1; //changed to 1 makes more sense when moving to new square
	private int sqID;
	private String sqName;
	private Player currentPlayer;
	private Setback setback;
	private Discovery discovery;
	private Task task;
	
	private TaskSquare taskSquare = new TaskSquare();

	public Square(String sqName, Setback setback) {

		this.sqName = sqName;
		this.sqID = nextID++;
		this.currentPlayer = null;

		this.setback = setback;

	}

	public Square(String sqName, Discovery discovery) {

		this.sqName = sqName;
		this.sqID = nextID++;
		this.currentPlayer = null;

		this.discovery = discovery;

	}

	public Square(String sqName, Task task) {

		this.sqName = sqName;
		this.sqID = nextID++;
		this.currentPlayer = null;

		this.task = task;

	}

	public Square(String sqName) {

		this.sqName = sqName;
		this.sqID = nextID++;
		this.currentPlayer = null;

	}

	public Setback getSetback() {
		return setback;
	}

	public Discovery getDiscovery() {
		return discovery;
	}

	public Task getTask() {
		return task;
	}
	
	public List<Task> getTasks(){
		return taskSquare.getTasks();
	}

	public Setback getNewSetback() {
		Setback setback = new SetbackSquare().randomSetback();
		setSetback(setback);
		return setback;
	}

	public Discovery getNewDiscovery() {
		Discovery discovery = new DiscoverySquare().randomDiscovery();
		setDiscovery(discovery);
		return discovery;
	}

	public Task getNewTask(int num) {
		Task task = new TaskSquare().getSpecificTask(num);
		setTask(task);
		return task;
	}

	public void setSetback(Setback setback) {
		this.setback = setback;
	}

	public void setDiscovery(Discovery discovery) {
		this.discovery = discovery;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public void setPlayer(Player player) {
		this.setCurrentPlayer(player);
	}

	public Player getPlayer() {
		return this.getCurrentPlayer();
	}

	public String getName() {
		return this.sqName;
	}
	
	public int getSquareID() {
		return this.sqID;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
