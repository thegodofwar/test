import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestP {
   public static void main(String args[]) {
	 // String s = "aaaabbbb\r\n\0";
	  //System.out.print(s.getBytes().length);
	  Calendar c=Calendar.getInstance();
	  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
	  Date d=null;
	  try {
	    d=sdf.parse("04-27");
	   } catch (ParseException e) {
		e.printStackTrace();
	   }
	   c.setTime(d);
	   c.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
	   System.out.println(sf.format(c.getTime()));
	  
   }
}
