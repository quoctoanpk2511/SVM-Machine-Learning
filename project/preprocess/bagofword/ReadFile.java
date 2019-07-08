package preprocess.bagofword;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class ReadFile {
    public static List<String> readFile() throws UnknownHostException {
    	List<String> listprepro = new ArrayList<String>();
    	MongoClient mongoClient = new MongoClient("localhost", 27017);
    	DB jobdb = mongoClient.getDB("JOBDB");
	      DBCollection dept = jobdb.getCollection("marketing");
	      
	      DBCursor cursor = dept.find();
	      
	   
	      while (cursor.hasNext()) {
	          
	          String content = (String) cursor.next().get("content");
	          listprepro.add(content.replaceAll("MÔ TẢ CÔNG VIỆC", ""));
	         
	      }
	      return listprepro;
	}
}
