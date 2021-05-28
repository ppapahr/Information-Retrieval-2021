package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
  

public class JsonParser {
	
	private String fileDirectoryName;
	
	public JsonParser(String fileDirectoryName){
		this.fileDirectoryName = fileDirectoryName;
	}
	
	
	private void createDir() throws IOException {
		File directory = new File(fileDirectoryName + "txt_Documents");
	    if (! directory.exists()){
	        directory.mkdir();
	        System.out.println("made");
	    }
	    
	}
	
	public void parseJSONFiles() throws FileNotFoundException, IOException, ParseException {
		this.createDir();
		
		File[] files = new File(fileDirectoryName + "\\JSON_Documents").listFiles();
		for (File f : files) {
			String name = f.getAbsolutePath();
			Object obj = new JSONParser().parse(new FileReader(name));
			JSONObject jo = (JSONObject) obj;
			String txtName = (String) jo.get("paper_id");
			File txt = new File(fileDirectoryName + "\\txt_Documents\\" + txtName + ".txt");
		    txt.createNewFile();
		    JSONObject metadata = (JSONObject) jo.get("metadata");
		    String title = (String) metadata.get("title");
		    FileWriter myWriter = new FileWriter(txt);
		    if (title.isBlank()) {
		    	title = "empty";
		    }
		    myWriter.write(title + "\n");
		    JSONArray authors = (JSONArray) metadata.get("authors");
		    if (authors.size() == 0) {
		    	myWriter.write("empty"+"\n");
		    } else {
		    	for (int i = 0; i < authors.size(); i++) {
			    	JSONObject jsonobject = (JSONObject) authors.get(i);
			    	String firstName = (String) jsonobject.get("first");
			    	String lastName = (String) jsonobject.get("last");
			    	myWriter.write(firstName + " " + lastName);
			    	if (i != authors.size() -1) {
			    		myWriter.write(", ");
			    	} else {
			    		myWriter.write("\n");
			    	}
			    }
		    }
		    JSONArray abstractObj = (JSONArray) jo.get("abstract");
		    if (abstractObj.size() > 0) {
		    	JSONObject jsonobject = (JSONObject) abstractObj.get(0);
			    String abstractText = (String) jsonobject.get("text");
			    myWriter.write(abstractText + "\n");
		    } else {
		    	myWriter.write("empty"+"\n");
		    }
		    JSONArray bodyText = (JSONArray) jo.get("body_text");
		    for (int i = 0; i < bodyText.size(); i++) {
		    	JSONObject jsonobject = (JSONObject) bodyText.get(i);
		    	String par = (String) jsonobject.get("text");
		    	myWriter.write(par + " ");
		    }
		    myWriter.close();
		}
		
		
	}
	
}
