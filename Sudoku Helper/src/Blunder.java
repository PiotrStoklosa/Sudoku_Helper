
public class Blunder {
	
	boolean correctness;
	boolean correctness_not_defined;
	private int x, y;

	Blunder() {
		correctness = true;
		correctness_not_defined = false;
	}

	Blunder Made_Blunder(int x, int y) {
		correctness = false;
		this.x = x;
		this.y = y;
		return this;
	}

	public boolean made_blunder() {
		return !correctness;
	}

	public int GetX() {
		return x;
	}

	public int GetY() {
		return y;
	}
	
	public void setcorrectness_not_defined() {
		correctness_not_defined = true;
	}
}
