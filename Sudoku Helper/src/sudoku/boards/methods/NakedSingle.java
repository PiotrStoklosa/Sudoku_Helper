package sudoku.boards.methods;

public class NakedSingle {
	public boolean found;
	public int row;
	public int column;
	public int digit;

	public NakedSingle() {
		found = false;
		row = 0;
		column = 0;
	}

	public boolean getfound() {
		return found;
	}

	public void setNaked_Single(int row, int column, int digit) {
		this.row = row;
		this.column = column;
		this.digit = digit+1;
		found = true;
	}
}
