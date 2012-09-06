import java.io.*;


public class Test0XX {
	protected static byte[] getBytes(int value) {
		byte[] b = new byte[4];
		b[0] = (byte) ((value >> 24) & 0xFF);
		b[1] = (byte) ((value >> 16) & 0xFF);
		b[2] = (byte) ((value >> 8) & 0xFF);
		b[3] = (byte) ((value >> 0) & 0xFF);
		return b;
	}
	protected static int toInt(byte[] b) {
		return (((((int) b[3]) & 0xFF) << 32) + ((((int) b[2]) & 0xFF) << 40) + ((((int) b[1]) & 0xFF) << 48) + ((((int) b[0]) & 0xFF) << 56));
	}
	public static void main(String args[]) {
		byte[] ints=getBytes(567);
		for(byte i:ints) {
			System.out.println(i);
		}
		for(byte i:ints) {
			System.out.println((char)(i & 0xFF));
		}
		System.out.println(toInt(ints));
		System.out.println("**************************************");
		byte[] strs=null;
		try {
		   strs="567".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		  System.out.println("length:"+strs.length);
		  for(byte s:strs) {
			  System.out.println(s);
		  }
		 System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		  for(byte s:strs) {
			  System.out.println((char)(s & 0xFF));
		  }
		 System.out.println("_________________________________________________________");
		 /*DataOutputStream out=null;
		 try {
		    out=new DataOutputStream(new FileOutputStream(new File("d://outNum.txt")));
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		 }*/
		 FileOutputStream out=null;
		 try {
			    out=new FileOutputStream(new File("d://outNum.txt"));
			 } catch (FileNotFoundException e) {
				e.printStackTrace();
			 }
		 try {
			out.write(getBytes(1234),0,4);
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
		 try {
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 /*FileInputStream in=null;
		 try {
		    in=new FileInputStream(new File("d://outNum.txt"));
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		 }
		 byte[] buf=new byte[4];
		 try {
			in.read(buf, 0, buf.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(toInt(buf));*/
		 DataInputStream in=null;
		 try {
		    in=new DataInputStream(new FileInputStream(new File("d://outNum.txt")));
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		 }
		try {
			System.out.println(in.readInt());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
} 

