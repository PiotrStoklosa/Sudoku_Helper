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

public class Number_Selector extends JFrame implements ActionListener, KeyListener, WindowListener {

	private static final long serialVersionUID = 2349463119831675099L;
	
	JButton[] Numbers;
	Sudoku_Block buttoninstance;
	Board board;
	
	int x;
	int y;

	Number_Selector(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		this.board = board;
		
		addKeyListener(this);
		addWindowListener(this);
		setFocusable(true);
		
		buttoninstance = board.Block[x * board.NumberofBlocks + y];
		
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

		for (int i = x * board.NumberofBlocks; i < x * board.NumberofBlocks + board.NumberofBlocks; i++) {
			
			if (!board.Block[i].getEmpty()
					&& Integer.parseInt(board.Block[i].getText()) == number)
				
				return blunder.Made_Blunder(x, i % board.NumberofBlocks);
		}
		
		for (int i = y; i < board.NumberofBlocks * board.NumberofBlocks; i += board.NumberofBlocks)
			if (!board.Block[i].getEmpty()
					&& Integer.parseInt(board.Block[i].getText()) == number)
				
				return blunder.Made_Blunder(i / board.NumberofBlocks, y);

		int first_block_row = x - x % board.block_height;
		int first_block_column = y - y % board.block_width;

		for (int j = 0; j < board.block_height; j++)
			for (int k = 0; k < board.block_width; k++)
				if (!board.Block[(first_block_row + j) * board.NumberofBlocks + first_block_column + k].getEmpty()
						&& Integer.parseInt(
								board.Block[(first_block_row + j) * board.NumberofBlocks + first_block_column + k].getText()) == number)
					return blunder.Made_Blunder(first_block_row + j, first_block_column + k);

		return blunder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		for (int i = 0; i < board.NumberofBlocks; i++) {
			
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
						
						board.mistakes.new_error();
						
						
						board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
								+ (test.GetY() + 1) + " number " + (i + 1) + " already exist!</html>");
						
						buttoninstance.setBackground(Color.red);
						buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
						buttoninstance.setForeground(Color.black);
						buttoninstance.setText(Integer.toString(i + 1));
						
						Sudoku_Block.counter = !Sudoku_Block.counter;
						
						if (board.mistakes.limit) {
							board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
									+ (test.GetY() + 1) + " number " + (i + 1) + " already exist! <br/> You reach maximum limit of mistakes!</html>");
							
							board.lose();
							
						}
						dispose();
					} else {
						board.Candidates_Update(i,x,y);
						board.counter++;
						if (board.counter==board.NumberofBlocks*board.NumberofBlocks) {
							board.timer.off();
							board.win();
						}
						buttoninstance.empty=false;
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
			chr--;
			if (board.Candidate.on_off) {
				buttoninstance.Player_Candidates[chr] = !buttoninstance.Player_Candidates[chr];
				buttoninstance.print();
				board.info.setText("Info");
				buttoninstance.setBackground(Color.white);
				buttoninstance.setForeground(Color.black);
				Sudoku_Block.counter = !Sudoku_Block.counter;
				dispose();
			} else {
				Blunder test = correct(chr + 1);
				if (test.made_blunder()) {
					board.mistakes.new_error();
					board.info.setText("<html>Incorrect number!<br/>In row:" + (test.GetX() + 1) + " column:"
							+ (test.GetY() + 1) + " number " + (chr + 1) + " already exist!</html>");
					buttoninstance.setBackground(Color.red);
					buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
					buttoninstance.setForeground(Color.black);
					buttoninstance.setText(Integer.toString(chr+ 1));
					Sudoku_Block.counter = !Sudoku_Block.counter;
					dispose();
				} else {
					board.Candidates_Update(chr,x,y);
					board.counter++;
					if (board.counter==board.NumberofBlocks*board.NumberofBlocks) {
						board.timer.off();
						board.win();
					}
					buttoninstance.empty=false;
					board.info.setText("Info");
					buttoninstance.setBackground(Color.white);
					buttoninstance.setText(Integer.toString(chr + 1));
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
