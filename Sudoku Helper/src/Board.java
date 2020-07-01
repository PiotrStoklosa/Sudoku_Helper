import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFrame;

public abstract class Board extends JFrame implements ActionListener, Methods, Observer {

	private static final long serialVersionUID = 2589826376003918979L;

	private int NumberofBlocks;
	private int index_in_file;
	private int block_width, block_height;
	private int border_right, border_left, border_top, border_bottom;
	private int counter;
	private boolean finished;
	private String role;
	protected MenuBar Bar;

	protected Sudoku_Block[] Block;

	protected Candidates Candidate;
	protected Hint hint;
	protected Info info;
	protected Mistakes mistakes;
	protected Show_next show_next;
	protected Solve solve;
	protected Timer timer;
	protected Show_Solution show_solution;
	protected Finish_Button finish;

	/*
	 * getters and setters
	 */

	public int getIndex_in_file() {
		return index_in_file;
	}

	public void setIndex_in_file(int index_in_file) {
		this.index_in_file = index_in_file;
	}

	public int getBlock_width() {
		return block_width;
	}

	public int getBlock_height() {
		return block_height;
	}

	protected int getBorder_right() {
		return border_right;
	}

	protected void setBorder_right(int border_right) {
		this.border_right = border_right;
	}

	protected int getBorder_left() {
		return border_left;
	}

	protected void setBorder_left(int border_left) {
		this.border_left = border_left;
	}

	protected int getBorder_top() {
		return border_top;
	}

	protected void setBorder_top(int border_top) {
		this.border_top = border_top;
	}

	protected int getBorder_bottom() {
		return border_bottom;
	}

	protected void setBorder_bottom(int border_bottom) {
		this.border_bottom = border_bottom;
	}

	public int getCounter() {
		return counter;
	}

	protected void setCountertoNull() {
		counter = 0;
	}

	public void IncreaseCounter() {
		counter++;
	}

	protected boolean isFinished() {
		return finished;
	}

	public int getNumberofBlocks() {
		return NumberofBlocks;
	}

	/*
	 * Main constructor
	 */

	Board(int NumberofBlocks, int block_width, int block_height, String role) {

		setLayout(null);
		setFocusable(true);
		finished = false;
		this.role = role;
		index_in_file = -1;

		Block = new Sudoku_Block[NumberofBlocks * NumberofBlocks];
		this.NumberofBlocks = NumberofBlocks;
		this.block_width = block_width;
		this.block_height = block_height;

		Bar = new MenuBar();
		setJMenuBar(Bar);
		setSize(800, 800);
		setResizable(false);
		setLocation(500, 100);
		setTitle("Sudoku");
		Bar.Main_Menu.addActionListener(this);
		Bar.Restart.addActionListener(this);
		Bar.Save.addActionListener(this);
		Bar.Load.addActionListener(this);

		if (role.equals("play")) {
			timer = new Timer(this);
			timer.setOpaque(true);
			timer.setBounds(550, 50, 200, 80);

			add(timer);

			mistakes = new Mistakes(3);
			mistakes.setBounds(550, 140, 200, 80);
			add(mistakes);

			Candidate = new Candidates();
			Candidate.setBounds(550, 230, 200, 80);
			add(Candidate);
			Candidate.addActionListener(this);

			hint = new Hint();
			add(hint);
			hint.setBounds(550, 320, 200, 80);
			hint.addActionListener(this);

			show_next = new Show_next();
			add(show_next);
			show_next.setBounds(550, 410, 200, 80);
			show_next.addActionListener(this);

			solve = new Solve();
			add(solve);
			solve.setBounds(550, 500, 200, 80);
			solve.addActionListener(this);

			show_solution = new Show_Solution();
			add(show_solution);
			show_solution.setBounds(550, 590, 200, 80);
			show_solution.addActionListener(this);

		} else {
			finish = new Finish_Button();
			add(finish);
			finish.setBounds(550, 300, 200, 80);
			finish.addActionListener(this);
		}

		info = new Info();
		add(info);
		info.setBounds(30, 510, 500, 150);

	}

