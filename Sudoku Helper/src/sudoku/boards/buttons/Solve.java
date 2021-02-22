package sudoku.boards.buttons;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Solve extends JButton{

	@Serial
	private static final long serialVersionUID = -700269828484033561L;

	public Solve(){
		super();
		setText("Solve");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
	}
}
