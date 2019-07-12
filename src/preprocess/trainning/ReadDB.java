package preprocess.trainning;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ReadDB {
	// Đọc dữ liệu từ MongoDB
    public static List<Document> readCollection(String dbName, String inFileName) throws UnknownHostException {
   
		List<Document> listdoc = new ArrayList<Document>();
		 MongoClient mongoClient = new MongoClient("localhost", 27017);
		 //
		 
	     DB jobdb = mongoClient.getDB(dbName);
	     DBCollection dept = jobdb.getCollection(inFileName);
	      
	     DBCursor cursor = dept.find();

	     while (cursor.hasNext()) {
	    	 
	    	 DBObject obj = cursor.next();
	    	 String content = (String) obj.get("content");
	    	 int label = (int) obj.get("label");
	    	 content=content.replaceAll("\\n", " ");
	    	 content = (content.replaceAll("MÔ TẢ CÔNG VIỆC", ""));
	    	 Document doc = new Document(label,content);
	    	 listdoc.add(doc);
	         
	      }
		return listdoc;
	}
}