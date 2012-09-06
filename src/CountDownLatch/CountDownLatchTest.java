package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {//该实例再次证明认识程序原理要从实践出发，让运行结果说话，不能主观臆断

	public static void main(String[] args) {//latch的作用是牵一发而动全身,它具有的是瞬时控制效应,它不可循环利用
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*1000));
						System.out.println("线程" + Thread.currentThread().getName() + 
								"正准备接受命令");						
						cdOrder.await();
						cdOrder.await();
						cdOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + 
						"已接受命令");		
						cdOrder.await();
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"回应命令处理结果");						
						cdAnswer.countDown();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		try {
			Thread.sleep((long)(Math.random()*1000));
		
			System.out.println("线程" + Thread.currentThread().getName() + 
					"即将发布命令");						
			cdOrder.countDown();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已发送命令，正在等待结果");	
			cdAnswer.await();
			System.out.println("线程" + Thread.currentThread().getName() + 
			"已收到所有响应结果");	
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();

	}
}

