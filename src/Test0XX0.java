import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Test0XX0 {
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
    public static void main(String argsp[]) {
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
			out.write("123456".getBytes("UTF-8"));
		 } catch (IOException e1) {
			e1.printStackTrace();
		 }
		 try {
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		 FileInputStream in=null;
		 try {
		    in=new FileInputStream(new File("d://outNum.txt"));
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		 }
		 byte[] buf=new byte["123456".getBytes().length];
		 try {
			in.read(buf, 0, buf.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(new String(buf));
		/* DataInputStream in=null;
		 try {
		    in=new DataInputStream(new FileInputStream(new File("d://outNum.txt")));
		 } catch (FileNotFoundException e) {
			e.printStackTrace();
		 }
		try {
			System.out.println(in.readInt());
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		 try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    }
