package WeakReference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class Test {

	public static void main(String args[]) {
		WeakHashMap map = new WeakHashMap();
		//HashMap map=new HashMap();
		map.put(new String("1"),"1");
		map.put("2","2");
		String s = new String("3");
		map.put(s, "3");
		myWeakObject mwo2 = new myWeakObject("myweakobject2");
		map.put(mwo2,"4");
		//s=null;
		mwo2=null;
		System.gc();
		while (map.size() > 0) {
		   try {
			  Thread.sleep(500);
			} catch (InterruptedException ignored) {
		    }
			
		   System.out.println("Map Size:"+map.size());

		   System.out.println(map.get("1"));

		   System.out.println(map.get("2"));

	       System.out.println(map.get("3"));
	       
	       System.out.println(map.get(mwo2));
	       s=null;
	       //mwo2=null;

	       System.gc();
		}

	}
}
