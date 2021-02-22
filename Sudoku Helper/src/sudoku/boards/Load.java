package sudoku.boards;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Load extends JFrame {

	@Serial
	private static final long serialVersionUID = 1260906524026586088L;

	private final JList<String> load;

	public Load(Board board) throws IOException {

		JPanel panel = new JPanel();
		DefaultListModel<String> model = new DefaultListModel<>();
		load = new JList<>();
		setTitle("Load");

		load.setModel(model);
		panel.add(new JScrollPane(load));
		setSize(200, 200);
		setLocationRelativeTo(null);

		add(panel);
		/*
		 * load all saves
		 */
		Path input = Paths.get("./Saves.txt");
		final List<String> pages = Files.readAllLines(input, StandardCharsets.UTF_8);
		/*
		 * add all saves to list
		 */
		for (String page : pages) model.addElement(page.split(" ")[0]);
		
		load.addListSelectionListener(e -> {

			Board loaded_sudoku;
			String[] loaded_board = pages.get(load.getSelectedIndex()).split(" ");
			String type = loaded_board[1];
			/*
			 * load each element to board (time, mistakes, numbers)
			 */
			if (type.equals("ClassicSudoku"))
				loaded_sudoku = new ClassicSudoku("play");
			else
				loaded_sudoku = new Sudoku6x6("play");

			int minutes = Integer.parseInt(loaded_board[2]);
			int seconds = Integer.parseInt(loaded_board[3]);
			int mistakes = Integer.parseInt(loaded_board[4]);

			loaded_sudoku.timer.setSeconds(seconds);
			loaded_sudoku.timer.setMinutes(minutes);
			for (int i=0; i<mistakes; i++)
				loaded_sudoku.mistakes.new_error();

			for (int i=0; i< loaded_sudoku.getNumberofBlocks() * loaded_sudoku.getNumberofBlocks(); i++)
				if (!loaded_board[i+5].equals("0")) {
					loaded_sudoku.Block[i].setText(loaded_board[i+5]);
					loaded_sudoku.Block[i].setBackground(Color.white);
					loaded_sudoku.Block[i].setFont(new Font("Arial", Font.BOLD, 30));
					loaded_sudoku.Block[i].setForeground(Color.black);
					loaded_sudoku.Block[i].enabled = !loaded_sudoku.Block[i].enabled;
					loaded_sudoku.Block[i].setEmpty(false);
					loaded_sudoku.IncreaseCounter();
					loaded_sudoku.Candidates_Update(Integer.parseInt(loaded_board[i+5]) - 1, i / loaded_sudoku.getNumberofBlocks(),
							i % board.getNumberofBlocks());
				}
			loaded_sudoku.setVisible(true);
			loaded_sudoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			board.dispose();
			dispose();
		});
	}
}
