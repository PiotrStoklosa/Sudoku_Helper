import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

public class Sudoku_Block extends JLabel {
	boolean enabled = true;
	static boolean counter = true;
	Sudoku_Block(int X, int Y)
	{
		super();
		Sudoku_Block me = this;
		this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (enabled && counter)
            	{
            		counter = !counter;
            		me.setBackground(new Color(0x83, 0xA3, 0x8C));
            		Number_Selector select = new Number_Selector(X, Y, me);
            		select.addWindowListener(new WindowAdapter() {
            	        public void windowClosing(WindowEvent e) {
							me.setBackground(Color.white);
            	        	counter = !counter;
            	        }
            	    });
            		select.setVisible(true);
            	}
        		}
        });
	}
}
