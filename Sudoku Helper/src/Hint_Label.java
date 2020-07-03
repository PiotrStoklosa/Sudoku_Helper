import java.awt.Font;

import javax.swing.JLabel;

public class Hint_Label extends JLabel {

	private static final long serialVersionUID = -4307241569119521160L;

	public Hint_Label() {
		
		super();
		setFont(new Font("Arial", Font.BOLD, 25));
		setText("Hint");
		
	}
}
