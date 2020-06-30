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
	String board = "";
	String type;
	String name;
	JTextField field;
	JLabel info;
	String minutes;
	String seconds;
	String mistakes;
	
	Saved_Sudoku(String type, Board board){
		
		for (int i=0; i< board.NumberofBlocks * board.NumberofBlocks; i++) {
			if (board.Block[i].empty) 
				this.board += "0 ";
			
			else {
				this.board += board.Block[i].getText();
				this.board+=" ";
			}
			
		}
		
		minutes = Integer.toString(board.timer.minutes);
		seconds = Integer.toString(board.timer.seconds);
		mistakes = Integer.toString(board.mistakes.Current_errors);
		
		/*if (board.timer.minutes < 10)
			minutes = '0' + Integer.toString(board.timer.minutes);
			
		if (board.timer.seconds < 10)
			seconds = '0' + Integer.toString(board.timer.minutes);*/
		
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
	
	String finished() {
		return name + " " + type + " " + minutes + " " + seconds + " " + mistakes + " " + board + '\n';
	}
	
}
