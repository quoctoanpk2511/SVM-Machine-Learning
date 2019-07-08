package crawler;

///vieclam24


import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Driver3 extends Thread{


	private WebDriver driver ;
	private String fileName;
	private String url;
	private MongoCollection<Document> collection;
	
	static int size = 10;
	static String host ="localhost";
	static int port = 27017;
	static String databaseName = "vieclam24h";

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void begin() {

		System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe"); 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		MongoDatabase database = new MongoClient( host , port ).getDatabase(databaseName);
		
		collection = database.getCollection(fileName);
		
		if(collection == null)
			database.createCollection(fileName);
		
		collection = database.getCollection(fileName);
	}
	
	
//	public void check() {
//		int i = 25;
//		for(;;) {
//			driver.get("https://vieclam24h.vn/ban-hang-c63.html?page="+i);
//			
//			WebElement w = driver.findElement(By.cssSelector("li.next"));
//			System.out.println(w.getAttribute("class"));
//			
//			if(w.getAttribute("class").equals("next active")) break;
//			
//			i++;
//		}
//	}
	@Override
	public void run() {		
		begin();
		String url ="https://vieclam24h.vn/"+this.url+"?page=",contentURL,id,content;
		int i =1,j;
		Document d;
		WebElement k;
		FindIterable<Document> filter;
		MongoCursor<Document> cursor;
		
		while(true) {
			String currentURL = url +i;
			driver.get(currentURL);
			if(driver.findElement(By.cssSelector("li.next")).getAttribute("class").equals("next active")) break;
			
			for(j = 1;j<=size;j++) {	
				
				System.out.println(fileName+" : "+j+"   page : "+i);	
								
				try {
					
					if(j!=1)
						k = driver.findElement(By.cssSelector("#box_vieclam_xacthuc .list-items:nth-child("+(j-1)+") .title-blockjob-main a"));
					else
						k = driver.findElement(By.cssSelector("#box_vieclam_xacthuc .list-items:first-child .title-blockjob-main a"));
					
					contentURL =k.getAttribute("href");	
					
					id = getId(contentURL);

					filter = collection.find(Filters.eq("_id",id));
					cursor = filter.iterator();
					

					
					if(cursor.hasNext() == false)
					{
						driver.get(contentURL);
						
						content = driver.findElement(By.xpath("//div[@class=\"item row\"][1]/p")).getText();
					
						
						
						d = new Document("_id",id).append("url", contentURL).append("content", content);
						System.out.println("koco"+d.toJson());
						collection.insertOne(d);
						
						driver.get(currentURL);
					}
					else 
					{
						System.out.println("daco"+cursor.next().toJson());
						continue;
					
					}
					
				}catch(Exception e) {
					e.printStackTrace();	
					driver.get(currentURL);
				}				
			}
			i++;
		}
		driver.quit();
	
	}

	public String getId(String contentURL) {
		char[] st = contentURL.substring(0, contentURL.length()-5).toCharArray();	
		String id="";
		
		for(int i = st.length-1;i>0;i--) {
			if(st[i]=='d') break;
			id =st[i]+id;
		}		
		return id;
	}
	
}
