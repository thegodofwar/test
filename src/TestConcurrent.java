import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TestConcurrent {
	static int i=0;
	public static void main(String args[]) {
	ExecutorService threadPool=Executors.newCachedThreadPool();
	   for(;i<100000;i++) {
		   Runnable run=new Runnable() {
			   public void run() {
				    try {
				    	System.out.println(i);
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			   }
		   };
		   threadPool.execute(run); 
	    }
	   threadPool.shutdown();
		try {
			while(!threadPool.awaitTermination(10, TimeUnit.SECONDS)){
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	   System.out.println("娃哈哈。。。。");
	}
}
