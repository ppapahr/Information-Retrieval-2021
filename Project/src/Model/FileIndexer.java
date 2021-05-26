package Model;

import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;


public class FileIndexer {
	
	private String indexPath;
	private IndexWriter writer;
	
	public FileIndexer(String indexPath) throws IOException {
		this.indexPath = indexPath;
		
		// Use that directory to store everything that is necessary for the index.
		Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));
				
		// An Analyzer builds TokenStreams, which analyze text.
		// It thus represents a policy for extracting index terms from text.
		Analyzer analyzer = new StandardAnalyzer();

		// Holds all the configuration that is used to create an IndexWriter.
		IndexWriterConfig writerConfiguration = new IndexWriterConfig(analyzer);
				
		// IndexWriter will create a new index if one does not already exist
		// at the provided path, otherwise open the existing index.
		this.writer = new IndexWriter(indexDirectory, writerConfiguration);
		
	}
	
	public void closeWriter() throws CorruptIndexException, IOException {
		this.writer.close();
	}
	
	
	private Document getDocument(File article) throws IOException {
		
		Document document = new Document();
		String articleTitle = getTitle(article);
		String articleBody = getBody(article);
		String articleAuthors = getAuthors(article);
		String articleAbstract = getAbstract(article);
		
		if (articleBody == null) {
			System.out.println("Can't read file " + article.getName());
			throw new IOException();
		}
		
		
		if(articleTitle != null) {
			Field title = new TextField("Title", articleTitle, Field.Store.YES);
			document.add(title);
		}
		
		if(articleAuthors != null) {
			Field authors = new TextField("Authors", articleAuthors, Field.Store.YES);
			document.add(authors);
		}
		
		if(articleAbstract != null) {
			Field abstractTxt = new TextField("Abstract", articleAbstract, Field.Store.YES);
			document.add(abstractTxt);
		}
		
		
		Field body = new TextField("Body", articleBody, Field.Store.YES);
		
		Field path = new StoredField("Path", article.getAbsolutePath());
		
		document.add(body);
		document.add(path);

		return document;
	}
	
	
	public static String getTitle(File article) throws IOException {
		FileReader fr=new FileReader(article);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		int counter = 0;
		while((line=br.readLine())!=null)  
		{ 
		if (counter == 0) {
			sb.append(line); 
		}  
		counter ++;
		}  
		fr.close(); 
		if (sb.toString().equals("empty")) {
			return null;  
		}
		return sb.toString();
	}
	
	private String getBody(File article) throws IOException {
		FileReader fr=new FileReader(article);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		int counter = 0;
		while((line=br.readLine())!=null)  
		{ 
		if (counter == 3) {
			sb.append(line); 
		}  
		counter ++;
		}  
		fr.close(); 
		if (sb.toString().equals("empty")) {
			return null;  
		}
		return sb.toString();
	}
	
	private String getAuthors(File article) throws IOException {
		FileReader fr=new FileReader(article);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		int counter = 0;
		while((line=br.readLine())!=null)  
		{ 
		if (counter == 1) {
			sb.append(line); 
		}  
		counter ++;
		}  
		fr.close(); 
		if (sb.toString().equals("empty")) {
			return null;  
		}
		return sb.toString();
	}
	
	private String getAbstract(File article) throws IOException {
		FileReader fr=new FileReader(article);
		BufferedReader br=new BufferedReader(fr);
		StringBuffer sb=new StringBuffer();
		String line;
		int counter = 0;
		while((line=br.readLine())!=null)  
		{ 
		if (counter == 2) {
			sb.append(line); 
		}  
		counter ++;
		}  
		fr.close(); 
		if (sb.toString().equals("empty")) {
			return null;  
		}
		return sb.toString();
	}
	
	
	private void indexArticle(File article) throws IOException {
		
		Document document = getDocument(article);
		this.writer.addDocument(document);
	}

	
	public int createIndex(String documentPath) throws IOException {

		File[] files = new File(documentPath).listFiles();
		
		for (File f : files) {
			
			if (f.exists() && f.canRead())
				indexArticle(f);
		}
		
		IndexWriter.DocStats stats = this.writer.getDocStats();
		return stats.numDocs;
	}
	
	
	/*/
	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\Philip\\Desktop\\search-engine\\Information-Retrieval-2021\\Project\\dir";
		FileIndexer test = new FileIndexer(path);
		test.createIndex("C:\\Users\\Philip\\Desktop\\search-engine\\Information-Retrieval-2021\\Project\\txt_Documents");
		
	}
	/*/
	
	

}
