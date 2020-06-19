import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Board extends JFrame implements ActionListener, Methods {
	int NumberofBlocks;
	Sudoku_Block[] Block;
	int border_right, border_left, border_top, border_bottom;
	Candidates Candidate;
	Hint hint;
	Info info;
	Mistakes Mistake;
	Show_next show_next;
	Solve solve;
	Board(int NumberofBlocks) {
		setFocusable(true);
		MenuBar Bar = new MenuBar();
		setJMenuBar(Bar);
		setSize(800, 800);
		setResizable(false);
		setLocation(500, 100);
		setTitle("Sudoku");
		this.NumberofBlocks = NumberofBlocks;

		Timer timer = new Timer();
		timer.setOpaque(true);
		timer.setBounds(550, 50, 200, 100);

		add(timer);
		Thread time = new Thread(timer);
		time.start();

		Mistake = new Mistakes(3);
		Mistake.setBounds(550, 160, 200, 100);
		add(Mistake);

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

		setLayout(null);
		Block = new Sudoku_Block[NumberofBlocks * NumberofBlocks];
	}
	
	public void Candidates_Update(int number, int x, int y) {
		for (int i = x * 9; i < x * 9 + 9; i++)
			Block[i].All_Candidates[number] = false;

		for (int i = y; i < 81; i += 9)
			Block[i].All_Candidates[number] = false;

		int first_block_row = x - x % 3;
		int first_block_column = y - y % 3;

		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 3; k++)
				Block[(first_block_row + j) * 9 + first_block_column + k].All_Candidates[number] = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == Candidate) {
			if (Candidate.on_off) {
				Candidate.setText("Candidates off");
			} else {
				Candidate.setText("Candidates on");
			}

			Candidate.on_off = !Candidate.on_off;
		} else if (source == hint) {

			Hidden_Single if_hidden = HiddenSingle();

			if (if_hidden.getfound()) {
				info.setText("<html>Hidden single: Exist one field in " + if_hidden.info_place_Hidden_Single()
						+ "where number " + if_hidden.digit + " can be written!</html>");
			} else {
				
				Naked_Single if_naked = NakedSingle();
				
				if (if_naked.getfound()) {
					info.setText("<html>Naked single: Exist one field in row " + if_naked.row
							+ " where there allow you to write only one number!" + "</html>");
				} else
					info.setText("I didn't find any hint!");
			}
		}
		else if (source == show_next) {
			
			Hidden_Single if_hidden = HiddenSingle();
			if (if_hidden.getfound()) {
				info.setText("<html>Hidden single in " + if_hidden.info_place_Hidden_Single() + "</html>");
				Block[if_hidden.row * NumberofBlocks + if_hidden.column].setText(Integer.toString(if_hidden.digit));
				Block[if_hidden.row * NumberofBlocks + if_hidden.column].setBackground(Color.green);
				Candidates_Update(if_hidden.digit-1, if_hidden.row, if_hidden.column);
				Block[if_hidden.row * NumberofBlocks + if_hidden.column].enabled = !Block[if_hidden.row * NumberofBlocks + if_hidden.column].enabled;
				Block[if_hidden.row * NumberofBlocks + if_hidden.column].empty=false;
				Block[if_hidden.row * NumberofBlocks + if_hidden.column].setFont(new Font("Arial", Font.BOLD, 30));
				
			}
			else {
				Naked_Single if_naked = NakedSingle();
				if (if_naked.getfound()) {
				info.setText("<html>Naked single in row " + if_naked.row + " column " + if_naked.column + "</html>");
				Block[if_naked.row * NumberofBlocks + if_naked.column].setText(Integer.toString(if_naked.digit));
				Block[if_naked.row * NumberofBlocks + if_naked.column].setBackground(Color.green);
				Candidates_Update(if_naked.digit-1, if_naked.row, if_naked.column);
				Block[if_naked.row * NumberofBlocks + if_naked.column].enabled = !Block[if_naked.row * NumberofBlocks + if_naked.column].enabled;
				Block[if_naked.row * NumberofBlocks + if_naked.column].empty=false;
				Block[if_naked.row * NumberofBlocks + if_naked.column].setFont(new Font("Arial", Font.BOLD, 30));
				} else
					info.setText("I didn't find any hint!");
			}
		}
		else if (source == solve) {
			
			do {
				Hidden_Single if_hidden = HiddenSingle();
				if (if_hidden.getfound()) {
					info.setText("<html>Hidden single in " + if_hidden.info_place_Hidden_Single() + "</html>");
					Block[if_hidden.row * NumberofBlocks + if_hidden.column].setText(Integer.toString(if_hidden.digit));
					Block[if_hidden.row * NumberofBlocks + if_hidden.column].setBackground(Color.green);
					Candidates_Update(if_hidden.digit-1, if_hidden.row, if_hidden.column);
					Block[if_hidden.row * NumberofBlocks + if_hidden.column].enabled = !Block[if_hidden.row * NumberofBlocks + if_hidden.column].enabled;
					Block[if_hidden.row * NumberofBlocks + if_hidden.column].empty=false;
					Block[if_hidden.row * NumberofBlocks + if_hidden.column].setFont(new Font("Arial", Font.BOLD, 30));
					
				}
				else {
					Naked_Single if_naked = NakedSingle();
					if (if_naked.getfound()) {
					info.setText("<html>Naked single in row " + if_naked.row + " column " + if_naked.column + "</html>");
					Block[if_naked.row * NumberofBlocks + if_naked.column].setText(Integer.toString(if_naked.digit));
					Block[if_naked.row * NumberofBlocks + if_naked.column].setBackground(Color.green);
					Candidates_Update(if_naked.digit-1, if_naked.row, if_naked.column);
					Block[if_naked.row * NumberofBlocks + if_naked.column].enabled = !Block[if_naked.row * NumberofBlocks + if_naked.column].enabled;
					Block[if_naked.row * NumberofBlocks + if_naked.column].empty=false;
					Block[if_naked.row * NumberofBlocks + if_naked.column].setFont(new Font("Arial", Font.BOLD, 30));
					} else
						info.setText("I didn't find any hint!");
				}
			} while (!info.getText().equals("I didn't find any hint!"));
		}
	}

	int Count_Possible_Digits(int field) {
		int counter = 0;
		for (int i = 0; i < NumberofBlocks; i++) {
			if (Block[field].All_Candidates[i])
				counter++;
		}
		return counter;
	}

	public Naked_Single NakedSingle() {
		int counter;
		Naked_Single single = new Naked_Single();
		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {
				counter = Count_Possible_Digits(i * 9 + j);
				if (counter == 1 && Block[i * 9 + j].getEmpty()) {
					for (int k=0; k<9; k++) {
						if (Block[i * 9 + j].All_Candidates[k]) 
							single.setNaked_Single(i, j, k);
					}
					
					return single;
				}

			}
		}
		return single;
	}

	public Hidden_Single HiddenSingle(String place, int index, int number, int block) {
		Hidden_Single single = new Hidden_Single();

		if (place.equals("row")) {
			for (int i = index * 9; i < index * 9 + 9; i++) {
				if (Block[i].getEmpty() && Block[i].All_Candidates[number] == true) {
					single.setHidden_Single("row", index, i % 9, number);
					return single;
				}
			}

		} else if (place.equals("column")) {
			for (int i = index; i < NumberofBlocks * NumberofBlocks; i += NumberofBlocks) {
				if (Block[i].getEmpty() && Block[i].All_Candidates[number] == true) {
					single.setHidden_Single("column", i / 9, index, number);
					return single;
				}
			}
		} else {
			int first_block_row = block - block%3;
			int first_block_column = (block % 3) * 3;
			for (int i = 0; i < 3; i++) { // row in block
				for (int j = 0; j < 3; j++) { // column in block
					if (Block[(first_block_row + i) * 9 + first_block_column + j].getEmpty() && Block[(first_block_row + i) * 9 + first_block_column + j].All_Candidates[number] == true) {
						single.setHidden_Single("block", first_block_row + i, first_block_column + j, number);
						return single;
					}
						
				}
			}
		}
		return single;
	}

	public Hidden_Single HiddenSingle() {
		
		for (int i = 0; i < 3; i++) { // block row
			for (int j = 0; j < 3; j++) { // block column
				for (int k = 0; k < 9; k++) { // number
					int counter = 0;
					for (int l = 0; l < 3; l++) { // row in block
						for (int m = 0; m < 3; m++) { // column in block
							if (Block[i*27 + j*3 + l*9 + m].getEmpty() && Block[i*27 + j*3 + l*9 + m].All_Candidates[k])
								counter++;
						}
					}
					if (counter == 1) {
						return HiddenSingle("block", 0, k, i*3 + j);
					}
							
						
				}
			}
		}
		
		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {
				int counter = 0;
				for (int k = 0; k < NumberofBlocks; k++) {
					if (Block[i * 9 + k].getEmpty() && Block[i * 9 + k].All_Candidates[j])
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
					if (Block[i + k * 9].getEmpty() && Block[i + k * 9].All_Candidates[j])
						counter++;
				}

				if (counter == 1)
					return HiddenSingle("column", i, j, 0);

			}
		}


		return new Hidden_Single();
	}
}
