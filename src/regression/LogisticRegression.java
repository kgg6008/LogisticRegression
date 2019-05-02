package regression;
import java.util.Arrays;

public class LogisticRegression{
	double []theta;
	private boolean opt_c = false;
	private int iteration = 100000;
	private double rLearning = 0.01;
	private double threshold = 0.0001;
	public LogisticRegression() {}
	public LogisticRegression(boolean opt_c) {
		this.opt_c = opt_c;
	}
	public LogisticRegression(int iteration) {
		this.iteration = iteration;
	}
	public LogisticRegression(int iteration, boolean opt_c) {
		this(iteration); 
		this.opt_c = opt_c;
	}
	private void setTheta(double[][] X_train) {
		double []temp = new double[X_train[0].length];
		Arrays.fill(temp, 1/X_train[0].length);
		theta = temp;
	}
	private double[][] combinationC(double[][] a) {
		double [][]temp = new double[a.length][];
		for(int i = 0; i < a.length; i++) {
			temp[i] = new double[a[i].length + 1];
			temp[i][0] = 1;
			for(int j = 0; j < a[i].length; j++)
				temp[i][j+1] = a[i][j];
		}
		return temp;
	}
	private double sigmoid(double z) {
		return 1.0/(1.0 + Math.exp(-z));
	}
	private double[] sigmoidfn(double[] z){
		double[] resultSigmo = new double[z.length];
		for(int i = 0; i < z.length; i++) {
			resultSigmo[i] = sigmoid(z[i]);
		}
		return resultSigmo;
	}
	private double costfn(double[] hypothesis, double[] y_true) {
		double result = 0.0;
		for(int i = 0; i < y_true.length; i++) {
			result += (-y_true[i]* Math.log(hypothesis[i])- ((1 - y_true[i])*Math.log(1 - hypothesis[i])));
		}
		result /= y_true.length;
		return result;
	}
	private double[] dot(double[][] X_train, double[] weights) {
		double []resultDot = new double[X_train.length];
		for(int i = 0; i < X_train.length; i++) {
			for(int j = 0; j < X_train[i].length; j++) {
				resultDot[i] += X_train[i][j] * weights[j];
			}
		}
		return resultDot;
	}
	private double[] divisionScalar(double[] array, double num) {
		double [] result = new double[array.length];
		for(int i = 0; i < result.length; i++) {
			result[i] = array[i] / num;
		}
		return result;
	}
	private double[] dot(double ratio, double[] gradient) {
		
		double []resultDot = new double[gradient.length];
		for(int i = 0; i < gradient.length; i++) {
			resultDot[i] = ratio * gradient[i];
		}
		return resultDot;
	}
	private double[] difference(double[]hypothesis, double[] y_true) {
		double[] diffResult = new double[y_true.length];
		for(int i = 0; i < y_true.length; i++)
			diffResult[i] = hypothesis[i] - y_true[i];
		return diffResult;
	}
	private double[][] trans(double[][] X_train){
		double [][]resultTrans = new double[X_train[0].length][X_train.length];
		for(int i = 0; i < X_train.length; i++) {
			for(int j = 0; j < X_train[i].length; j++)
				resultTrans[j][i] = X_train[i][j];
		}
		return resultTrans;
	}
	public void fit(double[][] X_train, double[] y_train) {
		if(opt_c) {
			X_train = combinationC(X_train);
		}
		setTheta(X_train);
		double numEx = X_train.length;
		for(int i = 0; i < iteration; i++) {
			double[] z =dot(X_train, theta);
			double[] hypothesis = sigmoidfn(z);
			double[] diff = difference(hypothesis, y_train);
			double cost = costfn(hypothesis, y_train);
			double[] gradient =  divisionScalar(dot(trans(X_train), diff), numEx);
			double[] tmptheta = dot(rLearning, gradient);
			theta = difference(theta, tmptheta);			
			if(cost < threshold)
				break;
			
		}
	}
	
	public double[] predictProba(double [][]X_test) {
		if(opt_c)
			X_test = combinationC(X_test);
		return sigmoidfn(dot(X_test, theta));
	}
	public double[] predict(double[][]X_test) {
		double[] result = predictProba(X_test);
		double []y_pred = new double[X_test.length];
		for(int i = 0; i < result.length; i++) {
			y_pred[i] = (int)Math.round(result[i]);
		}
		return y_pred;
	}
	
}
