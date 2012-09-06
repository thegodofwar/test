package SuffixArray;

/**
 * @see IOI2009国家集训队论文《后缀数组——处理字符串的有力工具》
 * @author leon
 * 
 */
public class SuffixArrayN {
	int max_char = '\uffff';

	char separator = '$';

	char eof = '#';

	int[][] rmq;

	String text = "";

	int min_len = Integer.MAX_VALUE;

	int lcs = 0;

	int index = -1;

	private void countingSort(Struct[] in, Struct[] out, int max, int compare_index) {
		int[] temp = new int[max + 1];
		for (int i = 0; i < in.length; i++) {
			temp[in[i].compareAry[compare_index]]++;
		}
		for (int i = 1; i < temp.length; i++) {
			temp[i] += temp[i - 1];
		}
		for (int i = in.length - 1; i >= 0; i--) {
			out[--temp[in[i].compareAry[compare_index]]] = in[i];
		}
	}

	/**
	 * Doubling Algorithm
	 * 
	 * @param text
	 * @return
	 */
	public Suffix buildSuffixArray(String text) {
		Struct[] in = new Struct[text.length()];
		for (int i = 0; i < in.length; i++) {
			in[i] = new Struct(i, new int[] { 0, text.charAt(i) });
		}
		Struct[] aux = (Struct[]) in.clone();
		countingSort(aux, in, max_char, 1);
		int[] rank = new int[in.length];
		int len = text.length();
		int wid = 1;
		Struct[] temp = new Struct[len];
		while (wid < len) {
			rank[in[0].suffix_i] = 0;
			for (int i = 1; i < len; i++) {
				rank[in[i].suffix_i] = rank[in[i - 1].suffix_i];
				if (in[i - 1].compareTo(in[i]) < 0) {
					rank[in[i].suffix_i]++;
				}
			}
			for (int i = 0; i < len; i++) {
				in[i].suffix_i = i;
				in[i].compareAry[0] = rank[i];
				in[i].compareAry[1] = i + wid < len ? rank[i + wid] : 0;
			}
			countingSort(in, temp, len, 1);
			countingSort(temp, in, len, 0);
			wid *= 2;
		}
		int[] sa = new int[rank.length];
		for (int i = 0; i < rank.length; i++) {
			sa[rank[i]] = i;
		}
		Suffix rs = new Suffix();
		rs.text = text;
		rs.rank = rank;
		rs.sa = sa;
		rs.h = calH(rank, sa, text.toCharArray());
		rs.height = calHeight(rs.h, sa);
		return rs;
	}

	private int[] calHeight(int[] h, int[] sa) {
		int[] height = new int[h.length];
		for (int i = 0; i < height.length; i++) {
			height[i] = h[sa[i]];
		}
		return height;
	}

	private int[] calH(int[] rank, int[] sa, char[] text) {
		int[] h = new int[rank.length];
		if (rank[0] == 0) {
			h[0] = 0;
		} else {
			h[0] = getH(text, 0, sa[rank[0] - 1]);
		}

		for (int i = 1; i < h.length; i++) {
			if (rank[i] == 0) {
				h[i] = 0;
			} else {
				if (h[i - 1] <= 1) {
					h[i] = getH(text, i, sa[rank[i] - 1]);
				} else {
					h[i] = h[i - 1] - 1 + getH(text, i + h[i - 1] - 1, sa[rank[i] - 1] + h[i - 1] - 1);
				}
			}
		}
		return h;
	}

	private int getH(char[] text, int i, int j) {
		int len = 0;
		while (i < text.length && j < text.length) {
			if (text[i] == text[j] && text[i] != separator && text[j] != separator) {
				i++;
				j++;
				len++;
			} else {
				break;
			}
		}
		return len;
	}

	public int inquireRMQ(int u, int v, int[] height) {
		if (u > v) {
			int temp = u;
			u = v;
			v = temp;
		}
		if (u == v)
			return height[u];
		int k = (int) (Math.log(1.0 * (v - u + 1)) / Math.log(2.0));
		if (rmq[u][k] < rmq[v - (1 << k) + 1][k])
			return rmq[u][k];
		else
			return rmq[v - (1 << k) + 1][k];
	}

