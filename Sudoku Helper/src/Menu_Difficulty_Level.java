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
import javax.swing.JFrame;

public class Menu_Difficulty_Level extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8191184212516234368L;

	Board board;
	JButton easy, normal, hard;
	int amount = 2;

	Menu_Difficulty_Level() {

		setFocusable(true);
		setSize(300, 300);
		setResizable(false);

		setTitle("Menu");

		easy = new JButton("easy");
		normal = new JButton("normal");
		hard = new JButton("hard");

		easy.setBounds(100, 50, 80, 40);
		normal.setBounds(100, 100, 80, 40);
		hard.setBounds(100, 150, 80, 40);

		setLocation(850, 400);
		setLayout(null);

		add(easy);
		add(normal);
		add(hard);

		easy.addActionListener(this);
		normal.addActionListener(this);
		hard.addActionListener(this);

	}

	public void Loaded_Board(List<String> pages, String level) {
		
		Random random = new Random();
		int offset = random.nextInt(amount);

		offset++;
		int index = 0;
		
		while (!pages.get(index).equals(level))
			index++;

		index += offset;
		
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
				
			}
		}

		dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		board = new Classic_Sudoku();
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);

		Path input = Paths.get("./Boards.txt");
		List<String> pages = null;

		try {

			pages = Files.readAllLines(input, Charset.forName("UTF-8"));

		} catch (IOException e1) {

			e1.printStackTrace();
		}

		if (source == easy) {
			Loaded_Board(pages, "easy");
		}

		if (source == normal) {

			Loaded_Board(pages, "normal");

		}

		if (source == hard) {
			Loaded_Board(pages, "hard");
		}

	}

}