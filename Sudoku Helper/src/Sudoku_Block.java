import java.awt.Font;
import javax.swing.JLabel;

public class Sudoku_Block extends JLabel {

	private static final long serialVersionUID = -2042175369665055478L;
	
	protected boolean enabled = true;
	public static boolean counter = true;
	private boolean All_Candidates[];
	private boolean Player_Candidates[];
	private boolean empty;

	public boolean getEmpty() {
		return empty;
	}

	public boolean[] getPlayer_Candidates() {
		return Player_Candidates;
	}
	
	protected void setPlayer_Candidates_number(int number, boolean b) {
		Player_Candidates[number] = b;
	}

	public boolean[] getAll_Candidates() {
		return All_Candidates;
	}

	protected void setAll_Candidates_number(int number, boolean b) {
		All_Candidates[number] = b;
	}

	protected void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public Sudoku_Block(int X, int Y, int NumberofBlocks) {
		
		super();
		empty = true;
		All_Candidates = new boolean[9];
		Player_Candidates = new boolean[9];
		
		for (int i = 0; i < 9; i++) {
			All_Candidates[i] = true;
			Player_Candidates[i] = false;
		}
		/*
		 * Sudoku_Block stores string with candidates
		 */
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
	/*
	 * print new string with candidates
	 */
	public void print() {

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

}
