import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class MinesWeeperSolver {

	private int numberOfLines;
	private int numberOfColumns;
	private String[] board;

	public MinesWeeperSolver(int numberOfLines, int numberOfColumns, String[] board) {
		this.numberOfLines = numberOfLines;
		this.numberOfColumns = numberOfColumns;
		this.board = board;
	}

	public void solve() {
		initMineCountForAllFields();
		incrementMineCountForAllFields();
	}

	private void incrementMineCountForAllFields() {
		for (int i = 0; i < numberOfLines; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				if (fieldContainsMine(i, j)) {
					incrementAllNeighbours(i, j);
				}
			}
		}
	}

	private void initMineCountForAllFields() {
		for (int i = 0; i < numberOfLines; i++) {
			board[i] = board[i].replaceAll("\\.", "0");
		}
	}

	private void incrementAllNeighbours(int row, int col) {
		handleMineNeighbourAt(row - 1, col - 1);
		handleMineNeighbourAt(row - 1, col);
		handleMineNeighbourAt(row - 1, col + 1);
		handleMineNeighbourAt(row, col + 1);
		handleMineNeighbourAt(row, col - 1);
		handleMineNeighbourAt(row + 1, col - 1);
		handleMineNeighbourAt(row + 1, col);
		handleMineNeighbourAt(row + 1, col + 1);
	}

	private void handleMineNeighbourAt(int row, int col) {
		if (isValidLine(row) && isValidColumn(col) && !fieldContainsMine(row, col)) {
			int numberOfMines = parseInt(valueOf(fieldAt(row, col))) + 1;
			board[row] = board[row].substring(0, col) + numberOfMines + board[row].substring(col + 1);
		}
	}

	private boolean isValidLine(int i) {
		return i >= 0 && i < numberOfLines;
	}

	private boolean isValidColumn(int j) {
		return j >= 0 && j < numberOfColumns;
	}

	private boolean fieldContainsMine(int row, int col) {
		return fieldAt(row, col) == '*';
	}

	private char fieldAt(int row, int col) {
		return board[row].charAt(col);
	}

	public String[] getLines() {
		return board;
	}

}
