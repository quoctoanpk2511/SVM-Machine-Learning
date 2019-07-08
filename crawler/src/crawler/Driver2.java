package crawler;

//my-work

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Driver2 extends Thread{

	private WebDriver driver ;
	private String fileName;
	private String url;
	private MongoCollection<Document> collection;
	
	static int size = 20;
	static String host ="localhost";
	static int port = 27017;
	static String databaseName = "mywork";
	
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
	
	@Override
	public void run() {	
		begin();
		String currentURL,contentURL,id,content;
		int i =1,j;
		Document d;
		WebElement k;
		FindIterable<Document> filter;
		MongoCursor<Document> cursor;
		while(true) {
			
			currentURL = "https://mywork.com.vn/tuyen-dung/trang/"+i+"?categories="+url;
			driver.get(currentURL);
			if(driver.findElement(By.cssSelector("li.page-item:last-child")).getAttribute("class").equals("page-item disabled"))
				break;
			
			for(j = 1;j<=size;j++) {	
				
				System.out.println(fileName+" : "+j+"\n page : "+i);
				

				try {
					k = driver.findElement(By.xpath("//section[@class='item']"+"["+j+"]//p/a"));

					contentURL =k.getAttribute("href");	
					
				
					id = getId(contentURL);

					filter = collection.find(Filters.eq("_id",id));
					cursor = filter.iterator();
					

					
					if(cursor.hasNext() == false)
					{
						driver.get(contentURL);
						
						content = driver.findElement(By.xpath("//h3[text()='Mô tả công việc ']/following-sibling::div[1]")).getText();
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
		StringTokenizer st = new StringTokenizer(contentURL,"/");
		String id = null;
		for(int i =0;i<5;i++)
			 id = st.nextToken();
		return id;
	}
	
}
