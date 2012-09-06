package SuffixArray;

/**
 * 
 * Build Suffix Array using Prefix Doubling Algorithm see also: Udi Manber and
 * Gene Myers' seminal paper(1991):
 * "Suffix arrays: A new method for on-line string searches"
 * 
 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) Licensed under GPL
 * (http://www.opensource.org/licenses/gpl-license.php)
 * 
 * @author ljs 2011-07-17
 * 
 */
public class SuffixArray {
	public static final char MAX_CHAR = '\u00FF';

	class Suffix {
		int[] sa;
		// Note: the p-th suffix in sa: SA[rank[p]-1]];
		// p is the index of the array "rank", start with 0;
		// a text S's p-th suffix is S[p..n], n=S.length-1.
		int[] rank;
		boolean done;
	}

	// a prefix of suffix[isuffix] represented with digits
	class Tuple {
		int isuffix; // the p-th suffix
		int[] digits;

		public Tuple(int suffix, int[] digits) {
			this.isuffix = suffix;
			this.digits = digits;
		}

		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(isuffix);
			sb.append("(");
			for (int i = 0; i < digits.length; i++) {
				sb.append(digits[i]);
				if (i < digits.length - 1)
					sb.append("-");
			}
			sb.append(")");
			return sb.toString();
		}
	}

	// the plain counting sort algorithm for comparison
	// A: input array
	// B: output array (sorted)
	// max: A value's range is 0...max
	public void countingSort(int[] A, int[] B, int max) {
		// init the counter array
		int[] C = new int[max + 1];
		for (int i = 0; i <= max; i++) {
			C[i] = 0;
		}
		// stat the count in A
		for (int j = 0; j < A.length; j++) {
			C[A[j]]++;
		}
		// process the counter array C
		for (int i = 1; i <= max; i++) {
			C[i] += C[i - 1];
		}
		// distribute the values in A to array B
		for (int j = A.length - 1; j >= 0; j--) {
			// C[A[j]] <= A.length
			B[--C[A[j]]] = A[j];
		}
	}

	// d: the digit to do countingsort
	// max: A value's range is 0...max
	private void countingSort(int d, Tuple[] tA, Tuple[] tB, int max) {
		// init the counter array
		int[] C = new int[max + 1];
		for (int i = 0; i <= max; i++) {
			C[i] = 0;
		}
		// stat the count
		for (int j = 0; j < tA.length; j++) {
			C[tA[j].digits[d]]++;
		}
		// process the counter array C
		for (int i = 1; i <= max; i++) {
			C[i] += C[i - 1];
		}
		// distribute the values
		for (int j = tA.length - 1; j >= 0; j--) {
			// C[A[j]] <= A.length
			tB[--C[tA[j].digits[d]]] = tA[j];
		}
	}

	// tA: input
	// tB: output for rank caculation
	private void radixSort(Tuple[] tA, Tuple[] tB, int max, int digitsLen) {
		int len = tA.length;
		int digitsTotalLen = tA[0].digits.length;

		for (int d = digitsTotalLen - 1, j = 0; j < digitsLen; d--, j++) {
			this.countingSort(d, tA, tB, max);
			// assign tB to tA
			if (j < digitsLen - 1) {
				for (int i = 0; i < len; i++) {
					tA[i] = tB[i];
				}
			}
		}
	}

	// max is the maximum value in any digit of TA.digits[], used for counting
	// sort
	// tA: input
	// tB: the place holder, reused between iterations
	private Suffix rank(Tuple[] tA, Tuple[] tB, int max, int digitsLen) {
		int len = tA.length;
		radixSort(tA, tB, max, digitsLen);

		int digitsTotalLen = tA[0].digits.length;

		// caculate rank and sa
		int[] sa = new int[len];
		sa[0] = tB[0].isuffix;

		int[] rank = new int[len];
		int r = 1; // rank starts with 1
		rank[tB[0].isuffix] = r;
		for (int i = 1; i < len; i++) {
			sa[i] = tB[i].isuffix;

			boolean equalLast = true;
			for (int j = digitsTotalLen - digitsLen; j < digitsTotalLen; j++) {
				if (tB[i].digits[j] != tB[i - 1].digits[j]) {
					equalLast = false;
					break;
				}
			}
			if (!equalLast) {
				r++;
			}
			rank[tB[i].isuffix] = r;
		}

		Suffix suffix = new Suffix();
		suffix.rank = rank;
		suffix.sa = sa;
		// judge if we are done
		if (r == len) {
			suffix.done = true;
		} else {
			suffix.done = false;
		}
		return suffix;

	}

	// Precondition: the last char in text must be less than other chars.
	public Suffix solve(String text) {
		if (text == null)
			return null;
		int len = text.length();
		if (len == 0)
			return null;

		int k = 1;
		char base = text.charAt(len - 1); // the smallest char
		Tuple[] tA = new Tuple[len];
		Tuple[] tB = new Tuple[len]; // placeholder
		for (int i = 0; i < len; i++) {
			tA[i] = new Tuple(i, new int[] { 0, text.charAt(i) - base });
		}
		Suffix suffix = rank(tA, tB, MAX_CHAR - base, 1);
		while (!suffix.done) { // no need to decide if: k<=len
			k <<= 1;
			int offset = k >> 1;
			for (int i = 0, j = i + offset; i < len; i++, j++) {
				tA[i].isuffix = i;
				tA[i].digits = new int[] { suffix.rank[i],
						(j < len) ? suffix.rank[i + offset] : 0 };
			}
			int max = suffix.rank[suffix.sa[len - 1]];
			suffix = rank(tA, tB, max, 2);
		}
		return suffix;
	}

	public void report(Suffix suffix) {
		int[] sa = suffix.sa;
		int[] rank = suffix.rank;
		int len = sa.length;

		System.out.println("suffix array:");
		for (int i = 0; i < len; i++) {
			System.out.format(" %s", sa[i]);
		}
		System.out.println();
		System.out.println("rank array:");
		for (int i = 0; i < len; i++) {
			System.out.format(" %s", rank[i]);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		/*
		 * //plain counting sort test:
		 * 
		 * int[] A= {2,5,3,0,2,3,0,3}; PrefixDoubling pd = new PrefixDoubling();
		 * int[] B = new int[A.length]; pd.countingSort(A,B,5); for(int
		 * i=0;i<B.length;i++) System.out.format(" %d", B[i]);
		 * System.out.println();
		 */

		String text = "GACCCACCACC#";
		SuffixArray pd = new SuffixArray();
		Suffix suffix = pd.solve(text);
		System.out.format("Text: %s%n", text);
		pd.report(suffix);

		System.out.println("********************************");
		text = "mississippi#";
		pd = new SuffixArray();
		suffix = pd.solve(text);
		System.out.format("Text: %s%n", text);
		pd.report(suffix);

		System.out.println("********************************");
		text = "abcdefghijklmmnopqrstuvwxyz#";
		pd = new SuffixArray();
		suffix = pd.solve(text);
		System.out.format("Text: %s%n", text);
		pd.report(suffix);

		System.out.println("********************************");
		text = "yabbadabbado#";
		pd = new SuffixArray();
		suffix = pd.solve(text);
		System.out.format("Text: %s%n", text);
		pd.report(suffix);

		System.out.println("********************************");
		text = "DFDLKJLJldfasdlfjasdfkldjasfldafjdajfdsfjalkdsfaewefsdafdsfa#";
		pd = new SuffixArray();
		suffix = pd.solve(text);
		System.out.format("Text: %s%n", text);
		pd.report(suffix);

	}
}
