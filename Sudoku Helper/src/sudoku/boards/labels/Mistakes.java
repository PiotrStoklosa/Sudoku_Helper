package sudoku.boards.labels;

import java.awt.Color;
import java.awt.Font;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Mistakes extends JLabel {

	@Serial
	private static final long serialVersionUID = -365624610982983246L;

	private final int Errors_limit;
	private int Current_errors;
	private boolean limit;

	public int getCurrent_errors() {
		return Current_errors;
	}

	public boolean isLimit() {
		return limit;
	}
	/*
	 * set appropriate info about mistakes
	 */
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
	/*
	 * create a new error
	 */
	public void new_error() {

		Current_errors++;

		if (Errors_limit == Current_errors)
			limit = true;

		Set_errors();
	}

}