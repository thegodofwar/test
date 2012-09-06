import java.text.SimpleDateFormat;
import java.util.Date;


public class TestMssLog {
   public static void main(String args[]) {
	   String logStr="Dec  5 10:39:04 mss1 mss[7614]: [INFO]  mss_ops.cpp[2517]       [3BB6D0DE430E99]        ip=192.168.0.102        mssid=1 action=index_update_dirid   stat=318,1,0,0,0,success        usage=173search usrid=11111111     emailid=2222222       kvkey=4295519181        src_dirid=1 dst_dirid=4     display_time=333333 timeelapse=0.000074";
	   String timestr = logStr.substring(0,15).trim();
	   System.out.println(timestr);
	   System.out.println(logStr);
	   long date = System.currentTimeMillis();
	   date = Date.parse(timestr+" "+ (new Date().getYear()+1900) );
	   System.out.println(date);
	   SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   System.out.println(sf.format(new Date(date)));
	   System.out.println(timestr+" "+ (new Date().getYear()+1900));
   }
}