	public void buildRMQ(int[] height) {
		int n = height.length;
		rmq = new int[n][n];
		for (int i = 1; i < height.length; i++)
			rmq[i][0] = height[i];
		for (int j = 1; (1 << j) < n; j++) {
			for (int i = 1; i < n; i++) {
				rmq[i][j] = rmq[i][j - 1];
				if (i + (1 << (j - 1)) < n) {
					if (rmq[i][j] > rmq[i + (1 << (j - 1))][j - 1]) {
						rmq[i][j] = rmq[i + (1 << (j - 1))][j - 1];
					}
				}
			}
		}
	}

	private int[] getRange(String[] texts) {
		int len = 0;

		for (int i = 0; i < texts.length; i++) {
			if (min_len > texts[i].length()) {
				min_len = texts[i].length();
			}
			text += texts[i];
			text += separator;
		}
		text += eof;
		len = text.length();
		int[] ary = new int[len];
		int j = 0;
		int k = 0;
		for (int i = 0; i < texts.length; i++) {
			int offset = texts[i].length() + 1;
			k += offset;
			while (j < k) {
				ary[j] = i;
				j++;
			}
		}
		return ary;
	}

	private void binarySearch(int[] height, int[] sa, int[] range, int n) {
		int low = 0;
		int high = min_len;
		while (low <= high) {
			int mid = (low + high) >> 1;
			if (checkMid(mid, height, sa, range, n)) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
	}

	private boolean checkMid(int mid, int[] height, int[] sa, int[] range, int n) {
		for (int i = 2; i < height.length; i++) {
			for (int j = i + n - 2; j < height.length; j++) {
				if (inquireRMQ(i, j, height) >= mid) {
					int sa_from = i - 1;
					int sa_to = j;
					if (inRange(range, sa, sa_from, sa_to, n)) {
						lcs = mid;
						index = sa[sa_from];
						return true;
					}
				} else {
					break;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean checkMid_old(int mid, int[] height, int[] sa, int[] range, int n) {
		int count = 1;
		for (int i = 2; i < height.length; i++) {
			int j = i;
			while (j < height.length && height[j] >= mid) {
				count++;
				j++;
			}
			if (count < n) {
				count = 1;
			} else {
				int sa_from = i - 1;
				int sa_to = j;
				if (inRange(range, sa, sa_from, sa_to, n)) {
					lcs = mid;
					index = sa[sa_from];
					return true;
				}

				count = 1;
			}
		}
		return false;
	}

	public String lcs(String[] texts) {
		int[] range = getRange(texts);
		Suffix suffix = buildSuffixArray(text);
		buildRMQ(suffix.height);
		binarySearch(suffix.height, suffix.sa, range, texts.length);
		if (lcs == 0) {
			return "EOF";
		}
		return text.substring(index, index + lcs);
	}

	public boolean inRange(int[] range, int[] sa, int sa_from, int sa_to, int n) {
		boolean[] visit = new boolean[n];
		int count = 0;
		for (int i = sa_from; i <= sa_to; i++) {
			if (!visit[range[sa[i]]]) {
				visit[range[sa[i]]] = true;
				count++;
			}
		}
		if (count == n) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		SuffixArrayN sa = new SuffixArrayN();
		String s = sa.lcs(new String[] { "adcccbadbbcadcccbba", "aabbccaacccabcca", "abccabccaccc", "ccc" });
		System.out.println(s);
	}
}

class Suffix {
	public String text;

	public int[] rank;

	public int[] sa;

	public int[] h;

	public int[] height;
}

class Struct implements Comparable<Struct> {
	public int suffix_i;

	public int[] compareAry = new int[2];

	public Struct(int suffix_i, int[] compareAry) {
		this.suffix_i = suffix_i;
		this.compareAry = compareAry;
	}

	public int compareTo(Struct st) {
		Struct other = st;
		int key0 = this.compareAry[0];
		int key1 = this.compareAry[1];
		int s0 = other.compareAry[0];
		int s1 = other.compareAry[1];
		if (key0 < s0) {
			return -1;
		} else if (key0 == s0) {
			return (key1 < s1 ? -1 : (key1 == s1 ? 0 : 1));
		} else {
			return 1;
		}
	}

	public String toString() {
		return "suffix(i)=" + this.suffix_i + ":" + compareAry[0] + "," + compareAry[1];
	}
}

