import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Main_menu menu = Main_menu.getInstance();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}