package regression;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVtoArray {
	String path;
	File file;
	
	public CSVtoArray(String path) {
		this.path = path;
	}
	public void changePath(String path) {
		this.path = path;
	}
	public double[][] returnArray(){
		file = new File(path);
		List<String> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                lines.add(inputStream.next());
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();System.out.println("Cannot find file.");
        }
        String []strCollection = lines.toArray(new String[lines.size()]);
        String[][]strSplit = new String[strCollection.length][];
        for(int i = 0; i < strCollection.length; i++) {
        	strSplit[i] = strCollection[i].split(",");
        }
        double[][]data  = new double[strSplit.length][strSplit[0].length];
        for(int i = 0; i < data.length; i++) {
        	for(int j = 0; j < data[i].length; j++) {
        		data[i][j] = Double.parseDouble(strSplit[i][j]);
        	}
        }
        return data;
	}
	public double[] returnY(){
		file = new File(path);
		List<String> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                lines.add(inputStream.next());
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();System.out.println("Cannot find file.");
        }
        String []strCollection = lines.toArray(new String[lines.size()]);
        double []data = new double[strCollection.length];
        for(int i = 0; i < data.length; i++)
        	data[i] = Double.parseDouble(strCollection[i]);
        return data;
	}
}
