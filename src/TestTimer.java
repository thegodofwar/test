import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestTimer {
	public int tag=-1;
    public static void main(String args[]) {
    	/*ScheduledExecutorService timer=Executors.newSingleThreadScheduledExecutor();
    	timer.scheduleAtFixedRate(new Runnable(){
    		public void run() {
    		   
    		}
    	}
    	, 1, 60, TimeUnit.SECONDS);*/
    	
    	
    	Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		Calendar c1=Calendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		c1.set(Calendar.MILLISECOND,0);
		long next=c1.getTimeInMillis();
		long dis=(next-new Date().getTime())/1000;
		SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss SS");
		System.out.println(format.format(c1.getTime()));
		System.out.println(format.format(new Date()));
		System.out.println(dis);
    }
}

class DateUtil {
	
	public static String genCurrentDayStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dayStr = sdf.format(new Date());
		return dayStr;
	}
	
	
	public static String genCurrentHourStr(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String dayStr = sdf.format(new Date());
		return dayStr;
	}
	
	public static int genCurrentHour(){
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String dayStr = sdf.format(new Date());
		return Integer.parseInt(dayStr);
	}
	
	/*public static void main(String[] args) {
		System.out.println(genCurrentDayStr());
		System.out.println(genCurrentHour());
	}*/

}