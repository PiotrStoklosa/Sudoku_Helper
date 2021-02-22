package sudoku.boards.menu;

import sudoku.boards.Board;
import sudoku.boards.EditedClassicSudoku;
import sudoku.boards.EditedSudoku6x6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;

public class CreateMenu extends JFrame implements ActionListener{

	@Serial
	private static final long serialVersionUID = 1562768394257804581L;

	private final JButton ClassicSudoku;
	private final JButton Sudoku6x6;
	
	private static CreateMenu singleton_instance = null;
	
	private CreateMenu(){
		
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
	/*
	 * singleton (only one instance)
	 */
	public static CreateMenu getInstance() {
	      if (singleton_instance == null)
	    	  singleton_instance = new CreateMenu();
	      
	      return singleton_instance;
	   }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		Board board = null;
		
		if (source == ClassicSudoku) 
			board = new EditedClassicSudoku();
			
		else if (source == Sudoku6x6)
			board = new EditedSudoku6x6();

		assert board != null;
		board.setVisible(true);
		dispose();
	}
}
