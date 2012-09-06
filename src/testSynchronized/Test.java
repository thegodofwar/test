package testSynchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
   public static Lock o=new ReentrantLock();
   public static Test t=new Test();
	public synchronized void t1() {
		 System.out.println("t1");
	}
	
	public synchronized void t2() {
		  t1();
		  System.out.println("t2");
	}
	
	public synchronized void sys() {
		System.out.println("WAAHAHAHA");
		new Thread(new Runnable() {
			public void run() {
				System.out.println("开始睡...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("睡醒");
				System.out.println("RUN..........");
			}
		}
				).start();
		System.out.println("走自己的路...");
	}
	
	public static void main(String args[]) {
		///Test t=new Test();
		///t.t2();
		new Thread(new Runnable() {
			public void run() {
				t.sys();
			}
		}
				).start();
		new Thread(new Runnable() {
			public void run() {
				t.t2();
			}
		}
				).start();
	}
}
