package testDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
   
	
	public static void main(String args[]) {
		Calendar nowC=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
		Date updateTime=null;
		try {
			updateTime=sdf.parse("08-12");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		nowC.setTime(updateTime);
		SimpleDateFormat newSdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(nowC.get(Calendar.YEAR)==1970) {
		   nowC.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
		}
		if(nowC.getTime().after(new Date())) {
		   nowC.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR)-1);
		}
		System.out.println(newSdf.format(nowC.getTime()));
		
	}
	
}
