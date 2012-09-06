import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestOXXF {
   public static void main(String args[]) {
	   System.out.println(0xFF);
	   System.out.println((char)0x80);
	   System.out.println((byte)'a');
	   System.out.println((byte)'A');
	   System.out.println("AAA中国".toLowerCase());
	   System.out.println("************"+String.format(".[%d]", 2));
	   System.out.println((char)13);
	   System.out.println((char)10);
	   System.out.println("jsghdsgdh");
	   System.out.println(-1L & 0xffffffffL);
	   System.out.println("aaaaaaaaaaaa");
	   System.out.println(((byte)(64 >> 0) & 0xFF));
	   System.out.println(new String(new byte[]{64}));
	   System.out.println("@".getBytes()[0]);
	   System.out.println((byte)(75 & 0xFF));
	   System.out.println("++++++++++++++++++++++++++++++++++++");
	   System.out.println((byte)'\t');
	   System.out.println((char)(byte)(0x75 & 0xFF));
	   //System.out.println(Integer.parseInt(" 100"));
	   System.out.println((char)32);
	   System.out.println((byte)13);
	   System.out.println(0x01 & 0xFF);
	   System.out.println(-3 & 2);//0 1 -3 -4 -5 -6 -7 ..............这些结果都为0
	   System.out.println(0x00 & 2);
	   Matcher m=Pattern.compile("a(.*?)c").matcher("abc");
	   if(m.find()) {
		   System.out.println(m.group(0));
	   }
	   System.out.println("***********************************");
	   int flag=8;
	   int last;
	   int mask=0xF;
	   char c;
	   int in1=64;
	   while(flag--!=0){
		   last= in1 & mask;
		   if(last>=10){
		    c=(char) (97+last-10);
		    System.out.println(c);
		   }else{
		    System.out.println(last);
		   }
		   in1>>>=4;//右移四位，这样子每四位取值一次
		  }
   }
}
