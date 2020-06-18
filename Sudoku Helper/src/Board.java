import java.awt.Color;
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
		info.setBounds(50, 500, 600, 150);

		setLayout(null);
		Block = new Sudoku_Block[NumberofBlocks * NumberofBlocks];
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
				info.setText(
						"I found hidden single in row: " + if_hidden.row /* + " and column: " + if_naked.column */);
			} else {
				Naked_Single if_naked = NakedSingle();
				if (if_naked.getfound()) {
					info.setText(
							"I found naked single in row: " + if_naked.row /* + " and column: " + if_naked.column */);
				} else
					info.setText("I didn't found any hint!");
			}
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
				if (counter == 1 && (Block[i * 9 + j].getText() == "" || Block[i * 9 + j].getText().length() > 5)) {
					single.setNaked_Single(i + 1, j + 1);
					return single;
				}

			}
		}
		return single;
	}

	public Hidden_Single HiddenSingle(String place, int index, int number) {
		Hidden_Single single = new Hidden_Single();

		if (place.equals("row")) {
			for (int i = index * 9; i < index * 9 + 9; i++) {
				if (Block[i].All_Candidates[number] == true) {
					single.setHidden_Single(index + 1, i % 9 + 1);
					return single;
				}
			}

		} else if (place.equals("column")) {
			for (int i = index; i < NumberofBlocks * NumberofBlocks; i += NumberofBlocks) {
				if (Block[i].All_Candidates[number] == true) {
					single.setHidden_Single(i / 9 + 1, index + 1);
					return single;
				}
			}
			return single;
		} else {// blocks?

		}
		return single;
	}

	public Hidden_Single HiddenSingle() {
		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {
				int counter = 0;
				for (int k = 0; k < NumberofBlocks; k++) {
					if (Block[i * 9 + k].getText().length() > 5 && Block[i * 9 + k].All_Candidates[j])
						counter++;
				}

				if (counter == 1)
					return HiddenSingle("row", i, j);

			}
		}
		for (int i = 0; i < NumberofBlocks; i++) {
			for (int j = 0; j < NumberofBlocks; j++) {
				int counter = 0;
				for (int k = 0; k < NumberofBlocks; k++) {
					if (Block[i + k * 9].getText().length() > 5 && Block[i + k * 9].All_Candidates[j])
						counter++;
				}

				if (counter == 1)
					return HiddenSingle("column", i, j);

			}
		}
		// blocks?

		return new Hidden_Single();
	}
}
