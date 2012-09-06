package BM;

/* Title:     Boyer-Moore string matching algorithm
 * Author:    H.W. Lang
 *            Fachhochschule Flensburg, University of Applied Sciences
 *            Flensburg, Germany
 * Date:      2007
 * Mail:      lang@fh-flensburg.de
 * Web:       http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/bmen.htm
 * Reference: R.S. Boyer, J.S. Moore: A Fast String Searching Algorithm.
 *            Communications of the ACM, 20, 10, 762-772 (1977)
 */

public class EasyBM {
	private char[] p, t; // pattern, text
	private int m, n; // pattern length, text length
	private static int alphabetsize = 256;
	private int[] occ; // occurence function
	private String matches; // string of match positions
	private char[] showmatches;// char array that shows matches
	private int[] f;
	private int[] s;
	private static String name = "Boyer-Moore";

	public EasyBM() {
		occ = new int[alphabetsize];
	}

	public void search(String tt, String pp) {
		setText(tt);
		setPattern(pp);
		bmSearch();
	}

	/**
	 * sets the text
	 */
	private void setText(String tt) {
		n = tt.length();
		t = tt.toCharArray();
		initmatches();
	}

	/**
	 * sets the pattern
	 */
	public void setPattern(String pp) {
		m = pp.length();
		p = pp.toCharArray();
		f = new int[m + 1];
		s = new int[m + 1];
		bmPreprocess();
	}

	/**
	 * computation of the occurrence function
	 */
	private void bmInitocc() {
		char a;
		int j;

		for (a = 0; a < alphabetsize; a++)
			occ[a] = -1;

		for (j = 0; j < m; j++) {
			a = p[j];
			occ[a] = j;
		}
	}

	/**
	 * preprocessing according to the pattern (part 1)
	 */
	private void bmPreprocess1() {
		int i = m, j = m + 1;
		f[i] = j;
		while (i > 0) {
			while (j <= m && p[i - 1] != p[j - 1]) {
				if (s[j] == 0)
					s[j] = j - i;
				j = f[j];
			}
			i--;
			j--;
			f[i] = j;
		}
	}

	/**
	 * preprocessing according to the pattern (part 2)
	 */
	private void bmPreprocess2() {
		int i, j;
		j = f[0];
		for (i = 0; i <= m; i++) {
			if (s[i] == 0)
				s[i] = j;
			if (i == j)
				j = f[j];
		}
	}

	/**
	 * preprocessing according to the pattern
	 */
	private void bmPreprocess() {
		bmInitocc();
		bmPreprocess1();
		bmPreprocess2();
	}

	/**
	 * initializes match positions and the array showmatches
	 */
	private void initmatches() {
		matches = "";
		showmatches = new char[n];
		for (int i = 0; i < n; i++)
			showmatches[i] = ' ';
	}

	/**
	 * searches the text for all occurences of the pattern
	 */
	private void bmSearch() {
		int i = 0, j;
		while (i <= n - m) {
			j = m - 1;
			while (j >= 0 && p[j] == t[i + j])
				j--;
			if (j < 0) {
				report(i);
				i += s[0];
			} else
				i += Math.max(s[j + 1], j - occ[t[i + j]]);
		}
	}

	/**
	 * reports a match
	 */
	private void report(int i) {
		matches += i + " ";
		showmatches[i] = '^';
	}

	/**
	 * returns the match positions after the search
	 */
	public String getMatches() {
		return matches;
	}

	// only for test purposes
	public static void main(String[] args) {
		EasyBM stm = new EasyBM();
		System.out.println(name);
		String tt, pp;
		tt = "eabcdacabacd";
		pp = "acaba";
		stm.search(tt, pp);
		System.out.println(pp);
		System.out.println(tt);
		System.out.println(stm.showmatches);
		System.out.println(stm.getMatches());
		tt = "gbababaa";
		pp = "aba";
		stm.search(tt, pp);
		System.out.println(pp);
		System.out.println(tt);
		System.out.println(stm.showmatches);
		System.out.println(stm.getMatches());
	}

} // end class BmStringMatcher

