package LCS;

public class LCS {
	
	public static int[][] lcs(String str1, String str2) {
		int[][] opt = new int[str2.length() + 1][str1.length() + 1];

		for (int i = 0; i <= str2.length(); i++) {
			opt[i][0] = 0;
		}

		for (int j = 0; j <= str1.length(); j++) {
			opt[0][j] = 0;
		}

		for (int j = 1; j <= str1.length(); j++) {
			for (int i = 1; i <= str2.length(); i++) {
				if (str2.charAt(i - 1) == str1.charAt(j - 1)) {
					opt[i][j] = opt[i - 1][j - 1] + 1;
				} else {
					opt[i][j] = (opt[i - 1][j] >= opt[i][j - 1] ? opt[i - 1][j]
							: opt[i][j - 1]);
				}
			}
		}

		return opt;
	}

	public static void print(int[][] opt, String X, String Y, int i, int j) {

		if (i == 0 || j == 0) {
			return;
		}

		if (X.charAt(i - 1) == Y.charAt(j - 1)) {
			System.out.print(X.charAt(i - 1));
			print(opt, X, Y, i - 1, j - 1); // don't put this line before the upper line. Otherwise, the order is wrong.
		} else if (opt[i - 1][j] == opt[i][j]) {
			print(opt, X, Y, i - 1, j);
		} else if (opt[i][j-1] == opt[i][j]) {
			print(opt, X, Y, i, j - 1);
		}
	}
	
	public static void main(String args[]) {
		String str1="adecfdg";
		String str2="aadcegadcefddg";
		int result[][]=lcs(str1,str2);
		
		System.out.println(result[str2.length()][str1.length()]);
		print(result,str2,str1,str2.length(),str1.length());
	}
}
