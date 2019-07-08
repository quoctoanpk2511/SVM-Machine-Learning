package preprocess.bagofword;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ReadDB {
    public static List<String> readFile(String inFileName) throws UnknownHostException {
    	List<String> listprepro = new ArrayList<String>();
		List<Integer> listlabel = new ArrayList<Integer>();
		 MongoClient mongoClient = new MongoClient("localhost", 27017);
		 //
		 
	     DB jobdb = mongoClient.getDB("JOBDB");
	     DBCollection dept = jobdb.getCollection("marketing");
	      
	     DBCursor cursor = dept.find();

	     while (cursor.hasNext()) {
	    	 DBObject obj = cursor.next();
	    	 String content = (String) obj.get("content");
	    	 int label = (int) obj.get("label");
	    	 listprepro.add(content.replaceAll("MÔ TẢ CÔNG VIỆC", ""));
	    	 listlabel.add(label);
	         
	      }
		return listprepro;
	}
}
