package model;

public class RandBbNum {
	
	public static String randNum() {
		String s = "";
		int chkNum[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		do {
			int tmp = (int)( Math.random() * 10);
			if (chkNum[tmp] < 1) {
				chkNum[tmp]++;
				s += Integer.toString(tmp);
			} 
		} while (s.length()<4);
		return s;
	}

}
