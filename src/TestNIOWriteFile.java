import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TestNIOWriteFile {
   
	public static void main(String args[]) {
		try {
			File f=new File("d://a.txt");
			System.out.println("f.length="+f.length());
			FileWriter fw=new FileWriter(f,true);
			String str=" nnn ccc  ";
			System.out.println("str.length="+str.length());
			fw.append(str,0,str.length());
			fw.flush();
			//fw.close();
			System.out.println(f.length());
			System.out.println("b"+new StringBuilder().toString()+"a");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
