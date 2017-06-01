package ga;

import unalcol.optimization.OptimizationFunction;
import unalcol.types.collection.bitarray.BitArray;
import function.real.Ackley;

public class RealFunctionFitness extends OptimizationFunction<BitArray> {

	private BitArrayToRealEncoder encoder;
	private Ackley ackley;

	public RealFunctionFitness(int bitArraySize) {

		encoder = new BitArrayToRealEncoder(bitArraySize, -32.768, 32.768);

		ackley = new Ackley();

	}

	@Override
	public Double apply(BitArray x) {

		double[] decode = encoder.decode(x);

		double fitness = ackley.value(decode);

		return fitness;
	}
}
