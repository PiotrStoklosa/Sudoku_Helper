
public class Hidden_Single {
	boolean found, in_row, in_column, in_block;
	int row;
	int column;
	int digit;
	
	public Hidden_Single(){
		found = false;
		in_row = false;
		in_column = false;
		in_block = false;
		row = 0;
		column = 0;
		digit = 0;
	}
	public boolean getfound() {
		return found;
	}
	void setHidden_Single(int row, int column) {
		this.row = row;
		this.column = column;
		found = true;
	}
}
