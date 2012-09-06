import java.util.concurrent.atomic.AtomicInteger;


public class TestAtomicInteger {

	/**
	 * @param liufukun
	 */
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		AtomicInteger i=new  AtomicInteger();
		while(true) {
			System.out.println(i.incrementAndGet());
			if(i.compareAndSet(10, 0)) {
				break;
			}
		}
		System.out.println(i.incrementAndGet());
		System.out.println(i.incrementAndGet());
	}

}
