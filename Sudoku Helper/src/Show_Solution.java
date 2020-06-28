import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Show_Solution extends JButton{
	
	private static final long serialVersionUID = -9022316516627339811L;

	Show_Solution() {
		
		super();
		setText("Show solution");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
		
	}
}
