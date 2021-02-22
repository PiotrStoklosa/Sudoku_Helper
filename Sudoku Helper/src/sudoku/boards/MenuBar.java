package sudoku.boards;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.io.Serial;

public class MenuBar extends JMenuBar{

	@Serial
	private static final long serialVersionUID = -6682723460371369300L;

	protected JMenuItem Save, Load, Restart, Main_Menu;

	public MenuBar() {
		super();

		JMenu options = new JMenu("Options");

		Save = new JMenuItem("Save");
		Load = new JMenuItem("Load");
		Restart = new JMenuItem("Restart");
		Main_Menu = new JMenuItem("Main menu");

		this.add(options);
		
		options.add(Save);
		options.add(Load);
		options.add(Restart);
		options.add(Main_Menu);

	}

}
