import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main_menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8386246453907190702L;
	
	JButton play, create, exit;
	
	private static Main_menu singleton_instance = null;

	private Main_menu() {
		
		setFocusable(true);
		setSize(300, 300);
		setResizable(false);
		
		setTitle("Menu");
		
		play = new JButton("Play");
		create = new JButton("Create");
		exit = new JButton("Exit");
		
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

	public static Main_menu getInstance() {
	      if (singleton_instance == null)
	    	  singleton_instance = new Main_menu();
	      
	      return singleton_instance;
	   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
	
		
		
		if (source == play) {
			Menu_Difficulty_Level menu = Menu_Difficulty_Level.getInstance();
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
		}
		
		if (source == create) {
			
			Create_Menu menu = Create_Menu.getInstance();
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
			
		}
		

		dispose();
		
	}

}
