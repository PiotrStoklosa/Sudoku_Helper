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

		setCountertoNull();

		for (int i = 0; i < getNumberofBlocks(); i++)
			for (int j = 0; j < getNumberofBlocks(); j++) {

				Block[i * getNumberofBlocks() + j] = new Sudoku_Block(50 + 50 * j, 50 + 50 * i, getNumberofBlocks());
				Block[i * getNumberofBlocks() + j].addMouseListener(this);
				Block[i * getNumberofBlocks() + j].setOpaque(true);
				Block[i * getNumberofBlocks() + j].setHorizontalAlignment(JLabel.CENTER);
				Block[i * getNumberofBlocks() + j].setVerticalAlignment(JLabel.CENTER);
				Block[i * getNumberofBlocks() + j].setBackground(Color.white);

				setBorder_right(3);
				setBorder_left(3);
				setBorder_top(3);
				setBorder_bottom(3);
				// set appropriate borders to board

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
		if (!isFinished()) {

			for (int i = 0; i < getNumberofBlocks(); i++)
				for (int j = 0; j < getNumberofBlocks(); j++) {

					if (source == Block[i * getNumberofBlocks() + j] && Block[i * getNumberofBlocks() + j].enabled
							&& Sudoku_Block.counter) {

						Sudoku_Block.counter = !Sudoku_Block.counter;

						Block[i * getNumberofBlocks() + j].setBackground(new Color(0x83, 0xA3, 0x8C));

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
