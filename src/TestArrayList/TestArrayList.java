package TestArrayList;

import java.util.ArrayList;
import java.util.List;

public class TestArrayList {
  
	public static void main(String args[]) {
		//int maxNum=Integer.parseInt(args[0]);
		List<byte[]> list=new ArrayList<byte[]>();
		for(int i=0;i<300;i++) {
			list.add(new byte[1024*1024]);
			System.out.println(i+"add one successfully!");
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("add finished!");
		System.out.println("max:"+Runtime.getRuntime().maxMemory()+" total:"+Runtime.getRuntime().totalMemory()+" free:"+Runtime.getRuntime().freeMemory());
		/*try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		int j=0;
		while(!list.isEmpty()) {
			 list.remove(0);
			 System.out.println(j+"remove one successfully!");
			 j++;
			 /*System.out.println("max:"+Runtime.getRuntime().maxMemory()+" total:"+Runtime.getRuntime().totalMemory()+" free:"+Runtime.getRuntime().freeMemory());
			 try {
					Thread.sleep(1000);
				 } catch (InterruptedException e) {
					e.printStackTrace();
			 }
			list.add(new byte[1024*900]); 		 
		    System.out.println("max:"+Runtime.getRuntime().maxMemory()+" total:"+Runtime.getRuntime().totalMemory()+" free:"+Runtime.getRuntime().freeMemory());		 */
		}
		System.out.println("remove finished!");
		System.out.println("max:"+Runtime.getRuntime().maxMemory()+" total:"+Runtime.getRuntime().totalMemory()+" free:"+Runtime.getRuntime().freeMemory());
	}
	
}
