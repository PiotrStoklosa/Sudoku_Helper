import java.awt.Color;
import java.awt.event.MouseEvent;

public class Edited_Classic_Sudoku extends Classic_Sudoku{

	private static final long serialVersionUID = 2223420529538466415L;

	Edited_Classic_Sudoku(){
		
		super("edit");
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		Object source = e.getSource();
		if (!finished) {

			for (int i = 0; i < NumberofBlocks; i++)
				for (int j = 0; j < NumberofBlocks; j++) {

					if (source == Block[i * NumberofBlocks + j] && Sudoku_Block.counter) {

						Sudoku_Block.counter = !Sudoku_Block.counter;

						Block[i * NumberofBlocks + j].setBackground(new Color(0x83, 0xA3, 0x8C));

						Number_Selector select = new Number_Selector(i, j, this, "edit");

						select.setVisible(true);
					}
				}
		}
	}
}
