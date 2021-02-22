package sudoku.boards;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.Serial;

public class EditedSudoku6x6 extends Sudoku6x6 {

	@Serial
	private static final long serialVersionUID = 2223420529538466415L;

	public EditedSudoku6x6() {

		super("edit");

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		for (int i = 0; i < getNumberofBlocks(); i++)
			for (int j = 0; j < getNumberofBlocks(); j++) {

				if (source == Block[i * getNumberofBlocks() + j] && SudokuBlock.counter) {

					SudokuBlock.counter = false;

					Block[i * getNumberofBlocks() + j].setBackground(new Color(0x83, 0xA3, 0x8C));

					NumberSelector select = new NumberSelector(i, j, this, "edit");

					select.setVisible(true);
				}
			}
	}
}
