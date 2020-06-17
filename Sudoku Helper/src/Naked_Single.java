
public class Naked_Single {
	boolean found;
	int row;
	int column;
	
	public Naked_Single(){
		found = false;
		row = 0;
		column = 0;
	}
	public boolean getfound() {
		return found;
	}
	void setNaked_Single(int row, int column) {
		this.row = row;
		this.column = column;
		found = true;
	}
}
