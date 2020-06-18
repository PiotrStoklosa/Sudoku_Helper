import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Hint extends JButton {
	Hint() {
		setText("Hint");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);

	}
}
