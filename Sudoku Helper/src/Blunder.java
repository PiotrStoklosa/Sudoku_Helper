public class Blunder {
	
	private boolean correctness;
	private boolean correctness_not_defined;
	private int x, y;

	public Blunder() {
		
		correctness = true;
		correctness_not_defined = false;
		
	}

	public Blunder Made_Blunder(int x, int y) {
		
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

	public boolean isCorrectness_not_defined() {
		return correctness_not_defined;
	}
	
	public void setCorrectness_not_defined() {
		correctness_not_defined = true;
	}
}
