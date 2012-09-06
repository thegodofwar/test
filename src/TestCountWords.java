import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TestCountWords {
	public static void main(String[] args) {
		File wf = new File("d://a.txt");
		System.out.println("file's length="+wf.length());
		final CountWords cw1 = new CountWords(wf, 0, wf.length()/2);
		final CountWords cw2 = new CountWords(wf, wf.length()/2, wf.length());
		final Thread t1 = new Thread(cw1);
		final Thread t2 = new Thread(cw2);
		//开辟两个线程分别处理文件的不同片段
		t1.start();
		t2.start();
		Thread t = new Thread() {
			public void run() {
				while(true) {
					//两个线程均运行结束
					if(Thread.State.TERMINATED==t1.getState() && Thread.State.TERMINATED==t2.getState()) {
						//获取各自处理的结果
						HashMap<String, Integer> hMap1 = cw1.getResult();
						HashMap<String, Integer> hMap2 = cw2.getResult();
						//使用TreeMap保证结果有序
						TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
						//对不同线程处理的结果进行整合
						tMap.putAll(hMap1);
						tMap.putAll(hMap2);
						//打印输出，查看结果
					    for(Map.Entry<String,Integer> entry : tMap.entrySet()) {
					        String key = entry.getKey();  
					        int value = entry.getValue();  
					        System.out.println("***************************");
					        System.out.println(key+":\t"+value);  
					    }
					    //将结果保存到文件中
					    mapToFile(tMap, new File("d://r.txt"));
					    return;
					}
				}
			}
		};
		t.start();
	}
	//将结果按照 "单词：次数" 格式存在文件中
	private static void mapToFile(Map<String, Integer> src, File dst) {
	    try {
	    	//对将要写入的文件建立通道
	    	FileChannel fcout = new FileOutputStream(dst).getChannel();
	    	//使用entrySet对结果集进行遍历
			for(Map.Entry<String,Integer> entry : src.entrySet()) {
		        String key = entry.getKey();
		        int value = entry.getValue();
		        //将结果按照指定格式放到缓冲区中
		        ByteBuffer bBuf = ByteBuffer.wrap((key+":\t"+value).getBytes());
		        fcout.write(bBuf);
		        bBuf.clear();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class CountWords implements Runnable {
	
	private FileChannel fc;
	private FileLock fl;
	private MappedByteBuffer mbBuf;
	private HashMap<String, Integer> hm;
	
	public CountWords(File src, long start, long end) {
		try {
			//得到当前文件的通道
			fc = new RandomAccessFile(src, "rw").getChannel();
			//锁定当前文件的部分
			fl = fc.lock(start, end, false);
			//对当前文件片段建立内存映射，如果文件过大需要切割成多个片段
			mbBuf = fc.map(FileChannel.MapMode.READ_ONLY, start, end);
			//创建HashMap实例存放处理结果
			hm = new HashMap<String,Integer>();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		String str = Charset.forName("UTF-8").decode(mbBuf).toString();
		//使用StringTokenizer分析单词
		StringTokenizer token = new StringTokenizer(str);
		String word;
		while(token.hasMoreTokens()) {
			//将处理结果放到一个HashMap中，考虑到存储速度
			word = token.nextToken();
			if(null != hm.get(word)) {
				hm.put(word, hm.get(word)+1);
			} else {
				hm.put(word, 1);
			}
		}
		try {
			//释放文件锁
			fl.release();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//获取当前线程的执行结果
	public HashMap<String, Integer> getResult() {
		return hm;
	}
}