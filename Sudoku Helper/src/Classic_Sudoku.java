import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Classic_Sudoku extends Board implements ActionListener, MouseListener {

	private static final long serialVersionUID = -5923595070988804248L;

	Classic_Sudoku(String role) {

		super(9, 3, 3, role);

		counter = 0;

		for (int i = 0; i < NumberofBlocks; i++)
			for (int j = 0; j < NumberofBlocks; j++) {

				Block[i * NumberofBlocks + j] = new Sudoku_Block(50 + 50 * j, 50 + 50 * i, NumberofBlocks);
				Block[i * NumberofBlocks + j].addMouseListener(this);
				Block[i * NumberofBlocks + j].setOpaque(true);
				Block[i * NumberofBlocks + j].setHorizontalAlignment(JLabel.CENTER);
				Block[i * NumberofBlocks + j].setVerticalAlignment(JLabel.CENTER);
				Block[i * NumberofBlocks + j].setBackground(Color.white);

				border_right = 3;
				border_left = 3;
				border_top = 3;
				border_bottom = 3;
				// set appropriate borders to board

				if (j == 2 || j == 5)
					border_right = 5;

				if (j == 3 || j == 6)
					border_left = 5;

				if (i == 2 || i == 5)
					border_bottom = 5;

				if (i == 3 || i == 6)
					border_top = 5;

				Block[i * NumberofBlocks + j].setBorder(BorderFactory.createMatteBorder(border_top, border_left,
						border_bottom, border_right, Color.BLACK));
				Block[i * NumberofBlocks + j].setBounds(50 + 50 * j, 50 + 50 * i, 50, 50);

				add(Block[i * NumberofBlocks + j]);

			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		if (!finished) {

			for (int i = 0; i < NumberofBlocks; i++)
				for (int j = 0; j < NumberofBlocks; j++) {

					if (source == Block[i * NumberofBlocks + j] && Block[i * NumberofBlocks + j].enabled
							&& Sudoku_Block.counter) {

						Sudoku_Block.counter = !Sudoku_Block.counter;

						Block[i * NumberofBlocks + j].setBackground(new Color(0x83, 0xA3, 0x8C));

						Number_Selector select = new Number_Selector(i, j, this, "play");

						select.setVisible(true);
					}
				}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

}
