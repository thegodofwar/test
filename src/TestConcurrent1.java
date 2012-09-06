import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


public class TestConcurrent1 {
   public static void main(String args[]) {
	   System.out.println("开始....");
        MySet mySet=new MySet();
        System.out.println(mySet.put(1));
        System.out.println(mySet.put(2));
        System.out.println(mySet.put(3));
        System.out.println(mySet.put(4));
        System.out.println(mySet.getAll());
   }
}

class MySet {
	Set<Integer> concurrentHashSet=Collections.newSetFromMap(new ConcurrentHashMap<Integer,Boolean>());
	Semaphore semaphore=new Semaphore(3,true);
	
	public boolean put (int i) {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean isSuccess=concurrentHashSet.add(i);
		if(isSuccess==false||i==3) {
			semaphore.release();
		}
		return isSuccess;
	}
	
	public String getAll() {
		String result="";
		for(int i:concurrentHashSet) {
			result+=i+",";
		}
		return result;
	}
}