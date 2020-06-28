import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main_menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8386246453907190702L;
	
	JButton play, create, exit;

	Main_menu() {
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
	
		
		
		if (source == play) {
			Menu_Difficulty_Level menu = new Menu_Difficulty_Level("play");
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
		}
		
		if (source == create) {
			
			Create_Menu menu = new Create_Menu();
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setVisible(true);
			
		}
		

		dispose();
		
	}

}
