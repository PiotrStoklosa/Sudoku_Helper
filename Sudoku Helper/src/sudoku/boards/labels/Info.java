package sudoku.boards.labels;

import java.awt.Font;
import java.io.Serial;

import javax.swing.JLabel;

public class Info extends JLabel {

	@Serial
	private static final long serialVersionUID = -1141092461195393072L;

	public Info() {
		
		super();
		setFont(new Font("Arial", Font.BOLD, 25));
		setText("Info");
		
	}
}
