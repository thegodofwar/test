package Abstract;

import java.util.HashMap;
import java.util.Map;

public class Abstract {
	/*
	 * 编程之美 最短摘要生成
	 */

	private int[] keywordsArray; // 记录关键字被访问次数的数组
	private int pBegin = 0;// 查找起始点
	private int pEnd = 0;// 查找终点
	private int abstractBegin = 0;// 摘要起始点
	private int abstractEnd = 0;// 摘要终点
	private int targetLen;// 摘要最小长度
	private Map<String, Integer> map;// 将关键字映射成数字

	public Abstract(String[] keywords) {
		int len = keywords.length;
		this.keywordsArray = new int[len];
		this.map = keywordsMap(keywords);
	}

	public String extractSummary(String description, String[] keywords) {
		String[] array = description.split(" ");// 将字符串转化为数组
		return extract(array, keywords);
	}

	// 实际的抽取函数
	public String extract(String[] description, String[] keywords) {
		String summary = "";
		int nLen = description.length;
		targetLen = nLen;
		
		while(pBegin<nLen&&map.get(description[pBegin]) == null) {
			pBegin++;
		}
		
		while (true) {
			while (!isAllExisted() && pEnd < nLen) {
				if (this.map.get(description[pEnd]) != null) {
					setKeywordsArray(keywordsArray, this.map.get(description[pEnd]), 0);
				}
				pEnd++;
			}
			while (isAllExisted()) {
				if (pEnd - pBegin < targetLen) {
					targetLen = pEnd - pBegin;
					abstractBegin = pBegin;
					abstractEnd = pEnd - 1;
				}
				if (map.get(description[pBegin]) != null) {
					setKeywordsArray(keywordsArray, map
							.get(description[pBegin]), 1);
				}
				pBegin++;
			}
			
			while(pBegin<nLen&&map.get(description[pBegin]) == null) {
				pBegin++;
			}
			
			if (pEnd >= nLen) {
				break;
			}
		}
		for (int j = abstractBegin; j <= abstractEnd; j++) {
			if (j != abstractEnd) {
				summary = summary + description[j] + " ";
			} else {
				summary += description[j];
			}
		}
		return summary;
	}

	public Map<String, Integer> keywordsMap(String[] keywords) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int len = keywords.length;
		for (int i = 0; i < len; i++) {
			map.put(keywords[i], i);
		}
		return map;
	}

	// 设置关键字被访问到的次数
	public void setKeywordsArray(int[] keywordsArray, int i, int flag) { // flag:0
																			// add
																			// flag:1
																			// sub
		if (flag == 0) {
			keywordsArray[i]++;
		} else {
			keywordsArray[i]--;
		}
	}

	// 检查是否包含全部关键字
	public boolean isAllExisted() {
		boolean result = true;
		for (int a : keywordsArray) {
			if (a == 0) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String description = "hello software hello test world spring sun flower hello";
		String[] keywords = { "hello", "world" };
		Abstract nAbstract = new Abstract(keywords);
		System.out.println(nAbstract.extractSummary(description, keywords));
	}
}