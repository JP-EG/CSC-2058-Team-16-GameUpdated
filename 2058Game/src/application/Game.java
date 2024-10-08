package application;


public class Game extends Board {

	private Board board;

	public Game(int sqID, String sqName, String description) {
		super(sqID, sqName, description);

		this.board = new Board(1, "board", "one");

	}

	public Board getBoard() {
		return this.board;
	}

}
