package testScheduledExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
   public static void main(String args[]) {
	   ScheduledExecutorService scheduledPool=Executors.newScheduledThreadPool(2);
	   scheduledPool.scheduleWithFixedDelay(new Runnable() {
		   public void run() {
			   System.out.println(Thread.currentThread().getName()+"A");
		   }
	   }, 5, 5, TimeUnit.MILLISECONDS);
	   scheduledPool.scheduleWithFixedDelay(new Runnable() {
		   public void run() {
			   System.out.println(Thread.currentThread().getName()+"B");
		   }
	   }, 5, 5, TimeUnit.MILLISECONDS);
	   scheduledPool.scheduleWithFixedDelay(new Runnable() {
		   public void run() {
			   System.out.println(Thread.currentThread().getName()+"C");
		   }
	   }, 5, 5, TimeUnit.MILLISECONDS);
   }
}
