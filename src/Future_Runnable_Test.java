import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//用Future实现Runnable返回值的方法得到某文件夹中（包括子文件夹）共多少个文件
public class Future_Runnable_Test {
    private final String filePath = "e:\\resin";
    private final String keywords = "java";
    Future_Runnable_Test(){
        FindByRunnable r = new FindByRunnable(new File(filePath), keywords);
        FutureTask<FindByRunnable> ft = new FutureTask<FindByRunnable>(r,r);
        ft.run();
        try {
            System.out.println("文件夹"+filePath+"中包含关键字"+keywords+"的文件数量为："+ft.get().count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Future_Runnable_Test();
    }
}
class FindByRunnable implements Runnable{
    private File filePath;
    private String keywords;
    public int count;
    FindByRunnable(File filePath, String keywords){
        this.filePath = filePath;
        this.keywords = keywords;
    }
    public void run(){
    	ReadByRunnable instance= new ReadByRunnable(filePath,keywords);
    	instance.statistics(filePath, keywords);
    	count=instance.num;
        System.out.println("count1 is:"+count);
    }
}
class ReadByRunnable{
    private boolean isHave = false;
    public File file;
    public String keywords;
    public int num;
    boolean getIsHave(){
        return isHave;
    }
    ReadByRunnable(File file, String keywords){
    	this.file=file;
    	this.keywords=keywords;
    }
    
    public void statistics(File file,String keywords) {
    	if(file.isDirectory()) {
    		File files[]=file.listFiles();
    		for(File f:files) {
    			statistics(f,keywords); 
    		}
    	} else {
    	   try {
             Scanner s = new Scanner(file);
             int n = 0;
             while(s.hasNextLine()){
                 String str = s.nextLine();
                 n++;
                 if(str.contains(keywords)){
                     System.out.println("文件："+file.getAbsolutePath()+"，第"+n+"行，内容："+str);
                     isHave = true;
                     break;
                 }
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         if(isHave==true) {
        	 num++;
         }
     }
    }
    
}


