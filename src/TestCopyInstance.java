import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class TestCopyInstance {
   
	public static void main(String args[]) {
		File a=new File("d://b.txt");
		FileWriter fw=null;
		try {
			fw=new FileWriter(a);
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestA t1=new TestA(fw);
		FileWriter fww=fw;
		TestA t2=new TestA(fww);
		t1.write("HHHHH,I love YOU!!!");
		t1.close();
		t2.write("OK,thx!");
		t2.close();
	}
	
}

class TestA {
	FileWriter f;
	public TestA(FileWriter f) {
		this.f=f;
	}
	void write(String str) {
		try {
			f.write(str);
			f.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void close() {
		if(f!=null) {
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}