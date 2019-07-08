package crawler;


import java.util.ArrayList;
import java.util.List;
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

public class Driver{
	
	private WebDriver driver ;
	private String fileName;
	private String url;
	private MongoCollection<Document> collection;
	
	static int size = 50;
	static String host ="localhost";
	static int port = 27017;
	static String databaseName = "vietnamwork";
	
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
	
	
	public void run() {
		begin();
		String currentURL ="",contentURL,id,content;
		Document d;
		WebElement k,h;
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		FindIterable<Document> filter;
		MongoCursor<Document> cursor;
		driver.get(currentURL);	
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		
		int i =0,j;
		while(true) {
			
			try {
				WebElement w = driver.findElement(By.linkText(String.valueOf(i+1)));
				js.executeScript("arguments[0].scrollIntoView();", w);
				w.click();
				
			}catch(Exception e) {
				break;
			}
			
//				if(i >= 6) break;				
			
			for(j = 50*i +1;j<50*(i+1);j++) {					
				System.out.println(fileName+" : "+j);
				
				try {
					k = driver.findElement(By.xpath("//div[@data-placement ="+String.valueOf(j)+"]//h3/a[1]"));

					contentURL =k.getAttribute("href");
					
					h = driver.findElement(By.xpath("//div[@data-placement ="+String.valueOf(j)+"]"));
					
					id = h.getAttribute("data-job-id");	
					
					filter = collection.find(Filters.eq("_id",id));
					cursor = filter.iterator();
					
					if(cursor.hasNext() == false)
					{						
						js.executeScript("arguments[0].scrollIntoView();", k);	
						
						k.click();			
						
						tabs = new ArrayList<String>(driver.getWindowHandles());
						
						driver.switchTo().window(tabs.get(1));	
						
						content = driver.findElement(By.className("description")).getText();
						
						
						d = new Document("url",contentURL).append("content", content).append("_id", id);
						
						collection.insertOne(d);
						driver.close();
						driver.switchTo().window(tabs.get(0));
					}
					else 
					{
						System.out.println("daco"+cursor.next().toJson());
						continue;						
					}
				
				}catch(Exception e) {
					e.printStackTrace();
					if(tabs.size() >1) {
						for(int f = 1;f<tabs.size();f++)
						driver.switchTo().window(tabs.get(f));
						driver.close();
					}
					driver.switchTo().window(tabs.get(0));
				}				
			}
			i++;
		}
		
	}




	
	
}
