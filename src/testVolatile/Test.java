package testVolatile;

public class Test {
   
	public static volatile int count;
	
	public static void main(String args[]) {
		for(int i=0;i<10;i++) {
			new Thread(new Runnable() {
				public void run() {
					for(int j=0;j<30;j++) {
					  System.out.println(Thread.currentThread().getName()+" j="+j+" count="+(count++));
					}
				}
			}).start();
		}
	}
	
}
