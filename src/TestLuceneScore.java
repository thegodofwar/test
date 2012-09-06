import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;


public class TestLuceneScore {

	/**
	 * @author liufukun
	 */
	public static void main(String[] args) {
		IndexWriter indexWriter=null;
		try {
			indexWriter = new IndexWriter(FSDirectory.open(new File("d://text_lucene_score")), new IKAnalyzer(false), true,
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
		doc1.add(new Field("content","how are those you liufukun thegodofwar",Field.Store.YES, Field.Index.ANALYZED));
		Document doc2=new Document();
		doc2.add(new Field("content","liufukun",Field.Store.YES,Field.Index.ANALYZED));
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
		
		IndexSearcher searcher=null;
		try {
			 searcher=new IndexSearcher(IndexReader.open(FSDirectory.open(new File("d://text_lucene_score"),null)));
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*QueryParser parser=new QueryParser(Version.LUCENE_30,"content",new IKAnalyzer(false));
		  Query q=null;
		*/
		MultiPhraseQuery q=new MultiPhraseQuery();
		q.setSlop(2);
		//String keyWords="how are you liufukun thegodofwar";
		/*try {
			 q=parser.parse(keyWords);
		} catch (ParseException e) {
			e.printStackTrace();
		} */
		// IKSegmentation segmentation = new IKSegmentation(new StringReader(keyWords), true);
        /* Lexeme lm =  null;
         List<String> queryList = new ArrayList<String>();
         try {
			while ((lm = segmentation.next()) != null){
			 	queryList.add(lm.getLexemeText());
			 }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(String sw:queryList) {
			System.out.print(sw+"---");
		}*/
		q.add(new Term("content","how"));
		q.add(new Term[]{new Term("content","you"),new Term("content","theey"),new Term("content","those")});
		q.add(new Term("content","thegodofwar"));
		TopDocs hits=null;
		try {
			 hits = searcher.search(q, 100);
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
