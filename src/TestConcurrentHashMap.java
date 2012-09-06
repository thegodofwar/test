import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestConcurrentHashMap {
	Map<Integer,String> map=new ConcurrentHashMap<Integer,String>();
	Lock lock=new ReentrantLock();
	public TestConcurrentHashMap() {
		//lock.lock();
		System.out.println("start");
	    //try {
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<15;i++) {
				  map.put(i, Thread.currentThread().getName()+i);
				}
			}
		}
	   ).start();
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
				 if(map.size()==5) {
					 //do something
					 try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					 map.clear();
					 return;
				 }
				}
			}
		}
		).start();
	    /*} finally {
	    	lock.unlock();
	    }*/
	    
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   /* lock.lock();
	     try {*/
	      for(Map.Entry<Integer, String> entry:map.entrySet()) {
	    	System.out.println(entry.getKey()+" : "+entry.getValue());
	      }
	     /*} finally {
	    	 lock.unlock();
	     }*/
	}
	public static void main(String args[]) {
		 new TestConcurrentHashMap();
	}
}
