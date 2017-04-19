package statistic;

import java.util.Arrays;

public class Experiment {

	public static void experiment() {
		int runs = 30;
		double[] x = new double[runs];

		double avg = 0.0;
		for (int r = 0; r < runs; r++) {
			x[r] = realPowerLaw();
			System.out.println("run " + r + ": " + x[r]);
			avg += x[r];
		}

		Statistics stats = new Statistics(x);

		System.out.println("mean: " + stats.avg);

		avg /= runs;

		// TODO merge sort
		Arrays.sort(x);
		double median = x[runs / 2];
		System.out.println("median: " + median);

		double v_avg = 0.0;
		double v_median = 0.0;

		for (int i = 0; i < x.length; i++) {
			v_avg += (x[i] - avg) * (x[i] - avg);
			v_median += (x[i] - median) * (x[i] - median);
		}

		// Standart deviation
		double s_avg = Math.sqrt(v_avg / (runs - 1));
		double s_median = Math.sqrt(v_median / (runs - 1));

		System.out.println("Using mean: " + avg + "+/-" + s_avg);
		System.out.println("Using median: " + median + "+/-" + s_median);
	}

	private static double realPowerLaw() {
		// TODO Auto-generated method stub
		return 0;
	}
}
