package differentialevolution;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.util.FastMath;

public class Rastrigin implements MultivariateFunction {

	private double axisratio;
	private double amplitude;

	Rastrigin() {
		this(1, 10);
	}

	Rastrigin(double axisratio, double amplitude) {
		this.axisratio = axisratio;
		this.amplitude = amplitude;
	}

	@Override
	public double value(double[] x) {
		double f = 0;
		double fac;
		for (int i = 0; i < x.length; ++i) {
			fac = FastMath.pow(axisratio, (i - 1.) / (x.length - 1.));
			if (i == 0 && x[i] < 0) {
				fac *= 1.;
			}
			f += fac * fac * x[i] * x[i] + amplitude
					* (1. - FastMath.cos(2. * FastMath.PI * fac * x[i]));
		}
		return f;
	}
}
