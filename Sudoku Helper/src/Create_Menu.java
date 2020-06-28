import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Create_Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1562768394257804581L;

	JButton ClassicSudoku, Sudoku6x6;
	
	Create_Menu(){
		
		setTitle("Create menu");
		
		setFocusable(true);
		setSize(300, 300);
		setResizable(false);
		
		ClassicSudoku = new JButton("Classic");
		Sudoku6x6 = new JButton("6x6");
		
		ClassicSudoku.setBounds(100, 80, 80, 40);
		Sudoku6x6.setBounds(100, 130, 80, 40);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocation(850, 400);
		setLayout(null);
		
		add(ClassicSudoku);
		add(Sudoku6x6);

		ClassicSudoku.addActionListener(this);
		Sudoku6x6.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		Board board = null;
		
		if (source == ClassicSudoku) 
			board = new Edited_Classic_Sudoku();
			
		else if (source == Sudoku6x6)
			board = new Edited_Sudoku_6x6();
		
		board.setVisible(true);
		dispose();
	}
}
