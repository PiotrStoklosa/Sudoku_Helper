
public class Hidden_Single {
	
	private boolean found, in_row, in_column, in_block;
	private int row;
	private int column;
	private int digit;
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getDigit() {
		return digit;
	}
	
	public boolean getfound() {
		return found;
	}
	
	public Hidden_Single(){
		found = false;
		in_row = false;
		in_column = false;
		in_block = false;
		row = 0;
		column = 0;
		digit = 0;
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
	
	String info_place_Hidden_Single(int block_width, int block_height) {

		if (in_row)
			return "row " + (row+1) + " ";

		if (in_column)
			return "column " + (column+1) + " ";
		
		int x_block = row - row % block_height;
		int y_block = (column - column%block_width)/block_width;
		
		if (in_block)
			return "block " + (x_block + y_block + 1) + " ";
		
		return "Blank single!";
	}
}
