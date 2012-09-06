import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;


public class TestLuceneDelete6 {
	/**
	 * @author liufukun
	 */
	public static void main(String[] args) {
		IndexWriter indexWriter=null;
		try {
			indexWriter = new IndexWriter(FSDirectory.open(new File("d://text_lucene_delete")), new IKAnalyzer(false), true,
					IndexWriter.MaxFieldLength.LIMITED);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		indexWriter.setMaxMergeDocs(9999999);
		indexWriter.setMergeFactor(300);
		indexWriter.setMaxBufferedDocs(300);
		
		Document doc1=new Document();
		doc1.add(new Field("id","1",Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc1.add(new Field("content","how are those you liufukun thegodofwar",Field.Store.YES, Field.Index.ANALYZED));
		Document doc2=new Document();
		doc2.add(new Field("id","3",Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc2.add(new Field("content","the best words is wahaha",Field.Store.YES,Field.Index.ANALYZED));
		Document doc3=new Document();
		doc3.add(new Field("id","6",Field.Store.YES,Field.Index.NOT_ANALYZED));
		doc3.add(new Field("content","welcome to Beijing",Field.Store.YES, Field.Index.ANALYZED));
		try {
			indexWriter.addDocument(doc1);
			indexWriter.addDocument(doc2);
			indexWriter.addDocument(doc3);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			indexWriter.optimize();
			indexWriter.commit();
            indexWriter.close();
		} catch (CorruptIndexException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		IndexReader reader=null;
		try {
			reader=IndexReader.open(FSDirectory.open(new File("d://text_lucene_delete"), null),false);
		} catch (CorruptIndexException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		CountDownLatch latch=new CountDownLatch(2);
		new Thread(new SearchConcurrentNew(reader,"Beijing",latch)).start();
		new Thread(new SearchConcurrentNew(reader,"wahaha",latch)).start();
		try {
			latch.await();
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		
	}

}

class SearchConcurrentNew implements Runnable {
	IndexSearcher searcher;
	String content;
	CountDownLatch latch;
	SearchConcurrentNew(IndexReader reader,String content,CountDownLatch latch) {
		searcher=new IndexSearcher(reader);
		this.content=content;
		this.latch=latch;
	}
	public void run() {
		Query cq=null;
		try {
			cq = IKQueryParser.parse("content", content);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		TopDocs hits=null;
		try {
			 hits = searcher.search(cq, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}   
		for (int i = 0; i < hits.scoreDocs.length; i++) {   
            ScoreDoc sdoc = hits.scoreDocs[i];   
            System.out.println(Thread.currentThread().getName()+" score:"+sdoc.score);
            Document doc=null;
			try {
				doc = searcher.doc(sdoc.doc);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}   
            System.out.println(Thread.currentThread().getName()+" "+doc.get("content"));               
        }
		latch.countDown();
	}
}