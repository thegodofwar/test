package testArrayCopy;

public class Test {
    
	public static void main(String args[]) {
		int a[]=new int[]{1,2,3,4,5,6,7};
		int b[]=new int[]{8,9,10};
		int c[]=new int[a.length+b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		for(int i:a) {
			System.out.print(i+" ");
		}
		System.arraycopy(b, 0, c,a.length,b.length);
		System.out.println("******************");
		for(int j:c) {
			System.out.print(j+" ");
		}
	}
	
}
