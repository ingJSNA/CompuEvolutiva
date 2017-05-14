package gp;

public class Example {

	private int answer;
	private int x;
	private int y;

	/**
	 * @param answer
	 * @param x
	 * @param y
	 */
	public Example(int x, int y) {
		super();
		this.answer = f(x, y);
		this.x = x;
		this.y = y;
	}

	private int f(int x, int y) {
		return (int) (Math.pow(x, 2) + 3 * x * y - 4);
	}

	/**
	 * @return the answer
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
