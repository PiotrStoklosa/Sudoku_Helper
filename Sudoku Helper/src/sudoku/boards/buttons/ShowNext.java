package sudoku.boards.buttons;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ShowNext extends JButton{

	@Serial
	private static final long serialVersionUID = -741496727331836702L;

	public ShowNext(){
		super();
		setText("Show next");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
	}
}
