import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.StaleReaderException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;


public class TestLuceneDelete3 {
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
		try {
			indexWriter.addDocument(doc1);
			indexWriter.addDocument(doc2);
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
		
		/*IndexSearcher searcher=null;
		try {
			 searcher=new IndexSearcher(IndexReader.open(FSDirectory.open(new File("d://text_lucene_delete"),null)));
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		IndexReader r=null;
		try {
		   r=IndexReader.open(FSDirectory.open(new File("d://text_lucene_delete"),null));
		} catch (CorruptIndexException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		{
			IndexWriter indexWriterN=null;
			try {
				indexWriterN = new IndexWriter(FSDirectory.open(new File("d://text_lucene_delete")), new IKAnalyzer(false), false,
						IndexWriter.MaxFieldLength.LIMITED);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (LockObtainFailedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			indexWriterN.setMaxMergeDocs(9999999);
			indexWriterN.setMergeFactor(300);
			indexWriterN.setMaxBufferedDocs(300);
			
			Document doc1N=new Document();
			doc1N.add(new Field("id","6",Field.Store.YES,Field.Index.NOT_ANALYZED));
			doc1N.add(new Field("content","Welcome to Beijing",Field.Store.YES, Field.Index.ANALYZED));
			try {
				indexWriterN.addDocument(doc1N);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				indexWriterN.optimize();
				indexWriterN.commit();
	            indexWriterN.close();
			} catch (CorruptIndexException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Query cq=null;
		try {
			cq = IKQueryParser.parse("content", "thegodofwar wahaha Beijing");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		IndexSearcher  searcher=new IndexSearcher(r);
		
		TopDocs hits=null;
		try {
			 hits = searcher.search(cq, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}   
		for (int i = 0; i < hits.scoreDocs.length; i++) {   
            ScoreDoc sdoc = hits.scoreDocs[i];   
            System.out.println("score:"+sdoc.score);
            Document doc=null;
			try {
				doc = searcher.doc(sdoc.doc);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}   
            System.out.println(doc.get("content"));               
        }          

	}

}
