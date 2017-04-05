package differentialevolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.analysis.function.Abs;
import org.apache.commons.math3.genetics.Fitness;

public class DifferentialEvolution {

	/**
	 * 0 <= F <= 2. It's close to 2.
	 */
	private static final double F = 0.6;

	public double[] nextInd(double[][] p, double[] i) {
		double[] a, b, c;

		List<double[]> parents = this.pickDifParents(p, i);
		a = parents.get(0);
		b = parents.get(1);
		c = parents.get(2);

		int d = p[0].length;
		int R = (int) (Math.random() * d);

		double[] q = new double[d];
		for (int k = 0; k < d; k++) {
			if (k == R) {
				q[k] = a[k] + F * (b[k] - c[k]);
			} else {
				q[k] = i[k];
			}
		}

		return q;
	}

	public double[][] nextPop(double[][] p) {
		double[][] q = new double[p.length][p[0].length];
		for (int i = 0; i < q.length; i++) {
			double[] nextInd = this.nextInd(p, p[i]);
			if (fitness(nextInd) < fitness(q[i])) {
				q[i] = nextInd;
			}
		}
		return q;
	}

	private double fitness(double[] nextInd) {
		Rastrigin rastrigin = new Rastrigin();
		return new Abs().value(rastrigin.value(nextInd));
	}

	public void differentialEvolution() {
		double[][] p = initPop(n);

	}

	private double[][] initPop() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<double[]> pickDifParents(double[][] p, double[] i) {
		List<double[]> parents = new ArrayList<double[]>(3);
		while (parents.size() < 3) {
			int t = RandomUtils.nextInt(0, p.length);
			double[] other = p[t];
			if (!Objects.deepEquals(i, other)) {
				parents.add(other);
			}

		}
		return parents;
	}
}
