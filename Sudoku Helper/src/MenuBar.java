import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	JMenu Options, Help, Info;

	JMenuItem Save, Load, Restart, Main_Menu;
	JMenuItem What_is_sudoku, How_to_play, Methods, More;
	JMenuItem About_application, About_author;

	public MenuBar() {
		super();

		Options = new JMenu("Options");
		Help = new JMenu("Help");
		Info = new JMenu("Info");

		Save = new JMenuItem("Save");
		Load = new JMenuItem("Load");
		Restart = new JMenuItem("Restart");
		Main_Menu = new JMenuItem("Main menu");

		this.add(Options);
		this.add(Help);
		this.add(Info);

		Options.add(Save);
		Options.add(Load);
		Options.add(Restart);
		Options.add(Main_Menu);

		What_is_sudoku = new JMenuItem("What is sudoku?");
		How_to_play = new JMenuItem("How to play?");
		Methods = new JMenuItem("Methods");
		More = new JMenuItem("More");

		Help.add(What_is_sudoku);
		Help.add(How_to_play);
		Help.add(Methods);
		Help.add(More);

		About_application = new JMenuItem("About application");
		About_author = new JMenuItem("About author");

		Info.add(About_application);
		Info.add(About_author);
	}
}
