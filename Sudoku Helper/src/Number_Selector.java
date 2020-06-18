import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Number_Selector extends JFrame implements ActionListener, KeyListener, WindowListener {
	JButton[] Numbers;
	Sudoku_Block buttoninstance;
	int x;
	int y;
	Board board;

	Number_Selector(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		this.board = board;

		addKeyListener(this);
		addWindowListener(this);
		setFocusable(true);
		buttoninstance = board.Block[x * 9 + y];
		setSize(200, 235);
		setResizable(false);
		setLocation(50 + 50 * y + 600, 50 + 50 * x + 50);
		setLayout(null);
		setTitle("Select number");
		Numbers = new JButton[9];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				Numbers[j * 3 + i] = new JButton(Integer.toString(j * 3 + i + 1));
				Numbers[j * 3 + i].setBounds(20 + 50 * i, 20 + 50 * j, 41, 41);
				add(Numbers[j * 3 + i]);
				Numbers[j * 3 + i].addActionListener(this);
			}
	}

	public Blunder correct(int number) {
		Blunder blunder = new Blunder();

		for (int i = x * 9; i < x * 9 + 9; i++) {
			if (board.Block[i].getText() != "" && board.Block[i].getText().length() < 5
					&& Integer.parseInt(board.Block[i].getText()) == number)
				return blunder.Made_Blunder(x, i % 9);
		}
		for (int i = y; i < 81; i += 9)
			if (board.Block[i].getText() != "" && board.Block[i].getText().length() < 5
					&& Integer.parseInt(board.Block[i].getText()) == number)
				return blunder.Made_Blunder(i / 9, y);

		int first_block_row = x - x % 3;
		int first_block_column = y - y % 3;

		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 3; k++)
				if (board.Block[(first_block_row + j) * 9 + first_block_column + k].getText() != ""
						&& board.Block[(first_block_row + j) * 9 + first_block_column + k].getText().length() < 5
						&& Integer.parseInt(
								board.Block[(first_block_row + j) * 9 + first_block_column + k].getText()) == number)
					return blunder.Made_Blunder(first_block_row + j, first_block_column + k);

		return blunder;
	}

	public void Candidates_Update(int number) {
		for (int i = x * 9; i < x * 9 + 9; i++)
			board.Block[i].All_Candidates[number] = false;

		for (int i = y; i < 81; i += 9)
			board.Block[i].All_Candidates[number] = false;

		int first_block_row = x - x % 3;
		int first_block_column = y - y % 3;

		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 3; k++)
				board.Block[(first_block_row + j) * 9 + first_block_column + k].All_Candidates[number] = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		for (int i = 0; i < 9; i++) {
			if (source == Numbers[i]) {
				if (board.Candidate.on_off) {
					buttoninstance.Player_Candidates[i] = !buttoninstance.Player_Candidates[i];
					buttoninstance.print();
					board.info.setText("Info");
					buttoninstance.setBackground(Color.white);
					buttoninstance.setForeground(Color.black);
					Sudoku_Block.counter = !Sudoku_Block.counter;
					dispose();
				} else {
					Blunder test = correct(i + 1);
					if (test.made_blunder()) {
						board.Mistake.new_error();
						board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
								+ (test.GetY() + 1) + " number " + (i + 1) + " already exist!</html>");
						buttoninstance.setBackground(Color.red);
						buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
						buttoninstance.setForeground(Color.black);
						buttoninstance.setText(Integer.toString(i + 1));
						Sudoku_Block.counter = !Sudoku_Block.counter;
						dispose();
					} else {
						Candidates_Update(i);
						board.info.setText("Info");
						buttoninstance.setBackground(Color.white);
						buttoninstance.setText(Integer.toString(i + 1));
						buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
						buttoninstance.setForeground(Color.black);
						Sudoku_Block.counter = !Sudoku_Block.counter;
						buttoninstance.enabled = !buttoninstance.enabled;
						dispose();
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
			int chr = c;
			chr -= 48;
			if (board.Candidate.on_off) {
				buttoninstance.Player_Candidates[chr - 1] = !buttoninstance.Player_Candidates[chr - 1];
				buttoninstance.print();
				board.info.setText("Info");
				buttoninstance.setBackground(Color.white);
				buttoninstance.setForeground(Color.black);
				Sudoku_Block.counter = !Sudoku_Block.counter;
				dispose();
			} else {
				Blunder test = correct(chr - 1 + 1);
				if (test.made_blunder()) {
					board.Mistake.new_error();
					board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
							+ (test.GetY() + 1) + " number " + (chr - 1 + 1) + " already exist!</html>");
					buttoninstance.setBackground(Color.red);
					buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
					buttoninstance.setForeground(Color.black);
					buttoninstance.setText(Integer.toString(chr - 1 + 1));
					Sudoku_Block.counter = !Sudoku_Block.counter;
					dispose();
				} else {
					Candidates_Update(chr - 1);
					board.info.setText("Info");
					buttoninstance.setBackground(Color.white);
					buttoninstance.setText(Integer.toString(chr - 1 + 1));
					buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
					buttoninstance.setForeground(Color.black);
					Sudoku_Block.counter = !Sudoku_Block.counter;
					buttoninstance.enabled = !buttoninstance.enabled;
					dispose();
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		buttoninstance.setBackground(Color.white);
		Sudoku_Block.counter = !Sudoku_Block.counter;

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
