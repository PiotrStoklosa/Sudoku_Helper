package sudoku.boards;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class NumberSelector extends JFrame implements ActionListener, KeyListener, WindowListener {

	@Serial
	private static final long serialVersionUID = 2349463119831675099L;

	private final JButton[] Numbers;
	private final SudokuBlock buttoninstance;
	private final Board board;

	private final int x;
	private final int y;
	private final String role;

	public NumberSelector(int x, int y, Board board, String role) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.role = role;

		addKeyListener(this);
		addWindowListener(this);
		setFocusable(true);

		buttoninstance = board.Block[x * board.getNumberofBlocks() + y];

		setSize(200, 235);
		setResizable(false);
		setLocation(50 + 50 * y + 600, 50 + 50 * x + 50);
		setLayout(null);
		setTitle("Select number");

		Numbers = new JButton[board.getNumberofBlocks()];
		/*
		 * create appropriate quantity and layout of buttons
		 */
		for (int i = 0; i < Math.floor(Math.sqrt(board.getNumberofBlocks())); i++)
			for (int j = 0; j < board.getNumberofBlocks() / Math.floor(Math.sqrt(board.getNumberofBlocks())); j++) {

				Numbers[(int) (j * Math.floor(Math.sqrt(board.getNumberofBlocks())) + i)] = new JButton(
						Integer.toString((int) (j * Math.floor(Math.sqrt(board.getNumberofBlocks())) + i) + 1));
				Numbers[(int) (j * Math.floor(Math.sqrt(board.getNumberofBlocks())) + i)].setBounds(20 + 50 * i, 20 + 50 * j,
						41, 41);

				add(Numbers[(int) (j * Math.floor(Math.sqrt(board.getNumberofBlocks())) + i)]);
				Numbers[(int) (j * Math.floor(Math.sqrt(board.getNumberofBlocks())) + i)].addActionListener(this);
			}
	}
	/*
	 * test to check the correctness of inserted number
	 */
	public Blunder correct(int number) {

		Blunder blunder = new Blunder();
		/*
		 * test in row
		 */
		for (int i = x * board.getNumberofBlocks(); i < x * board.getNumberofBlocks() + board.getNumberofBlocks(); i++) {

			if (!board.Block[i].getEmpty() && Integer.parseInt(board.Block[i].getText()) == number)

				return blunder.Made_Blunder(x, i % board.getNumberofBlocks());
		}
		/*
		 * test in column
		 */
		for (int i = y; i < board.getNumberofBlocks() * board.getNumberofBlocks(); i += board.getNumberofBlocks())
			if (!board.Block[i].getEmpty() && Integer.parseInt(board.Block[i].getText()) == number)

				return blunder.Made_Blunder(i / board.getNumberofBlocks(), y);
		/*
		 * test in the block
		 */
		int first_block_row = x - x % board.getBlock_height();
		int first_block_column = y - y % board.getBlock_width();

		for (int j = 0; j < board.getBlock_height(); j++)
			for (int k = 0; k < board.getBlock_width(); k++)
				if (!board.Block[(first_block_row + j) * board.getNumberofBlocks() + first_block_column + k].getEmpty()
						&& Integer.parseInt(
								board.Block[(first_block_row + j) * board.getNumberofBlocks() + first_block_column + k]
										.getText()) == number)
					return blunder.Made_Blunder(first_block_row + j, first_block_column + k);
		/*
		 * if the board has the finished copy in the file, program compare both versions to detect a mistake
		 */
		if (board.getIndex_in_file() > -1) {

			Path input = Paths.get("./boards.txt");
			List<String> pages = null;
			
			try {
				
				pages = Files.readAllLines(input, StandardCharsets.UTF_8);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}

			assert pages != null;
			String[] copy = pages.get(board.getIndex_in_file() + 1).split(" ");
			
			if (Integer.parseInt(copy[x * board.getNumberofBlocks() + y]) != number) {
				
				blunder.setCorrectness_not_defined();
				return blunder.Made_Blunder(x, y);
				
			}
		}

		return blunder;
	}
	
	public void if_candidates(int number) {
		/*
		 * if player insert a candidate
		 */
		buttoninstance.setPlayer_Candidates_number(number, !buttoninstance.getPlayer_Candidates()[number]);
		buttoninstance.print();
		board.info.setText("Info");
		buttoninstance.setBackground(Color.white);
		buttoninstance.setForeground(Color.black);

		SudokuBlock.counter = !SudokuBlock.counter;

		dispose();
	}
	/*
	 * if player insert an incorrect number
	 */
	public void if_error(Blunder test, int number, String role) {

		board.mistakes.new_error();
		if (test.isCorrectness_not_defined())
			board.info.setText("<html>Incorrect number!</html>");
		else
			board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:" + (test.GetY() + 1)
				+ " number " + (number + 1) + " already exists!</html>");

		buttoninstance.setBackground(Color.red);
		buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
		buttoninstance.setForeground(Color.black);
		buttoninstance.setText(Integer.toString(number + 1));

		SudokuBlock.counter = !SudokuBlock.counter;
		
		if (role.equals("play"))
			if (board.mistakes.isLimit()) {
				if (test.isCorrectness_not_defined())
					board.info.setText("<html>Incorrect number!<br/> You reach maximum limit of mistakes!</html>");
				else
					board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
						+ (test.GetY() + 1) + " number " + (number + 1)
						+ " already exist! <br/> You reach maximum limit of mistakes!</html>");

				board.lose();

			}
		
		dispose();

	}
	/*
	 * if player insert a correct number
	 */
	public void if_correct(int number) {

		buttoninstance.setEmpty(false);
		board.info.setText("Info");
		buttoninstance.setBackground(Color.white);
		buttoninstance.setText(Integer.toString(number + 1));
		buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
		buttoninstance.setForeground(Color.black);
		SudokuBlock.counter = !SudokuBlock.counter;
		dispose();

	}
	/*
	 * two versions of picking a numbers (one from keyboard one from window)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		for (int i = 0; i < board.getNumberofBlocks(); i++) {

			if (source == Numbers[i] && role.equals("play"))
				insertNumber(i);

			else if (source == Numbers[i]) {

				Blunder test = correct(i + 1);

				if (test.made_blunder())
					if_error(test, i, "edit");

				else
					if_correct(i);
			}
		}
	}

	private void insertNumber(int i) {
		if (board.Candidate.on_off)
			if_candidates(i);

		else {

			Blunder test = correct(i + 1);

			if (test.made_blunder())
				if_error(test, i, "play");

			else {

				board.Candidates_Update(i, x, y);
				board.IncreaseCounter();

				if (board.getCounter() == board.getNumberofBlocks() * board.getNumberofBlocks()) {
					board.timer.off();
					board.win();
				}

				buttoninstance.enabled = !buttoninstance.enabled;

				if_correct(i);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {

			int chr = c;
			chr -= 48;
			chr--;

			if (role.equals("play"))
				insertNumber(chr);

			else {
				Blunder test = correct(chr + 1);

				if (test.made_blunder())
					if_error(test, chr, "edit");

				else
					if_correct(chr);

			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		
		buttoninstance.setBackground(Color.white);
		SudokuBlock.counter = !SudokuBlock.counter;

	}
	
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}

}
