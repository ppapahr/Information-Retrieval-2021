package Command;

import Model.FileSearcher;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;


public class Search implements ActionListener {
	
	private String indexDirectoryPath = System.getProperty("user.dir") + File.separator + "dir";
	private String query;
	private String field;

	@Override
	public void actionPerformed(ActionEvent e) {		
		FileSearcher searcher = new FileSearcher(indexDirectoryPath);

		ArrayList<Document> documents = searcher.search(this.field, this.query);
			
		MainWindowCommander mwc = MainWindowCommander.getInstance();
		MainWindow mainWindow = mwc.getMainWindow();
			
		Screen resultsScreen = mainWindow.getScreen("Results");
			
		((ResultsScreen) resultsScreen).createResults(documents, this.query);
			
		printStats(documents);
			
		searcher.closeReader();
		
	}
	
	public void update(String field, String query) {
		this.field = field;
		this.query = query;
	}
	
	private void printStats(ArrayList<Document> documents) {
		
		System.out.println("Query \"" + query + "\" matches " + documents.size() + " document(s) in field \"" + field + "\".");
	}
	
	
	

}
