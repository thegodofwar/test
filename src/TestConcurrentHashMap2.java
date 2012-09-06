import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestConcurrentHashMap2 {
	public static void main(String args[]) {
		Map<Integer,String> map=new ConcurrentHashMap<Integer,String>();
		CountDownLatch latch=new CountDownLatch(2);
		Lock lock=new ReentrantLock();
		new Thread(new Run1(lock,latch,map)).start();
		new Thread(new Run2(lock,latch,map)).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Map.Entry<Integer, String> entry:map.entrySet()) {
	    	System.out.println(entry.getKey()+" : "+entry.getValue());
	      }
	}
}

class Run1 implements Runnable {
	public Lock lock;
	public CountDownLatch latch;
	public Map<Integer,String> map;
	Run1(Lock lock,CountDownLatch latch,Map<Integer,String> map) {
		this.lock=lock;
		this.latch=latch;
		this.map=map;
	}
	public void run() {
		for(int i=0;i<15;i++) {
			  lock.lock();
			  try {
			   map.put(i, Thread.currentThread().getName()+i);
		      } finally {
			   lock.unlock();
	     	  }
			  try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		latch.countDown();
	}
}

class Run2 implements Runnable {
	public Lock lock;
	public CountDownLatch latch;
	public Map<Integer,String> map;
	Run2(Lock lock,CountDownLatch latch,Map<Integer,String> map) {
		this.lock=lock;
		this.latch=latch;
		this.map=map;
	}
	public void run() {
		while(true) {
			if(map.size()==5) {
				for(Map.Entry<Integer, String> entry:map.entrySet()) {
			    	System.out.println("Test :"+entry.getKey()+" : "+entry.getValue());
			    	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			    }
				 lock.lock();
				 try {
				 System.out.println(map.size());
				 map.clear();
				 } finally {
					 lock.unlock();
				 }
				 break;
			 }
		}
	  latch.countDown();
	}
}
