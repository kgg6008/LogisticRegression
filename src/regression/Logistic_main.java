package regression;

public class Logistic_main {
	public static void main(String[] args) {
		CSVtoArray csv = new CSVtoArray("X_train.csv");
		double[][] X_train = csv.returnArray();
		csv.changePath("Y_train.csv");
		double []y_train = csv.returnY();
		csv.changePath("X_test.csv");
		double[][] X_test = csv.returnArray();
		csv.changePath("Y_test.csv");
		double []y_test = csv.returnY();
		LogisticRegression LR_learn= new LogisticRegression(true);
		LR_learn.fit(X_train, y_train);
		double []y_pred = LR_learn.predict(X_test);
		ResultAnalysis ra = new ResultAnalysis(y_pred, y_test);
//		System.out.println(Arrays.toString(y_test));
//		System.out.println(Arrays.toString(y_pred));
		System.out.println("Accuracy : " + ra.getAccuracy());
//		System.out.println(Arrays.toString(LR_learn.theta));
		
	}
}
