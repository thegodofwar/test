import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;


public class TestByteBuffer {
   
	public static void main(String args[]) throws Exception{
		System.out.println(Arrays.toString(new int[]{1,3,5}));
		ByteBuffer buf=ByteBuffer.allocate(2);
		FileChannel fc=new FileInputStream(new File("d://byte.txt")).getChannel();
		FileChannel fo=new FileOutputStream(new File("d://newByte.txt")).getChannel();
		buf.clear();
		System.out.println("buf.position start:"+buf.position());
		int count=-2;
		while ((count=fc.read(buf))>0||buf.position()!=0) {
			System.out.println("count:"+count);
			buf.flip();
			fo.write(buf);
			System.out.println(buf.limit());//最后一次读时buf.capacity()是2，而buf.limit()是1
			System.out.println((char)buf.get(0)+","+(char)buf.get(buf.limit()-1));
			System.out.println("buf.position1:"+buf.position());
			//System.out.println(Charset.forName("UTF-8").decode(buf).toString());
			buf.compact();//compact()和clear()效果一样
			System.out.println("buf.position2:"+buf.position());
			//System.out.println("**:"+Charset.forName("UTF-8").decode(buf).toString());
		}
	}
}
