package regression;
import java.io.*;

class SaveLoadResult {
	private String loadPath = "History.txt";//�ҷ��� ���
	private String savePath = "Linearregression.txt";//������ ���
	private String helpPath = "help.txt";//���� ���
	private String passWord = "deleteAll";//���� ��й�ȣ
	public SaveLoadResult() {}
	public void loadData() {//�����͸� �ҷ����� �޼ҵ�
		try {
			File file = new File(loadPath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String stringLine = null;
			while((stringLine = br.readLine()) != null) {
				System.out.println(stringLine);
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException fne) {
			System.out.println("����� ��� ������ �����ϴ�.");//������ ���� ���
		}catch(Exception e) {}
	}
	public void saveData(String expression, String result) {//�����͸� �����ϴ� �޼ҵ�
		try {
			File file = new File(savePath);
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw,true);
			pw.write(expression + " = " + result + "\n");
			pw.flush();
			pw.close();
			bw.close();
			fw.close();
		}catch(IOException ioe) {}
	}
	public void delData(String pw) {//�����͸� �����ϴ� �޼ҵ�
		if(pw.equals(passWord)) {//��й�ȣ�� �������
			File file = new File(savePath);
			if(file.exists()) {//�����ϸ� ����
				if(file.delete())
					System.out.println("������ ������ �����Ǿ����ϴ�.");
				else
					System.out.println("���� �� ������ �߻��߽��ϴ�.");
			}
			else {
				System.out.println("������ �������� �ʽ��ϴ�.");
			}
		}
	}
	public void loadHelp() {//���򸻺ҷ�����
		try {
			File file = new File(helpPath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String stringLine = null;
			while((stringLine = br.readLine()) != null) {
				System.out.println(stringLine);
			}
			br.close();
			fr.close();
		}catch(FileNotFoundException fne) {
			System.out.println("����� ��� ������ �����ϴ�.");
		}catch(Exception e) {}
		
	}
}
