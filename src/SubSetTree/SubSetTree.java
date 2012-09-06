package SubSetTree;

import java.util.ArrayList;
import java.util.TreeSet;

public class SubSetTree {
	// 求出从0到集合子集数目（这里为16）之间的数的二进制形式，存放在数组result中
	public static String[] getBinaryValue(TreeSet<String> set) {
		int size = set.size();
		int m = (int) Math.pow(2, size) - 1;
		String[] result = new String[m + 1];
		for (int i = m; i > -1; i--) {
			StringBuffer sb = new StringBuffer(Integer.toBinaryString(i));
			int length = sb.length();
			if (length < size) {
				for (int j = 0; j < size - length; j++) {
					sb.insert(0, "0");
				}
			}
			result[i] = sb.toString();
		}
		return result;
	}
	
	// 根据二进制字符串生成子集
	public static ArrayList<TreeSet<String>> getSubset(TreeSet<String> set) {
		ArrayList<TreeSet<String>> result = new ArrayList<TreeSet<String>>();

		// 把集合元素放入数组中，方便存取
		String[] items = new String[set.size()];
		int i = 0;
		for (String item : set) {
			items[i++] = item;
		}
		// 调用二进制字符串生成函数
		String[] binaryValue = getBinaryValue(set);
		// 根据二进制字符串取集合元素构成子集
		for (int j = 0; j < binaryValue.length; j++) {
			String value = binaryValue[j];
			TreeSet<String> subset = new TreeSet<String>();
			for (int k = 0; k < value.length(); k++) {
				if (value.charAt(k) == '1')
					subset.add(items[k]);
			}
			result.add(subset);
		}

		return result;
	}
	
	public static void main(String[] args) {
		TreeSet<String> set = new TreeSet<String>();
		set.add("A");
		set.add("B");
		set.add("C");
		set.add("D");
		ArrayList<TreeSet<String>> subset = getSubset(set);
		System.out.println("一共有" + subset.size() + "个子集。");
		for (TreeSet<String> ts : subset) {
			System.out.println(ts.toString());
		}
	}

}
