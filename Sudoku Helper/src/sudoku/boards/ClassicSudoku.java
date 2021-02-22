package sudoku.boards;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class ClassicSudoku extends Board implements ActionListener, MouseListener {

	@Serial
	private static final long serialVersionUID = -5923595070988804248L;

	public ClassicSudoku(String role) {
		/*
		 * create the board 9 x 9 with blocks 3 x 3
		 */
		super(9, 3, 3, role);
		
		setCountertoNull();
		/*
		 * draw a board
		 */
		for (int i = 0; i < getNumberofBlocks(); i++)
			for (int j = 0; j < getNumberofBlocks(); j++) {

				Block[i * getNumberofBlocks() + j] = new SudokuBlock();
				Block[i * getNumberofBlocks() + j].addMouseListener(this);
				Block[i * getNumberofBlocks() + j].setOpaque(true);
				Block[i * getNumberofBlocks() + j].setHorizontalAlignment(JLabel.CENTER);
				Block[i * getNumberofBlocks() + j].setVerticalAlignment(JLabel.CENTER);
				Block[i * getNumberofBlocks() + j].setBackground(Color.white);

				setBorder_right(3);
				setBorder_left(3);
				setBorder_top(3);
				setBorder_bottom(3);
				/*
				 * set appropriate borders
				 */
				if (j == 2 || j == 5)
					setBorder_right(5);

				if (j == 3 || j == 6)
					setBorder_left(5);

				if (i == 2 || i == 5)
					setBorder_bottom(5);

				if (i == 3 || i == 6)
					setBorder_top(5);

				Block[i * getNumberofBlocks() + j].setBorder(BorderFactory.createMatteBorder(getBorder_top(), getBorder_left(),
						getBorder_bottom(), getBorder_right(), Color.BLACK));
				Block[i * getNumberofBlocks() + j].setBounds(50 + 50 * j, 50 + 50 * i, 50, 50);

				add(Block[i * getNumberofBlocks() + j]);

			}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		/*
		 * after a click you can select an appropriate number in the new window
		 */
		if (!isFinished()) {

			for (int i = 0; i < getNumberofBlocks(); i++)
				for (int j = 0; j < getNumberofBlocks(); j++) {

					if (source == Block[i * getNumberofBlocks() + j] && Block[i * getNumberofBlocks() + j].enabled
							&& SudokuBlock.counter) {

						SudokuBlock.counter = false;

						Block[i * getNumberofBlocks() + j].setBackground(new Color(0x83, 0xA3, 0x8C));

						NumberSelector select = new NumberSelector(i, j, this, "play");

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
