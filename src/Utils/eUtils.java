package Utils;

import java.util.Random;

public class eUtils {
	private Random rand;
	
	public eUtils(){
		rand = new Random();
	}

	public double[] makeRandomArray(int i, double base, double error) {
		double[] out = new double[i];
		for(int j = 0; j < i; j++){
			out[j] = getRandom(base, error);
		}
		return out;
	}

	public double[] makeArray(int i, int j) {
		double[] out = new double[i];
		for(int k = 0; k < i; k++){
			out[k] = j;
		}
		return out;
	}

	public double getRandom(double base, double error) {
		double out = ((rand.nextDouble()*2 - 1)*error) + base;
		return out;
		
	}

}