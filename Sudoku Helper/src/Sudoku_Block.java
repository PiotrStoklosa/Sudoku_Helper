import java.awt.Font;
import javax.swing.JLabel;

public class Sudoku_Block extends JLabel {

	private static final long serialVersionUID = -2042175369665055478L;
	
	public boolean enabled = true;
	public static boolean counter = true;
	boolean All_Candidates[];
	boolean Player_Candidates[];
	boolean empty;

	Sudoku_Block(int X, int Y, int NumberofBlocks) {
		super();
		empty = true;
		All_Candidates = new boolean[9];
		Player_Candidates = new boolean[9];
		for (int i = 0; i < 9; i++) {
			All_Candidates[i] = true;
			Player_Candidates[i] = false;
		}

		StringBuilder Candidates = new StringBuilder();
		Candidates.append("<html> <font color='#FFFFFF'>");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Candidates.append("  " + (i * 3 + j + 1) + "  ");
			}
			Candidates.append("<br/>");
		}
		Candidates.append("</font></html>");
		setText(Candidates.toString());
	}

	void print() {
		setFont(new Font("Arial", Font.BOLD, 12));
		StringBuilder Candidates = new StringBuilder("<html>");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!Player_Candidates[i * 3 + j])
					Candidates.append("<font color='#FFFFFF'>");

				Candidates.append("  " + (i * 3 + j + 1) + "  ");
				if (!Player_Candidates[i * 3 + j])
					Candidates.append("</font>");
			}
			Candidates.append("<br/>");
		}
		Candidates.append("</html>");
		setText(Candidates.toString());

	}
	boolean getEmpty() {
		return empty;
	}
}
