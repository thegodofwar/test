import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;


public class TestNIOChanle {
   
	public static void main(String args[]) {
		File file=new File("d://20111116test.txt");
		System.out.println(file.length());
		FileChannel fc=null;
		try {
			fc=new RandomAccessFile(file,"rw").getChannel();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str="123";
		try {
			MappedByteBuffer mp=fc.map(FileChannel.MapMode.READ_WRITE, 1, str.length());
			String oldStr=Charset.forName("UTF-8").decode(mp).toString();
			fc.write(ByteBuffer.wrap((str).getBytes("UTF-8")),1);
			fc.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
