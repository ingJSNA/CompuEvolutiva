package function.real;

import org.apache.commons.math3.util.FastMath;

public class Ackley {

	private static final double A = 20;
	private static final double B = 20;
	private static final double C = 2 * FastMath.PI;

	private double axisratio;

	Ackley(double axra) {
		axisratio = axra;
	}

	public Ackley() {
		this(1);
	}

	public double value(double[] x) {
		double f = 0;
		double res2 = 0;
		double fac = 0;
		for (int d = 0; d < x.length; ++d) {
			fac = FastMath.pow(axisratio, (d - 1.) / (x.length - 1.));
			f += fac * fac * x[d] * x[d];
			res2 += FastMath.cos(C * fac * x[d]);
		}
		f = (A - A * FastMath.exp(-B * FastMath.sqrt(f / x.length)) + FastMath.exp(1.) - FastMath
				.exp(res2 / x.length));
		return f;
	}
}
