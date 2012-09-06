
public class TestExceptionReturn {
    
	public static void main(String args[]) {
		D tmp=new D(0).test("liufukun");
		System.out.println(tmp);
	}
}

class D {
	public int i;
	D(int i) {
	   this.i=i;
	}
	public D test(String str) {
		D d=null;
		int ti=-1;
		try{
		  ti=Integer.parseInt(str);
		  d=new D(ti);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
		//d=new D(ti);
		return d;
	}
	
  @Override
  public String toString() {
	  return i+"";
  }
}