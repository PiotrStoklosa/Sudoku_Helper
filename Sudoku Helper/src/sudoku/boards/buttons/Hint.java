package sudoku.boards.buttons;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Hint extends JButton {

	@Serial
	private static final long serialVersionUID = -7767884746912273317L;

	public Hint() {
		setText("Hint");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);

	}
}
