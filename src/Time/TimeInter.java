package Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeInter {
	
    public static void main(String args[]) {
    	String str1="2012-08-26 16:18:00";
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date o=null;
    	try {
			o=format.parse(str1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	System.out.println((new Date().getTime()-o.getTime())/(1000*60*60));
    }
}
