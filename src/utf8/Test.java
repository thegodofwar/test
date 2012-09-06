package utf8;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Test {
   public static void main(String args[]) {
	   System.out.println((char)('a'+25));
	   String str="第一章 � 悬崖采药";
	   try {
		  byte bytes[]=str.getBytes("gb2312");
		  System.out.println(new String(bytes,Charset.forName("gbk")));
	   } catch (UnsupportedEncodingException e) {
		  e.printStackTrace();
	   }
   }
}
