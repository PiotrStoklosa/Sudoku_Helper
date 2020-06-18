
public class Blunder {
	boolean correctness;
	int x, y;

	Blunder() {
		correctness = true;
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
}
