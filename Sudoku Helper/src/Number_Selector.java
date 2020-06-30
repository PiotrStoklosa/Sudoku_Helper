import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Number_Selector extends JFrame implements ActionListener, KeyListener, WindowListener {

	private static final long serialVersionUID = 2349463119831675099L;

	JButton[] Numbers;
	Sudoku_Block buttoninstance;
	Board board;

	int x;
	int y;
	String role;

	Number_Selector(int x, int y, Board board, String role) {
		this.x = x;
		this.y = y;
		this.board = board;
		this.role = role;

		addKeyListener(this);
		addWindowListener(this);
		setFocusable(true);

		buttoninstance = board.Block[x * board.NumberofBlocks + y];

		setSize(200, 235);
		setResizable(false);
		setLocation(50 + 50 * y + 600, 50 + 50 * x + 50);
		setLayout(null);
		setTitle("Select number");

		Numbers = new JButton[board.NumberofBlocks];

		for (int i = 0; i < Math.floor(Math.sqrt(board.NumberofBlocks)); i++)
			for (int j = 0; j < board.NumberofBlocks / Math.floor(Math.sqrt(board.NumberofBlocks)); j++) {

				Numbers[(int) (j * Math.floor(Math.sqrt(board.NumberofBlocks)) + i)] = new JButton(
						Integer.toString((int) (j * Math.floor(Math.sqrt(board.NumberofBlocks)) + i) + 1));
				Numbers[(int) (j * Math.floor(Math.sqrt(board.NumberofBlocks)) + i)].setBounds(20 + 50 * i, 20 + 50 * j,
						41, 41);

				add(Numbers[(int) (j * Math.floor(Math.sqrt(board.NumberofBlocks)) + i)]);
				Numbers[(int) (j * Math.floor(Math.sqrt(board.NumberofBlocks)) + i)].addActionListener(this);
			}
	}

	public Blunder correct(int number) {

		Blunder blunder = new Blunder();

		for (int i = x * board.NumberofBlocks; i < x * board.NumberofBlocks + board.NumberofBlocks; i++) {

			if (!board.Block[i].getEmpty() && Integer.parseInt(board.Block[i].getText()) == number)

				return blunder.Made_Blunder(x, i % board.NumberofBlocks);
		}

		for (int i = y; i < board.NumberofBlocks * board.NumberofBlocks; i += board.NumberofBlocks)
			if (!board.Block[i].getEmpty() && Integer.parseInt(board.Block[i].getText()) == number)

				return blunder.Made_Blunder(i / board.NumberofBlocks, y);

		int first_block_row = x - x % board.block_height;
		int first_block_column = y - y % board.block_width;

		for (int j = 0; j < board.block_height; j++)
			for (int k = 0; k < board.block_width; k++)
				if (!board.Block[(first_block_row + j) * board.NumberofBlocks + first_block_column + k].getEmpty()
						&& Integer.parseInt(
								board.Block[(first_block_row + j) * board.NumberofBlocks + first_block_column + k]
										.getText()) == number)
					return blunder.Made_Blunder(first_block_row + j, first_block_column + k);

		if (board.index_in_file > -1) {

			Path input = Paths.get("./boards.txt");
			List<String> pages = null;
			
			try {
				
				pages = Files.readAllLines(input, Charset.forName("UTF-8"));
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
			String[] copy = pages.get(board.index_in_file + 1).split(" ");
			
			if (Integer.parseInt(copy[x * board.NumberofBlocks + y]) != number) {
				
				blunder.setcorrectness_not_defined();
				return blunder.Made_Blunder(x, y);
				
			}
		}

		return blunder;
	}

	public void if_candidates(int number) {
		
		buttoninstance.Player_Candidates[number] = !buttoninstance.Player_Candidates[number];
		buttoninstance.print();
		board.info.setText("Info");
		buttoninstance.setBackground(Color.white);
		buttoninstance.setForeground(Color.black);

		Sudoku_Block.counter = !Sudoku_Block.counter;

		dispose();
	}

	public void if_error(Blunder test, int number, String role) {

		board.mistakes.new_error();
		if (test.correctness_not_defined)
			board.info.setText("<html>Incorrect number!</html>");
		else
			board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:" + (test.GetY() + 1)
				+ " number " + (number + 1) + " already exist!</html>");

		buttoninstance.setBackground(Color.red);
		buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
		buttoninstance.setForeground(Color.black);
		buttoninstance.setText(Integer.toString(number + 1));

		Sudoku_Block.counter = !Sudoku_Block.counter;
		
		if (role.equals("play"))
			if (board.mistakes.limit) {
				if (test.correctness_not_defined)
					board.info.setText("<html>Incorrect number!<br/> You reach maximum limit of mistakes!</html>");
				else
					board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
						+ (test.GetY() + 1) + " number " + (number + 1)
						+ " already exist! <br/> You reach maximum limit of mistakes!</html>");

				board.lose();

			}
		
		dispose();

	}

	public void if_correct(int number) {

		buttoninstance.empty = false;
		board.info.setText("Info");
		buttoninstance.setBackground(Color.white);
		buttoninstance.setText(Integer.toString(number + 1));
		buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
		buttoninstance.setForeground(Color.black);
		Sudoku_Block.counter = !Sudoku_Block.counter;
		dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		for (int i = 0; i < board.NumberofBlocks; i++) {

			if (source == Numbers[i] && role.equals("play")) {

				if (board.Candidate.on_off)
					if_candidates(i);

				else {

					Blunder test = correct(i + 1);

					if (test.made_blunder())
						if_error(test, i, "play");

					else {

						board.Candidates_Update(i, x, y);
						board.counter++;

						if (board.counter == board.NumberofBlocks * board.NumberofBlocks) {
							board.timer.off();
							board.win();
						}

						buttoninstance.enabled = !buttoninstance.enabled;

						if_correct(i);
					}
				}
			} else if (source == Numbers[i]) {

				Blunder test = correct(i + 1);

				if (test.made_blunder())
					if_error(test, i, "edit");

				else
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

			if (role.equals("play")) {

				if (board.Candidate.on_off)
					if_candidates(chr);

				else {

					Blunder test = correct(chr + 1);

					if (test.made_blunder())
						if_error(test, chr, "play");

					else {

						board.Candidates_Update(chr, x, y);
						board.counter++;

						if (board.counter == board.NumberofBlocks * board.NumberofBlocks) {
							board.timer.off();
							board.win();
						}

						buttoninstance.enabled = !buttoninstance.enabled;
						if_correct(chr);
					}
				}
			} else {
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
		Sudoku_Block.counter = !Sudoku_Block.counter;

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
