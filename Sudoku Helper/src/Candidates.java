import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Candidates extends JButton {

	boolean on_off = false;

	Candidates() {
		super();
		setText("Candidates off");
		setFont(new Font("Arial", Font.BOLD, 25));
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));
		setFocusPainted(false);
	}
}
