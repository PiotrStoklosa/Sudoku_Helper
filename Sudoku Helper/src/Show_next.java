import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Show_next extends JButton{

	private static final long serialVersionUID = -741496727331836702L;

	Show_next(){
		super();
		setText("Show next");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
	}
}
