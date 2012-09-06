package WeakReference;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class testWeakReference {

	public static void main(String[] args) {

		System.out
				.println("=======================弱引用对象垃圾收集情况================");
        
		AtomicInteger testInt=new AtomicInteger(100);
		
		myWeakObject mwo = new myWeakObject("myweakobject1");

		WeakReference wr = new WeakReference(mwo);

		mwo = null;
		testInt=null;
		
		((myWeakObject) wr.get()).show();
		
		//System.out.println("testInt的值:"+testInt.get());

		System.out.println("第一次垃圾收集！！！");

		System.gc();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (wr.get() != null) {
			((myWeakObject) wr.get()).show();
		}

		System.out
				.println("==========================弱引用MAP=========================");
	    WeakHashMap whm = new WeakHashMap();
		//HashMap whm=new HashMap();
		myWeakObject mwo2 = new myWeakObject("myweakobject2");
		whm.put(mwo2, "XXXXX");
		mwo2 = null;
		((myWeakObject) whm.keySet().iterator().next()).show();
		System.out.println("第二次垃圾回收！！！");
		System.gc();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

			((myWeakObject) whm.keySet().iterator().next()).show();

	}

}

class myWeakObject {

	String mwname;

	public myWeakObject(String mwname) {
		super();
		this.mwname = mwname;
	}

	public void finalize() {
		System.out.println(mwname + "对象满足垃圾收集条件，被收集！！！");
	}

	public void show() {
		System.out.println(mwname + "对象还可以被调用！！！");
	}

}

