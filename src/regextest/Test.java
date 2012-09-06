package regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
   
	public static void main(String args[]) {
		Pattern p=Pattern.compile("((?:abc)?de)f");
		Matcher m=p.matcher("qabcdefgh");
		while(m.find()) {
		System.out.println(m.group(1));
		}
	}
	
}
