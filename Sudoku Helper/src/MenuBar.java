import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar{

	private static final long serialVersionUID = -6682723460371369300L;

	JMenu Options;

	JMenuItem Save, Load, Restart, Main_Menu;

	public MenuBar() {
		super();

		Options = new JMenu("Options");

		Save = new JMenuItem("Save");
		Load = new JMenuItem("Load");
		Restart = new JMenuItem("Restart");
		Main_Menu = new JMenuItem("Main menu");

		this.add(Options);
		
		Options.add(Save);
		Options.add(Load);
		Options.add(Restart);
		Options.add(Main_Menu);

	}

}
