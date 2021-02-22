package sudoku.boards.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame implements ActionListener {

	@Serial
	private static final long serialVersionUID = 8386246453907190702L;
	
	private final JButton play;
	private final JButton create;

	private static MainMenu singleton_instance = null;

	private MainMenu() {
		
		setFocusable(true);
		setSize(300, 300);
		setResizable(false);
		
		setTitle("Menu");
		
		play = new JButton("Play");
		create = new JButton("Create");
		JButton exit = new JButton("Exit");
		
		play.setBounds(100, 50, 80, 40);
		create.setBounds(100, 100, 80, 40);
		exit.setBounds(100, 150, 80, 40);
		
		setLocation(850, 400);
		setLayout(null);
		
		add(play);
		add(create);
		add(exit);
		
		play.addActionListener(this);
		create.addActionListener(this);
		exit.addActionListener(this);
		
	}
	/*
	 * singleton (only one instance)
	 */
	public static MainMenu getInstance() {
	      if (singleton_instance == null)
	    	  singleton_instance = new MainMenu();
	      
	      return singleton_instance;
	   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
	
		
		
		if (source == play) {
			MenuDifficultyLevel menu = MenuDifficultyLevel.getInstance();
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
		}
		
		if (source == create) {
			
			CreateMenu menu = CreateMenu.getInstance();
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
			
		}
		

		dispose();
		
	}

}
