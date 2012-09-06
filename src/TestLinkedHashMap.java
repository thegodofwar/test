import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class TestLinkedHashMap {
	public static void main(String args[]) {
      CacheMap<Integer,String> myCache=new CacheMap<Integer,String>(5);
      myCache.put(1,"a");
      myCache.put(2,"b");
      myCache.put(3,"c");
      myCache.put(4,"d");
      myCache.put(5,"e");
      myCache.put(3,"f");
      myCache.put(6,"h");
      System.out.println(myCache.get(1));
      System.out.println(myCache.get(1));
      System.out.println(myCache.get(1));
      
      for(Map.Entry<Integer, String> entry:myCache.entrySet()) {
    	  System.out.println(entry.getKey()+":"+entry.getValue());
      }
	}
	
}

class CacheMap<K,V> extends LinkedHashMap<K,V> {
	public final int maxCapacity;  
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;  
    public Lock lock = new ReentrantLock(); 
    
    public CacheMap(int maxCapacity) {  
        super(maxCapacity, DEFAULT_LOAD_FACTOR,false);  
        this.maxCapacity = maxCapacity;  
    }
    
    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> eldest) {	
          return size()>maxCapacity;    	
    }
    
    @Override
    public V put(K key,V value) {
    	 lock.lock();  
         try {  
             return super.put(key, value);  
         } finally {  
             lock.unlock();  
         }  
    }
    
    @Override
    public V get(Object key) {  
        lock.lock();  
        try {  
            return super.get(key);  
        } finally {  
            lock.unlock();  
        }  
    }  
    
    @Override
    public boolean containsKey(Object key) {  
        lock.lock();  
        try {  
            return super.containsKey(key);  
        } finally {  
            lock.unlock();  
        }  
    }  
}