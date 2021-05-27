package Command;

import java.awt.event.ActionListener;

public class CommandFactory {

	public static ActionListener createCommand(String s) {
		if (s.equals("Search")) {
			Search search = new Search();
			return (ActionListener) search;
		} else {
			return null;
		}
		
	}
}