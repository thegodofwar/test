package indexof;

import java.util.concurrent.ConcurrentHashMap;

public class Test {
    
	public static void main(String args[]) {
        //String str="121xs: book update";
        //System.out.println(str.subSequence(0,str.indexOf(':')));
		ConcurrentHashMap<String,Long> crawlIntervals=new ConcurrentHashMap<String,Long>();
		//crawlIntervals.put("a", 1L);
		System.out.println(crawlIntervals.putIfAbsent("a",2L));
		String abc="\u4e2d\u56fd";
		String a="\u65e5";
		//String b="\\uhgg";
	}
	
}
