import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Number_Selector extends JFrame implements ActionListener, KeyListener {
	JButton[] Numbers;
	Sudoku_Block buttoninstance;

	Number_Selector(int XLayout, int YLayout, Sudoku_Block block) {
		addKeyListener(this);
		setFocusable(true);
		buttoninstance = block;
		setSize(200, 235);
		setResizable(false);
		setLocation(XLayout + 600, YLayout + 50);
		setLayout(null);
		setTitle("Select number");
		Numbers = new JButton[9];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				Numbers[j * 3 + i] = new JButton(Integer.toString(j * 3 + i + 1));
				Numbers[j * 3 + i].setBounds(20 + 50 * i, 20 + 50 * j, 41, 41);
				add(Numbers[j * 3 + i]);
				Numbers[j * 3 + i].addActionListener(this);
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		for (int i = 0; i < 9; i++) {
			if (source == Numbers[i]) {
				buttoninstance.setBackground(Color.white);
				buttoninstance.setText(Integer.toString(i + 1));
				buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
				buttoninstance.setForeground(Color.black);
				Sudoku_Block.counter = !Sudoku_Block.counter;
				buttoninstance.enabled = !buttoninstance.enabled;
				dispose();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if (c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9') {
			int chr = c;
			chr-=48;
			buttoninstance.setBackground(Color.white);
			buttoninstance.setText(Integer.toString(chr));
			buttoninstance.setFont(new Font("Arial", Font.BOLD, 30));
			buttoninstance.setForeground(Color.black);
			Sudoku_Block.counter = !Sudoku_Block.counter;
			buttoninstance.enabled = !buttoninstance.enabled;
			dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {		
	}

}
