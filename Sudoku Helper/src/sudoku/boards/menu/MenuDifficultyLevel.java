package sudoku.boards.menu;

import sudoku.boards.Board;
import sudoku.boards.ClassicSudoku;
import sudoku.boards.Sudoku6x6;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MenuDifficultyLevel extends JFrame implements ActionListener {

	@Serial
	private static final long serialVersionUID = -8191184212516234368L;

	private Board board;
	private final JButton easy;
	private final JButton normal;
	private final JButton hard;
	private final JComboBox<String> Type_of_Sudoku;

	private static MenuDifficultyLevel singleton_instance = null;

	private MenuDifficultyLevel() {

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

		Type_of_Sudoku = new JComboBox<>();
		Type_of_Sudoku.setBounds(70, 50, 140, 40);
		Type_of_Sudoku.addItem("Classic Sudoku");
		Type_of_Sudoku.addItem("Sudoku 6x6");
		add(Type_of_Sudoku);
	}
	/*
	 * singleton (only one instance)
	 */
	public static MenuDifficultyLevel getInstance() {
		if (singleton_instance == null)
			singleton_instance = new MenuDifficultyLevel();

		return singleton_instance;
	}
	/*
	 * load random board to play
	 */
	public void Loaded_Board(List<String> pages, String level, String type) {

		Random random = new Random();
		int amount = 2;
		int offset = random.nextInt(amount);
		/*
		 * number is multiplied by 2, because every board in the file has finished version
		 */
		offset = 2 * offset + 1;
		int index = 0;
		/*
		 * skip all boards that are not suitable for the player's choice
		 */
		while (!pages.get(index).equals(type))
			index++;

		while (!pages.get(index).equals(level))
			index++;

		index += offset;
		
		board.setIndex_in_file(index);

		String[] load = pages.get(index).split(" ");
		/*
		 * load a board
		 */
		for (int i = 0; i < load.length; i++) {

			int digit = Integer.parseInt(load[i]);

			if (digit > 0) {

				board.Candidates_Update(digit - 1, i / board.getNumberofBlocks(), i % board.getNumberofBlocks());
				board.IncreaseCounter();
				board.Block[i].setEmpty(false);
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

		String choice = Objects.requireNonNull(Type_of_Sudoku.getSelectedItem()).toString();

		if (choice.equals("Classic Sudoku")) {
			board = new ClassicSudoku("play");

		} else if (choice.equals("Sudoku 6x6")) {
			board = new Sudoku6x6("play");
		}

		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);

		Path input = Paths.get("./Boards.txt");
		List<String> pages = null;

		try {

			pages = Files.readAllLines(input, StandardCharsets.UTF_8);

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		if (source == easy) {
			assert pages != null;
			Loaded_Board(pages, "easy", choice);
		}

		if (source == normal) {

			assert pages != null;
			Loaded_Board(pages, "normal", choice);

		}

		if (source == hard) {
			assert pages != null;
			Loaded_Board(pages, "hard", choice);
		}
	}

}
