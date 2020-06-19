
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
	void setHidden_Single(String place, int row, int column, int number) {
		if (place.equals("row"))
			in_row = true;
		if (place.equals("column"))
			in_column = true;
		if (place.equals("block"))
			in_block = true;
		
		digit = number + 1;
		this.row = row;
		this.column = column;
		found = true;
	}
	String info_place_Hidden_Single() {
		if (in_row)
			return "row " + (row+1) + " ";
		if (in_column)
			return "column " + (column+1) + " ";
		int x_block = row - row%3;
		int y_block = (column - column%3)/3;
		if (in_block)
			return "block " + (x_block + y_block + 1) + " ";
		return "Blank single!";
	}
}
