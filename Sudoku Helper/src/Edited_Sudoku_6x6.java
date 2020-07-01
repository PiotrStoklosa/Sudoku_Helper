import java.awt.Color;
import java.awt.event.MouseEvent;

public class Edited_Sudoku_6x6 extends Sudoku_6x6 {

	private static final long serialVersionUID = 2223420529538466415L;

	Edited_Sudoku_6x6() {

		super("edit");

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		for (int i = 0; i < getNumberofBlocks(); i++)
			for (int j = 0; j < getNumberofBlocks(); j++) {

				if (source == Block[i * getNumberofBlocks() + j] && Sudoku_Block.counter) {

					Sudoku_Block.counter = !Sudoku_Block.counter;

					Block[i * getNumberofBlocks() + j].setBackground(new Color(0x83, 0xA3, 0x8C));

					Number_Selector select = new Number_Selector(i, j, this, "edit");

					select.setVisible(true);
				}
			}
	}
}
