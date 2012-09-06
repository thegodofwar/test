package CrawlTest;

import org.apache.http.client.HttpClient;


public class Crawl {
	
    public static void main(String args[]) {
    	 int crawlNum=10000;
    	 HttpClient httpclient=HttpCrawler.createMultiThreadClient(400, 80,6000,9000);
    	 for(int i=0;i<crawlNum;i++) {
    		 System.out.println(HttpCrawler.crawl((i+1)+"", httpclient,"http://www.obooko.cn","gb18030"));
    		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	 }
    }
}
