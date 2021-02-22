package sudoku.boards.buttons;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Candidates extends JButton {

	@Serial
	private static final long serialVersionUID = -841279372899433310L;

	public boolean on_off = false;

	public Candidates() {
		
		super();
		
		setText("Candidates off");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
		
	}
}
