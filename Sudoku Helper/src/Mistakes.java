import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Mistakes extends JLabel {

	private static final long serialVersionUID = -365624610982983246L;

	int Errors_limit;
	int Current_errors;
	boolean limit;

	private void Set_errors() {
		this.setText("<html>Mistakes <br/> " + Current_errors + " / " + Errors_limit + "</html>");
	}

	public Mistakes(int Errors) {

		limit = false;
		this.Errors_limit = Errors;
		Current_errors = 0;

		Set_errors();

		this.setFont(new Font("Arial", Font.BOLD, 30));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

	}

	void new_error() {

		Current_errors++;

		if (Errors_limit == Current_errors)
			limit = true;

		Set_errors();
	}
}