	/*
	 * if end of time (observer)
	 */
	@Override
	public void end_of_time() {

		lose();
		info.setText("End of time!");

	}
	/*
	 * Method for updating candidates after enter a number
	 */
	public void Candidates_Update(int number, int x, int y) {
		/*
		 * Updating candidates in row
		 */
		for (int i = x * NumberofBlocks; i < x * NumberofBlocks + NumberofBlocks; i++)
			Block[i].All_Candidates[number] = false;
		/*
		 * Updating candidates in column
		 */
		for (int i = y; i < NumberofBlocks * NumberofBlocks; i += NumberofBlocks)
			Block[i].All_Candidates[number] = false;
		/*
		 * Updating candidates in the block
		 */
		int first_block_row = x - x % block_height;
		int first_block_column = y - y % block_width;

		for (int j = 0; j < block_height; j++)
			for (int k = 0; k < block_width; k++)
				Block[(first_block_row + j) * NumberofBlocks + first_block_column + k].All_Candidates[number] = false;

	}
	/*
	 * If win
	 */
	public void win() {
		info.setText("<html>Congratulation! You solved this sudoku! <br/>" + timer.getText() + "</html>");
		finished = true;
		timer.off();
	}
	/*
	 * If lose
	 */
	public void lose() {
		info.setText("You've reahced limit of mistakes!");
		finished = true;
		timer.off();
	}

