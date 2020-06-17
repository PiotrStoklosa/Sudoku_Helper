import java.awt.Font;

import javax.swing.JLabel;

public class Info extends JLabel {
	Info(){
		super();
		setFont(new Font("Arial", Font.BOLD, 25));
		setText("Hint");
	}
}
