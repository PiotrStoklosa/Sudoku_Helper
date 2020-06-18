import java.awt.Font;

import javax.swing.JLabel;

public class Hint_Label extends JLabel {
	Hint_Label() {
		super();
		setFont(new Font("Arial", Font.BOLD, 25));
		setText("Hint");
	}
}
