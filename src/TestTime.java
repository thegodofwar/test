import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TestTime {
  public static void main(String args[]) {
	  /*Date curTime=new Date();
	  curTime.getTime();
	  Calendar now=Calendar.getInstance();*/
	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  Date bTime1=null;
	  Date bTime2=null;
	  try {
		bTime1=format.parse("2011-12-26 00:00:00");
		bTime2=format.parse("2011-12-27 00:00:00");
	  } catch (ParseException e) {
		e.printStackTrace();
	  }
	  System.out.println(bTime2.getTime()-bTime1.getTime());
	  System.out.println(24*60*60*1000);
	  int a=1+65536;
	  System.out.println(a);
  }
}
