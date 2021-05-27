package Command;

import View.MainWindow;

public class MainWindowCommander {

	protected MainWindow mainWindow;
	
	private static MainWindowCommander mwc = null;
	
	private MainWindowCommander() {

		mainWindow = new MainWindow();
	}
	
	public static MainWindowCommander getInstance() {
		
		if (mwc == null)
			mwc =  new MainWindowCommander();
		
		return mwc;
	}
	
	public MainWindow getMainWindow() {
		
		return mainWindow;
	}
}