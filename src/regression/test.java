package regression;
import java.util.*;
public class test {

	public static void main(String[] args) {
		double[][] X2= new double[4][4];
		for(int i = 0; i < X2.length; i++)
			Arrays.fill(X2[i], 0.1);
		System.out.println(Arrays.deepToString(X2));		
	}

}
