package sudoku.boards.buttons;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class FinishButton extends JButton{
	
	@Serial
	private static final long serialVersionUID = -3914075474559336161L;

	public FinishButton() {
		
		setText("Finish");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);

	}
}
