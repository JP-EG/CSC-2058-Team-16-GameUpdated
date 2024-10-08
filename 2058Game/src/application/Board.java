package application;

public class Board extends Square {

	private Square[] squares;

	public Board(int sqID, String sqName, String description) {
		super(sqName);

		initialise();

	}

	public void initialise() {
		
		squares = new Square[20];

		squares[0] = new Square("Start");

		squares[1] = new Square("Task 1(a): Site Assessment", this.getNewTask(1));
		squares[2] = new Square("Task 1(b): Obtain Permits and Approvals", this.getNewTask(2));

		squares[3] = new Square("Setback Square", this.getNewSetback());

		squares[4] = new Square("Task 1(c): Conduct an Environmental Impact Assessment", this.getNewTask(3));
		squares[5] = new Square("Task 2(a): Obtain Solar Panels, Pipes and Equipment", this.getNewTask(4));

		squares[6] = new Square("Discovery Square", this.getNewDiscovery());

		squares[7] = new Square("Task 2(b): Manage Budget for Equipment", this.getNewTask(5));
		squares[8] = new Square("Task 3(a): Assemble and Install Equipment", this.getNewTask(6));

		squares[9] = new Square("Setback Square", this.getNewSetback());

		squares[10] = new Square("Task 3(b): Ensure connection of Solar System", this.getNewTask(7));
		squares[11] = new Square("Task 3(c): Test Water Pump", this.getNewTask(8));

		squares[12] = new Square("Discovery Square", this.getNewDiscovery());

		squares[13] = new Square("Task 4(a): Establish a maintenance plan", this.getNewTask(9));
		squares[14] = new Square("Task 4(b): Train Community Members to operate", getNewTask(10));

		squares[15] = new Square("Setback Square", this.getNewSetback());

		squares[16] = new Square("Task 4(c): Develop a community funding model", this.getNewTask(11));
		squares[17] = new Square("Task 5(a): Engage with local community to gain support", getNewTask(12));

		squares[18] = new Square("Discovery Square", this.getNewDiscovery());

		squares[19] = new Square("Task 5(b): Resolve any Community Disputes", this.getNewTask(13));

	}

	public Square[] getSquares() {
		return this.squares;
	}
}