	/* Method Hidden_Single (one function for finding, one for returning)
	*
	*/
	public Hidden_Single HiddenSingle() {

		for (int i = 0; i < NumberofBlocks / block_height; i++) { // block row
			for (int j = 0; j < NumberofBlocks / block_width; j++) { // block column
				for (int k = 0; k < NumberofBlocks; k++) { // number

					int counter = 0;

					for (int l = 0; l < block_height; l++) { // row in block
						for (int m = 0; m < block_width; m++) { // column in block

							if (Block[i * NumberofBlocks * block_height + j * block_width + l * NumberofBlocks + m]
									.getEmpty()
									&& Block[i * NumberofBlocks * block_height + j * block_width + l * NumberofBlocks
											+ m].All_Candidates[k])
								counter++;
						}
					}
					if (counter == 1) {
						return HiddenSingle("block", 0, k, i * NumberofBlocks / block_width + j);
					}

				}
			}
		}

		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {

				int counter = 0;

				for (int k = 0; k < NumberofBlocks; k++) {

					if (Block[i * NumberofBlocks + k].getEmpty() && Block[i * NumberofBlocks + k].All_Candidates[j])
						counter++;
				}

				if (counter == 1)
					return HiddenSingle("row", i, j, 0);

			}
		}

		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {

				int counter = 0;

				for (int k = 0; k < NumberofBlocks; k++) {

					if (Block[i + k * NumberofBlocks].getEmpty() && Block[i + k * NumberofBlocks].All_Candidates[j])
						counter++;
				}

				if (counter == 1)
					return HiddenSingle("column", i, j, 0);

			}
		}

		return new Hidden_Single();
	}

	public Hidden_Single HiddenSingle(String place, int index, int number, int block) {

		Hidden_Single single = new Hidden_Single();

		if (place.equals("row")) {

			for (int i = index * NumberofBlocks; i < index * NumberofBlocks + NumberofBlocks; i++) {

				if (Block[i].getEmpty() && Block[i].All_Candidates[number] == true) {

					single.setHidden_Single("row", index, i % NumberofBlocks, number);
					return single;
				}
			}

		} else if (place.equals("column")) {

			for (int i = index; i < NumberofBlocks * NumberofBlocks; i += NumberofBlocks) {

				if (Block[i].getEmpty() && Block[i].All_Candidates[number] == true) {

					single.setHidden_Single("column", i / NumberofBlocks, index, number);
					return single;
				}
			}
		} else {

			int first_block_row = block - block % block_height;
			int first_block_column = (block % block_height) * block_width;

			for (int i = 0; i < block_height; i++) { // row in block
				for (int j = 0; j < block_width; j++) { // column in block

					if (Block[(first_block_row + i) * NumberofBlocks + first_block_column + j].getEmpty()
							&& Block[(first_block_row + i) * NumberofBlocks + first_block_column
									+ j].All_Candidates[number] == true) {

						single.setHidden_Single("block", first_block_row + i, first_block_column + j, number);
						return single;
					}

				}
			}
		}
		return single;
	}

	// Method Naked_Single (one function for finding, one for counting possible
	// digits)

	public Naked_Single NakedSingle() {

		int counter;
		Naked_Single single = new Naked_Single();

		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {

				counter = Count_Possible_Digits(i * NumberofBlocks + j);

				if (counter == 1 && Block[i * NumberofBlocks + j].getEmpty()) {

					for (int k = 0; k < NumberofBlocks; k++) {
						if (Block[i * NumberofBlocks + j].All_Candidates[k])
							single.setNaked_Single(i, j, k);
					}

					return single;
				}

			}
		}

		return single;
	}

	public int Count_Possible_Digits(int field) {

		int counter = 0;

		for (int i = 0; i < NumberofBlocks; i++) {

			if (Block[field].All_Candidates[i])
				counter++;
		}

		return counter;
	}

	public void next(String method, String place, int row, int column, int digit) {

		int block_index = row * NumberofBlocks + column;

		if (method.equals("Hidden single"))
			info.setText("<html>Hidden single in " + place + "</html>");

		else if (method.equals("Naked single"))
			info.setText("<html>Naked single in row " + row + " column " + column + "</html>");

		Block[block_index].setText(Integer.toString(digit));
		Block[block_index].setBackground(Color.green);

		Candidates_Update(digit - 1, row, column);

		Block[block_index].enabled = !Block[block_index].enabled;
		Block[block_index].empty = false;
		Block[block_index].setFont(new Font("Arial", Font.BOLD, 30));

		if (++counter == NumberofBlocks * NumberofBlocks)
			win();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		if (source == Candidate) {

			if (Candidate.on_off)
				Candidate.setText("Candidates off");

			else
				Candidate.setText("Candidates on");

			Candidate.on_off = !Candidate.on_off;

		} else if (source == hint) {

			if (!finished) {

				Hidden_Single if_hidden = HiddenSingle();

				if (if_hidden.getfound())
					info.setText("<html>Hidden single: Exist one field in "
							+ if_hidden.info_place_Hidden_Single(block_width, block_height) + "where number "
							+ if_hidden.digit + " can be written!</html>");

				else {

					Naked_Single if_naked = NakedSingle();

					if (if_naked.getfound())
						info.setText("<html>Naked single: Exist one field in row " + (if_naked.row + 1)
								+ " where there allow you to write only one number!" + "</html>");

					else
						info.setText("I didn't find any hint!");
				}
			}

		} else if (source == show_next) {

			if (!finished) {

				Hidden_Single if_hidden = HiddenSingle();

				if (if_hidden.getfound())
					next("Hidden single", if_hidden.info_place_Hidden_Single(block_width, block_height), if_hidden.row,
							if_hidden.column, if_hidden.digit);

				else {

					Naked_Single if_naked = NakedSingle();

					if (if_naked.getfound())
						next("Naked single", "", if_naked.row, if_naked.column, if_naked.digit);

					else
						info.setText("I didn't find any hint!");

				}
			}

		} else if (source == solve) {

			if (!finished) {

				do {
					Hidden_Single if_hidden = HiddenSingle();

					if (if_hidden.getfound())
						next("Hidden single", if_hidden.info_place_Hidden_Single(block_width, block_height),
								if_hidden.row, if_hidden.column, if_hidden.digit);

					else {

						Naked_Single if_naked = NakedSingle();

						if (if_naked.getfound())
							next("Naked single", "", if_naked.row, if_naked.column, if_naked.digit);

						else
							info.setText("I didn't find any hint!");

					}

				} while (!finished && info.getText() != "I didn't find any hint!");
			}
		} else if (source == finish) {

			Board game = null;

			if (this instanceof Sudoku_6x6)
				game = new Sudoku_6x6("play");
			else if (this instanceof Classic_Sudoku)
				game = new Classic_Sudoku("play");

			for (int i = 0; i < this.NumberofBlocks * this.NumberofBlocks; i++) {
				if (!Block[i].empty) {
					int digit = Integer.parseInt(Block[i].getText());
					digit--;
					game.Block[i].setText(Block[i].getText());
					game.Candidates_Update(digit, i / this.NumberofBlocks, i % this.NumberofBlocks);

					game.counter++;
					game.Block[i].empty = false;
					game.info.setText("Info");
					game.Block[i].setBackground(Color.white);
					game.Block[i].setFont(new Font("Arial", Font.BOLD, 30));
					game.Block[i].setForeground(Color.black);
					game.Block[i].enabled = !game.Block[i].enabled;
				}
			}

			game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			game.setVisible(true);
			dispose();
		} else if (source == show_solution) {
			if (index_in_file == -1)
				info.setText("I can't find solution in file!");
			else {

				Path input = Paths.get("./Boards.txt");

				List<String> pages = null;

				try {

					pages = Files.readAllLines(input, Charset.forName("UTF-8"));

				} catch (IOException e1) {

					e1.printStackTrace();
				}

				String[] load = pages.get(index_in_file + 1).split(" ");

				for (int i = 0; i < NumberofBlocks * NumberofBlocks; i++) {
					if (Block[i].empty) {

						Block[i].setText(load[i]);
						Block[i].setBackground(Color.blue);
						Block[i].setFont(new Font("Arial", Font.BOLD, 30));
						Block[i].setForeground(Color.black);
						Block[i].enabled = !Block[i].enabled;
					}

				}
				info.setText("Solution");
				finished = true;
				timer.off();
			}
		} else if (source == Bar.Main_Menu) {
			Main_menu menu = Main_menu.getInstance();
			;
			menu.setVisible(true);
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		} else if (source == Bar.Restart) {
			if (index_in_file == -1) {
				info.setText("I didnt find oryginal version of this sudoku!");
			} else {
				Board board;
				if (this instanceof Classic_Sudoku)
					board = new Classic_Sudoku("play");
				else
					board = new Sudoku_6x6("play");

				Path input = Paths.get("./Boards.txt");

				List<String> pages = null;

				try {

					pages = Files.readAllLines(input, Charset.forName("UTF-8"));

				} catch (IOException e1) {

					e1.printStackTrace();
				}

				String[] load = pages.get(index_in_file).split(" ");

				for (int i = 0; i < NumberofBlocks * NumberofBlocks; i++) {
					if (!load[i].equals("0")) {

						board.Block[i].setText(load[i]);
						board.Block[i].setBackground(Color.white);
						board.Block[i].setFont(new Font("Arial", Font.BOLD, 30));
						board.Block[i].setForeground(Color.black);
						board.Block[i].enabled = !Block[i].enabled;
						board.Block[i].empty = false;
						board.counter++;
						board.index_in_file = index_in_file;
						board.Candidates_Update(Integer.parseInt(load[i]) - 1, i / board.NumberofBlocks,
								i % board.NumberofBlocks);
					}

				}
				board.setVisible(true);
				board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		} else if (source == Bar.Save) {
			Saved_Sudoku save;
			if (this instanceof Classic_Sudoku)
				save = new Saved_Sudoku("Classic_Sudoku", this);
			else
				save = new Saved_Sudoku("Sudoku_6x6", this);

			save.setVisible(true);

		} else if (source == Bar.Load) {

			Load load = null;

			try {

				load = new Load(this);

			} catch (IOException e1) {

				e1.printStackTrace();

			}
			load.setVisible(true);
		}
	}
}
