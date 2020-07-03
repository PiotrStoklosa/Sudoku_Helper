import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Saved_Sudoku extends JFrame implements ActionListener{

	private static final long serialVersionUID = -6291472736114097561L;
	private String board = "";
	private String type;
	private String name;
	private JTextField field;
	private JLabel info;
	private String minutes;
	private String seconds;
	private String mistakes;
	
	public Saved_Sudoku(String type, Board board){
		/*
		 * create a string to save board
		 */
		for (int i=0; i< board.getNumberofBlocks() * board.getNumberofBlocks(); i++) {
			if (board.Block[i].getEmpty()) 
				this.board += "0 ";
			
			else {
				this.board += board.Block[i].getText();
				this.board+=" ";
			}
			
		}
		
		minutes = Integer.toString(board.timer.getMinutes());
		seconds = Integer.toString(board.timer.getSeconds());
		mistakes = Integer.toString(board.mistakes.getCurrent_errors());
		
		
		this.type = type;
		
		setLayout(null);
		setFocusable(true);
		setBounds(700, 700, 300, 300);
		setResizable(false);
		
		
		setTitle("Save");
		
		info = new JLabel("Your save");
		info.setBounds(100, 20, 80, 40);
		add(info);
		
		field = new JTextField();
		field.setBounds(100, 100, 80, 40);
		field.addActionListener(this);
		add(field);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * load all saves and add new one
		 */
		name = field.getText();
		
		String save = finished();
		
		Path input = Paths.get("./Saves.txt");
		
		String saves = null;
		
		try {
			
			saves = Files.readString(input);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		saves += save;
		
		Path output = Paths.get("./saves.txt");
		try {
			
			Files.write(output,saves.getBytes(Charset.forName("UTF-8")));
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
			
		}
		
		dispose();
	}
	/*
	 * form of save board
	 */
	String finished() {
		return name + " " + type + " " + minutes + " " + seconds + " " + mistakes + " " + board + '\n';
	}
	
}
