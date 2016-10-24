import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class MinesWeeperTest {

	@Test
	public void dont_parse_empty_board() throws Exception {
		assertThat(findBombs(terminationBoard()), is(""));
	}

	@Test
	public void parse_one_board_with_different_lines() throws Exception {
		assertThat(findBombs(boardWithMines(1, 1) + terminationBoard()), is("Game #1\\n*\\n"));
		assertThat(findBombs(boardWithMines(1, 2) + terminationBoard()), is("Game #1\\n**\\n"));
	}

	@Test
	public void parse_board_with_different_lines_without_mines() throws Exception {
		assertThat(
				findBombs(boardWithoutMines(1, 1) //
						+ terminationBoard()), //
				is("Game #1\\n0\\n"));
	}

	@Test
	public void neighbours_of_mine_in_same_line() throws Exception {
		assertThat(
				findBombs("1 2\\n*.\\n" //
						+ terminationBoard()), //
				is("Game #1\\n*1\\n"));
		assertThat(
				findBombs("1 3\\n.*.\\n" //
						+ terminationBoard()), //
				is("Game #1\\n1*1\\n"));
		assertThat(
				findBombs("1 4\\n..*.\\n" //
						+ terminationBoard()), //
				is("Game #1\\n01*1\\n"));
		assertThat(
				findBombs("1 4\\n.*..\\n" //
						+ terminationBoard()), //
				is("Game #1\\n1*10\\n"));
	}

	@Test
	public void neighbours_of_mine_in_same_column() throws Exception {
		assertThat(
				findBombs("2 1\\n*\\n.\\n" //
						+ terminationBoard()), //
				is("Game #1\\n*\\n1\\n"));
		assertThat(
				findBombs("2 1\\n.\\n*\\n" //
						+ terminationBoard()), //
				is("Game #1\\n1\\n*\\n"));
	}

	@Test
	public void neighbours_of_a_single_mine() throws Exception {
		assertThat(
				findBombs("2 3\\n...\\n.*.\\n" //
						+ terminationBoard()), //
				is("Game #1\\n111\\n1*1\\n"));
		assertThat(
				findBombs("3 3\\n...\\n.*.\\n...\\n" //
						+ terminationBoard()), //
				is("Game #1\\n111\\n1*1\\n111\\n"));
	}

	@Test
	public void neighbours_of_a_mutiple_mines() throws Exception {
		assertThat(
				findBombs("3 3\\n*..\\n.*.\\n..*\\n" //
						+ terminationBoard()), //
				is("Game #1\\n*21\\n2*2\\n12*\\n"));
	}

	@Test
	public void acceptance_test() throws Exception {
		assertThat(findBombs("4 4\\n*...\\n....\\n.*..\\n....\\n3 5\\n**...\\n.....\\n.*...\\n0 0"), //
				is("Game #1\\n*100\\n2210\\n1*10\\n1110\\nGame #2\\n**100\\n33200\\n1*100\\n"));
	}

	private String boardWithoutMines(int rows, int cols) {
		return minesWeeperBoard(rows, cols, ".");
	}

	private String boardWithMines(int rows, int cols) {
		return minesWeeperBoard(rows, cols, "*");
	}

	private String terminationBoard() {
		return "0 0";
	}

	private String minesWeeperBoard(int rows, int cols, String initialValue) {
		String mines = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				mines += initialValue;
			}
			mines += "\\n";
		}
		return "" + rows + " " + cols + "\\n" + mines;
	}

	@Test
	public void parse_more_boards_with_different_lines() throws Exception {
		assertThat(
				findBombs(boardWithMines(1, 1) //
						+ boardWithMines(1, 1) //
						+ terminationBoard()), //
				is("Game #1\\n*\\nGame #2\\n*\\n"));
		assertThat(
				findBombs(boardWithMines(1, 1) //
						+ boardWithMines(1, 1) //
						+ boardWithMines(1, 1) //
						+ terminationBoard()), //
				is("Game #1\\n*\\nGame #2\\n*\\nGame #3\\n*\\n"));
		assertThat(
				findBombs(boardWithMines(1, 1) //
						+ boardWithMines(2, 2) //
						+ boardWithMines(1, 1) //
						+ terminationBoard()), //
				is("Game #1\\n*\\nGame #2\\n**\\n**\\nGame #3\\n*\\n"));
	}

	private String findBombs(String input) {
		return new MinesWeeper().findAllBombs(input);
	}
}
