package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest1 {
	
   public static void main(String args[]) {
	   CountDownLatch latch=new CountDownLatch(1);
	   new Thread(new MyThread(latch)).start();
	   try {
		System.out.println("latch await...");
		latch.await();
		System.out.println("latch await again...");
		latch.await();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	   System.out.println("Process is Over!");
   }
   
}
class MyThread implements Runnable {
	public CountDownLatch latch;
	MyThread(CountDownLatch latch) {
		this.latch=latch;
	}
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("latch countDown...");
		latch.countDown();
	}
}