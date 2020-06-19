
public class Naked_Single {
	boolean found;
	int row;
	int column;
	int digit;

	public Naked_Single() {
		found = false;
		row = 0;
		column = 0;
	}

	public boolean getfound() {
		return found;
	}

	void setNaked_Single(int row, int column, int digit) {
		this.row = row;
		this.column = column;
		this.digit = digit+1;
		found = true;
	}
}
