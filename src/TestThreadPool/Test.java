package TestThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Test {
	
	public static void main(String args[]) {
		 int threadNum=Runtime.getRuntime().availableProcessors()*3;
		 ExecutorService threadPool=Executors.newFixedThreadPool(threadNum);
		 for(int i=0;i<100;i++) {
	     final int ii=i;
		 threadPool.execute(new Runnable(){
			public void run() {
				System.out.println(Thread.currentThread().getName()+" index="+ii);
			}
		 });
		 }
		 try {
			while(!threadPool.awaitTermination(10,TimeUnit.MILLISECONDS)) {
				 
			 }
		 } catch (InterruptedException e) {
			e.printStackTrace();
		 }
		 threadPool.execute(new Runnable() {
			 public void run() {
			 System.out.println("END...........");
			 }
		 });
	}
	
}

