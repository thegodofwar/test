import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;


public class TestThread {
   public static boolean isEnd=false;
   @SuppressWarnings("unchecked")
   public static void main(String args[]) {
	   Thread threads[]=new Thread[10];
	   LinkedBlockingQueue[] queues=new LinkedBlockingQueue[10];
	   for(int i=0;i<queues.length;i++) {
			queues[i]=new LinkedBlockingQueue<String>();
		}
	   CountDownLatch latches=new CountDownLatch(10);
		for(int i=0;i<threads.length;i++) {
			threads[i]=new Thread(new ThreadClass(new TestThread(),queues[i],latches));
			threads[i].start();
		}
		
		int cursor=0;
		String line=null;
		BufferedReader stdIn=null;
		try {
			stdIn = new BufferedReader(new FileReader("d://test.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while ((line = stdIn.readLine()) != null) {
				System.out.println(Thread.currentThread().getName()+"-Thread:"+"line="+line);
				try {
					queues[cursor%10].put(line);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cursor++;
			}
		  isEnd=true;
	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		  for(int i=0;i<queues.length;i++) {
			   try {
				queues[i].put("READ OVER");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		  System.out.println("isEnd="+isEnd+"******************************************************");
		  stdIn.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
   try {
		latches.await();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println("结束啦......");
   }
}
class ThreadClass implements Runnable {
	public TestThread test;
	public LinkedBlockingQueue<String> queue;
	public CountDownLatch latches;
	public ThreadClass(TestThread test,LinkedBlockingQueue<String> queue,CountDownLatch latches) {
		this.test=test;
		this.queue=queue;
		this.latches=latches;
	}
	@SuppressWarnings("static-access")
	public void run() {
		System.out.println(Thread.currentThread().getName()+" is Running...");
		while(queue.size()>0||test.isEnd==false) {
			/*if(queue.size()==0&&test.isEnd==true) {
				break;
			}*/
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			String readStr=null;
			try {
				readStr=queue.take();
				if(readStr!=null&&readStr.equals("READ OVER")) {
					System.out.println(Thread.currentThread().getName()+" Getted The Over Sign.");
					break;
				}else {
				  System.out.println(Thread.currentThread().getName()+"该行内容为："+readStr);
				}
				//Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		latches.countDown();
	}
}