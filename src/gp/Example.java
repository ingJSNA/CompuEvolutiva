package gp;

public class Example {

	private double answer;
	private double x;
	private double y;

	/**
	 * @param answer
	 * @param x
	 * @param y
	 */
	public Example(double x, double y) {
		super();
		this.answer = f(x, y);
		this.x = x;
		this.y = y;
	}

	private double f(double x, double y) {
		return Math.pow(x, 2) + 3 * x * y - 4;
	}

	/**
	 * @return the answer
	 */
	public double getAnswer() {
		return answer;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

}
