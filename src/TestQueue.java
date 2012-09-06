import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class TestQueue {
	public static void main(String args[]) {
	Queue<Integer> indexes = new ConcurrentLinkedQueue<Integer>();
	List<Integer> list=new ArrayList<Integer>();
	indexes.add(1);
	indexes.add(2);
	indexes.add(3);
	indexes.add(4);
	int size=indexes.size();
	/*for(int i=0;i<size;i++) {
		int tem=indexes.remove();
		System.out.println(tem);
		list.add(tem);
	  }*/
	System.out.println("size:"+indexes.size());
	while(indexes.size()!=0) {
		int tem=indexes.remove();
		System.out.println(tem);
		list.add(tem);
		System.out.println("size:"+indexes.size());
	}
	System.out.println("--------------------------------");
	for(int i: list) {
		System.out.println(i);
	}
	System.out.println("*****************************");
	for(int i:indexes) {
		System.out.println(i);
	}
	}
	
}
