import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public abstract class Board extends JFrame implements ActionListener, Methods {

	private static final long serialVersionUID = 2589826376003918979L;

	int NumberofBlocks;
	int block_width, block_height;
	int border_right, border_left, border_top, border_bottom;
	int counter;
	boolean finished;

	Sudoku_Block[] Block;

	Candidates Candidate;
	Hint hint;
	Info info;
	Mistakes mistakes;
	Show_next show_next;
	Solve solve;
	Timer timer;

	Board(int NumberofBlocks, int block_width, int block_height) {

		setLayout(null);
		setFocusable(true);
		finished = false;

		Block = new Sudoku_Block[NumberofBlocks * NumberofBlocks];
		this.NumberofBlocks = NumberofBlocks;
		this.block_width = block_width;
		this.block_height = block_height;

		MenuBar Bar = new MenuBar();
		setJMenuBar(Bar);
		setSize(800, 800);
		setResizable(false);
		setLocation(500, 100);
		setTitle("Sudoku");

		timer = new Timer();
		timer.setOpaque(true);
		timer.setBounds(550, 50, 200, 100);

		add(timer);
		Thread time = new Thread(timer);
		time.start();

		mistakes = new Mistakes(3);
		mistakes.setBounds(550, 160, 200, 100);
		add(mistakes);

		Candidate = new Candidates();
		Candidate.setBounds(550, 270, 200, 100);
		add(Candidate);
		Candidate.addActionListener(this);

		hint = new Hint();
		add(hint);
		hint.setBounds(550, 380, 200, 100);
		hint.addActionListener(this);

		info = new Info();
		add(info);
		info.setBounds(30, 510, 500, 150);

		show_next = new Show_next();
		add(show_next);
		show_next.setBounds(550, 490, 200, 100);
		show_next.addActionListener(this);

		solve = new Solve();
		add(solve);
		solve.setBounds(550, 600, 200, 100);
		solve.addActionListener(this);

	}

	public void Candidates_Update(int number, int x, int y) {

		for (int i = x * NumberofBlocks; i < x * NumberofBlocks + NumberofBlocks; i++)
			Block[i].All_Candidates[number] = false;

		for (int i = y; i < NumberofBlocks * NumberofBlocks; i += NumberofBlocks)
			Block[i].All_Candidates[number] = false;

		int first_block_row = x - x % block_height;
		int first_block_column = y - y % block_width;

		for (int j = 0; j < block_height; j++)
			for (int k = 0; k < block_width; k++)
				Block[(first_block_row + j) * NumberofBlocks + first_block_column + k].All_Candidates[number] = false;

	}

	public void win() {
		info.setText("<html>Congratulation! You solved this sudoku! <br/>" + timer.getText() + "</html>");
		finished = true;
		timer.off();
	}
	
	public void lose() {
		info.setText("You've reahced limit of mistakes!");
		finished = true;
		timer.off();
	}

	// Method Hidden_Single (one function for finding, one for returning)

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
					info.setText("<html>Hidden single: Exist one field in " + if_hidden.info_place_Hidden_Single(block_width, block_height)
							+ "where number " + if_hidden.digit + " can be written!</html>");

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
					next("Hidden single", if_hidden.info_place_Hidden_Single(block_width, block_height), if_hidden.row, if_hidden.column,
							if_hidden.digit);

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
						next("Hidden single", if_hidden.info_place_Hidden_Single(block_width, block_height), if_hidden.row, if_hidden.column,
								if_hidden.digit);

					else {

						Naked_Single if_naked = NakedSingle();

						if (if_naked.getfound())
							next("Naked single", "", if_naked.row, if_naked.column, if_naked.digit);

						else
							info.setText("I didn't find any hint!");

					}

				} while (!finished && info.getText() != "I didn't find any hint!");
			}
		}
	}
}
