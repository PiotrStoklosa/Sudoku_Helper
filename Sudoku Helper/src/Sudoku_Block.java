import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

public class Sudoku_Block extends JLabel {
	public boolean enabled = true;
	public static boolean counter = true;
	boolean Candidates[];
	Sudoku_Block(int X, int Y, int NumberofBlocks)
	{
		super();
		Candidates = new boolean[NumberofBlocks];
		for (int i=0; i<NumberofBlocks; i++)
			Candidates[i] = true;
	}
}
