package Main;


import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.simple.parser.ParseException;

import Command.MainWindowCommander;
import Model.FileIndexer;
import View.MainWindow;
import Model.JsonParser;

public class Main {

	private String indexPath = System.getProperty("user.dir") + File.separator + "dir";
	private String txtPath = System.getProperty("user.dir") + File.separator + "txt_Documents";
	private String projectDirectory = System.getProperty("user.dir") + File.separator;
	
	public static void main(String[] args) throws IOException, ParseException {

		Main main = new Main();
		main.JSONToTxt();
		main.createIndex();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					MainWindowCommander mwc = MainWindowCommander.getInstance();
					MainWindow mainWindow = mwc.getMainWindow();

					mainWindow.initialize();

				}catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
			}
		});
	}
	
	private void JSONToTxt() throws FileNotFoundException, IOException, ParseException {
		
		JsonParser jp = new JsonParser(projectDirectory);
		
		jp.parseJSONFiles();
	}
	
	private void createIndex() throws IOException {
		
		deleteExistingIndex();

		FileIndexer indexer = new FileIndexer(indexPath);
		
		//vars to hold the amount of articles and the start/end time to calculate elapsed time
		long startTime = System.currentTimeMillis();
		int numberOfIndexedArticles = indexer.createIndex(txtPath);
		long finishTime = System.currentTimeMillis();
		
		indexer.closeWriter();
		//prints the information
		System.out.println("Indexed " + numberOfIndexedArticles + " articles in " + (finishTime - startTime) + "ms.");
	}
	
	private void deleteExistingIndex() {
		
		File indexDirectory = new File(indexPath);
		File[] files = indexDirectory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				file.delete();
			}
		}
		
		indexDirectory.delete();
		System.out.println("Deleted existing Index @ " + indexPath);
	}
}
