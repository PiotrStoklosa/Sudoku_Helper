import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Finish_Button extends JButton{
	
	private static final long serialVersionUID = -3914075474559336161L;

	Finish_Button() {
		
		setText("Finish");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);

	}
}
