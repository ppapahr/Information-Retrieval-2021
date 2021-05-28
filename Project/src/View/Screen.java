package View;

import javax.swing.JPanel;
//used for resultsScreen and for future expansion
public abstract class Screen {

	JPanel screenPanel;
	JPanel container;
	
	public abstract void setContainer(JPanel container);

	public abstract JPanel getScreen();
}
