import static java.lang.Integer.parseInt;
import static java.util.Arrays.copyOfRange;

import java.util.ArrayList;

public class MinesWeeper {

	public String findAllBombs(String input) {
		ArrayList<MinesWeeperSolver> boards = parseBoards(input.split("\\\\n"));
		solveBoards(boards);

		return formatResult(boards);
	}

	private ArrayList<MinesWeeperSolver> parseBoards(String[] lines) {
		int pos = 0;
		ArrayList<MinesWeeperSolver> boards = new ArrayList<>();
		while (!lines[pos].equals("0 0")) {
			int numberOfLines = parseLineNumber(lines, pos);
			String[] boardLines = copyOfRange(lines, pos + 1, pos + numberOfLines + 1);
			boards.add(new MinesWeeperSolver(numberOfLines, parseColumnNumber(lines, pos), boardLines));

			pos += numberOfLines + 1;
		}
		return boards;
	}

	private void solveBoards(ArrayList<MinesWeeperSolver> boards) {
		for (MinesWeeperSolver minesWeeperSolver : boards) {
			minesWeeperSolver.solve();
		}
	}

	private String formatResult(ArrayList<MinesWeeperSolver> boards) {
		String result = "";
		int gameNumber = 1;
		for (MinesWeeperSolver minesWeeperSolver : boards) {
			result += "Game #" + gameNumber + "\\n";

			for (String solvedLine : minesWeeperSolver.getLines()) {
				result += solvedLine;
				result += "\\n";
			}
			gameNumber++;
		}
		return result;
	}

	private int parseColumnNumber(String[] lines, int pos) {
		return parseInt(lines[pos].split(" ")[1]);
	}

	private int parseLineNumber(String[] lines, int pos) {
		return parseInt(lines[pos].split(" ")[0]);
	}
}
