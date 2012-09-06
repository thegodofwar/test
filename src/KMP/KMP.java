package KMP;

public class KMP {//KMP复杂度主串不回溯O(m+n)、BF复杂度主串回溯O(m*n)
	/**
	 * 在target字符串中寻找source的匹配
	 * 
	 * @param target
	 * @param source
	 */
	public void kmp(String target, String source) {
		int sourceLength = source.length();
		int targetLength = target.length();
		int[] result = preProcess(source);
		int j = 0;
		int k = 0;
		for (int i = 0; i < targetLength; i++) {
			// 找到匹配的字符时才执行
			while (j > 0 && source.charAt(j) != target.charAt(i)) {
				// 设置为source中合适的位置
				j = result[j - 1];
			}
			// 找到一个匹配的字符
			if (source.charAt(j) == target.charAt(i)) {
				j++;
			}
			// 匹配到一个，输出结果
			if (j == sourceLength) {
				j = result[j - 1];
				k++;
				System.out.println("find...index=" + (i + 1 - sourceLength));
			}
		}
	}

	/**
	 * 预处理
	 * 
	 * @param s
	 * @return
	 */
	public int[] preProcess(final String s) {
		int size = s.length();
		int[] result = new int[size];
		result[0] = 0;
		int j = 0;
		// 循环计算
		for (int i = 1; i < size; i++) {
			while (j > 0 && s.charAt(j) != s.charAt(i)) {
				j = result[j];
			}
			if (s.charAt(j) == s.charAt(i)) {
				j++;
			}
			// 找到一个结果
			result[i] = j;
		}
		System.out.println(java.util.Arrays.toString(result));
		return result;
	}

	public static void main(String[] args) throws Exception {
		// final String s = "abcdabcd";
		final String s = "abcacabababcb";
		KMP k = new KMP();
		// k.kmp(s, "abcd");
		// k.kmp(s, "ababc");
		k.kmp(s, "aba");
	}

}
