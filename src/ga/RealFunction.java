package ga;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.FastMath;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.OptimizationGoal;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.testbed.Ackley;
import unalcol.optimization.real.testbed.Rastrigin;
import unalcol.search.Goal;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.search.space.Space;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArray;

public class RealFunction extends OptimizationFunction<BitArray> {

	// Number of bits per integer (i.e. per real)
	int BITS_PER_DOUBLE = 8;

	public RealFunction() {
	}

	@Override
	public Double apply(BitArray x) {
		// TODO Auto-generated method stub
		int DIM = x.dimension() / BITS_PER_DOUBLE;
		double[] min = DoubleArray.create(DIM, -32.768);
		double[] max = DoubleArray.create(DIM, 32.768);

		// CodeDecodeMap

		CodeDecodeMap<BitArray, double[]> map = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);

		double[] decode = map.decode(x);

		double fitness = new Ackley().apply(decode);

		return fitness;
	}

	public double Ackley(double[] x) {
		double f = 0;
		double res2 = 0;
		double fac = 0;
		for (int i = 0; i < x.length; ++i) {
			double axisratio = 1;
			fac = FastMath.pow(axisratio, (i - 1.) / (x.length - 1.));
			f += fac * fac * x[i] * x[i];
			res2 += FastMath.cos(2. * FastMath.PI * fac * x[i]);
		}
		f = (20. - 20. * FastMath.exp(-0.2 * FastMath.sqrt(f / x.length)) + FastMath.exp(1.) - FastMath
				.exp(res2 / x.length));
		return f;
	}
}
