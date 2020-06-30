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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Menu_Difficulty_Level extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8191184212516234368L;

	Board board;
	JButton easy, normal, hard;
	int amount = 2;
	String role;
	JComboBox<String> Type_of_Sudoku;

	Menu_Difficulty_Level(String role) {

		this.role = role;
		setFocusable(true);
		setSize(300, 300);
		setResizable(false);

		setTitle("Menu");

		easy = new JButton("easy");
		normal = new JButton("normal");
		hard = new JButton("hard");

		easy.setBounds(100, 100, 80, 40);
		normal.setBounds(100, 150, 80, 40);
		hard.setBounds(100, 200, 80, 40);

		setLocation(850, 400);
		setLayout(null);

		add(easy);
		add(normal);
		add(hard);

		easy.addActionListener(this);
		normal.addActionListener(this);
		hard.addActionListener(this);

		Type_of_Sudoku = new JComboBox<String>();
		Type_of_Sudoku.setBounds(70, 50, 140, 40);
		Type_of_Sudoku.addItem("Classic Sudoku");
		Type_of_Sudoku.addItem("Sudoku 6x6");
		add(Type_of_Sudoku);
	}

	public void Loaded_Board(List<String> pages, String level, String type) {

		Random random = new Random();
		int offset = random.nextInt(amount);
		
		offset = 2 * offset + 1;
		int index = 0;
		while (!pages.get(index).equals(type))
			index++;

		while (!pages.get(index).equals(level))
			index++;

		index += offset;

		board.index_in_file = index;
		
		String[] load = pages.get(index).split(" ");

		for (int i = 0; i < load.length; i++) {

			int digit = Integer.parseInt(load[i]);

			if (digit > 0) {

				board.Candidates_Update(digit - 1, i / board.NumberofBlocks, i % board.NumberofBlocks);
				board.counter++;
				board.Block[i].empty = false;
				board.info.setText("Info");
				board.Block[i].setBackground(Color.white);
				board.Block[i].setText(Integer.toString(digit));
				board.Block[i].setFont(new Font("Arial", Font.BOLD, 30));
				board.Block[i].setForeground(Color.black);
				board.Block[i].enabled = false;

			}
		}
		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		String choice = Type_of_Sudoku.getSelectedItem().toString();
		
			if (choice == "Classic Sudoku") {
				board = new Classic_Sudoku("play");
				
			} else if (choice == "Sudoku 6x6") {
				board = new Sudoku_6x6("play");
			}

		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);

		if (role.contentEquals("play")) {

			Path input = Paths.get("./Boards.txt");
			List<String> pages = null;

			try {

				pages = Files.readAllLines(input, Charset.forName("UTF-8"));

			} catch (IOException e1) {

				e1.printStackTrace();
			}

			if (source == easy) {
				Loaded_Board(pages, "easy", choice);
			}

			if (source == normal) {

				Loaded_Board(pages, "normal", choice);

			}

			if (source == hard) {
				Loaded_Board(pages, "hard", choice);
			}
		}
	}

}
