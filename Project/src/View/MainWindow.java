package View;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainWindow {

	private JFrame frame;
	private JPanel container;
	private Screen resultsScreen; //extends screen class
	
	public void initialize() {
		
		this.container = new JPanel(new CardLayout());
		this.frame = initializeFrame();
		
		//main screen used for the app
		this.resultsScreen = new ResultsScreen();
		JPanel resultsScreenPanel = resultsScreen.getScreen();

		JScrollPane resultsPanel = new JScrollPane(resultsScreenPanel);
		resultsPanel.getVerticalScrollBar().setUnitIncrement(16);

		this.container.add(resultsPanel);
		resultsScreen.setContainer(container);
		
		this.frame.add(this.container);
		
		this.frame.setVisible(true);
	}
	
	private JFrame initializeFrame() {
		
		JFrame frame = new JFrame("COVID-19 Database Search");
		frame.setSize(1024, 768); //classic 4:3 res
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
	}
	
	public Screen getScreen(String screen) {
		// only one page is used for the app, in any other case there's something wrong
		switch (screen) {
		case "Results":
			return this.resultsScreen;
		default:
			throw new IllegalArgumentException("Screen \"" + screen + "\" does not exist.");
		}
	}
}
