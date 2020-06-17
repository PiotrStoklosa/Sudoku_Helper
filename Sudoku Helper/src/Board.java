import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Board extends JFrame implements ActionListener, Methods {
	int NumberofBlocks;
	JLabel[] Block;
	int border_right, border_left, border_top, border_bottom;
	Candidates Candidate;
	Hint hint;
	Hint_Label hint_label;
	
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

		Mistakes Mistake = new Mistakes(3);
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
		
		hint_label = new Hint_Label();
		add(hint_label);
		hint_label.setBounds(50, 500, 600, 150);
		
		setLayout(null);
		Block = new JLabel[NumberofBlocks * NumberofBlocks];
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
		}
		else if (source == hint) {
			 Naked_Single if_naked = NakedSingle();
			 if (if_naked.getfound()) {
				 hint_label.setText("I found naked single in row: " + if_naked.row + " and column: " + if_naked.column);
			 }
			 else
				 hint_label.setText("I didn't found any hint!");
		}
	}
	/*public
	boolean Can_I_write_number(int number, int row, int column) {
		
		if (!Can_I_write_number_in_box(number, row, column))
			return false;
		
		if (!Can_I_write_number_in_row(number, row))
			return false;
		
		if (!Can_I_write_number_in_column(number, column))
			return false;
		
		return true;
	}
	
	public
	boolean Can_I_write_number_in_box(int number) {
		boolean can = true;
		return can;
	}
	public
	boolean Can_I_write_number_in_row(int number, int row) {
		for (int i=0; )
		boolean can = true;
		return can;
	}
	public
	boolean Can_I_write_number_in_column(int number) {
		boolean can = true;
		return can;
	}
	@Override
	public
	Hidden_Single HiddenSingle() {
		Hidden_Single Single = new Hidden_Single();
		for (int i = 0; i < NumberofBlocks; i++) { // dla ka¿dej kolumny
			for (int j = 1; j <= NumberofBlocks; j++) { // dla ka¿dej cyfry
				for (int k=  0; k < NumberofBlocks; k++) { // dla ka¿dej cyfry w rzêdzie
					
				}
			
			}
		}
		return Single;
	}*/
	public
	int Count_Possible_Digits(int field) {
		int column = field % 9;
		int row = field/9;
		int counter = 9;
		int temp;

			
		for (int i=1; i<= 9; i++) {

			temp = counter;
			for (int j= row * 9; j< row * 9 + 9; j++) {
				if (Block[j].getText() != ""){
					if (Integer.parseInt(Block[j].getText()) == i){
						counter--;
						break;
					}
				}
			}
			if (temp == counter) {
				for (int j= column; j< NumberofBlocks * NumberofBlocks; j+=9) {
					if (Block[j].getText() != ""){
						if (Integer.parseInt(Block[j].getText()) == i){
							counter--;
							break;
						}
					}
				}
			}
			if (temp == counter) {
				int first_block_row = row - row % 3;
				int first_block_column = column - column % 3;
				
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						if (Block[(first_block_row + j)*9 + first_block_column + k].getText() != ""){
							if (Integer.parseInt(Block[(first_block_row + j)*9 + first_block_column + k].getText()) == i) {
								counter--;
								break;
							}
						}
					}
					if (temp != counter)
						break;
				}
			}
			
		}
		return counter;
	}
	
	public 
	Naked_Single NakedSingle() {
		int counter;
		Naked_Single single = new Naked_Single();
		for (int i=0; i<NumberofBlocks; i++) {
			for (int j=0; j< NumberofBlocks; j++) {
				counter = Count_Possible_Digits(i*9+j);
				if (counter == 1 && Block[i*9 + j].getText()=="") {
					single.setNaked_Single(i+1, j+1);
					return single;
				}
				
			}
		}
		return single;
	}
}
