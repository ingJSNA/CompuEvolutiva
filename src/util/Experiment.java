package util;

import java.util.concurrent.Callable;

import unalcol.types.real.array.DoubleArray;

public class Experiment {

	private int runs;
	private Callable<Double> callable;

	public Experiment(Callable<Double> callable) {
		runs = 30;
		this.callable = callable;
	}

	public void experiment() {
		double[] x = new double[runs];

		double avg = 0.0;
		for (int r = 0; r < runs; r++) {
			x[r] = run();
			avg += x[r];
		}

		avg /= runs;
		DoubleArray.merge(x);
		double median = x[runs / 2];

		double v_avg = 0.0;
		double v_median = 0.0;

		for (int i = 0; i < x.length; i++) {
			v_avg += (x[i] - avg) * (x[i] - avg);
			v_median += (x[i] - median) * (x[i] - median);
		}

		// Standart deviation
		double s_avg = Math.sqrt(v_avg / (runs - 1));
		double s_median = Math.sqrt(v_median / (runs - 1));

		System.out.println("Using mean: " + avg + " +/- " + s_avg);
		System.out.println("Using median: " + median + " +/- " + s_median);
	}

	private double run() {
		try {
			return this.callable.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
