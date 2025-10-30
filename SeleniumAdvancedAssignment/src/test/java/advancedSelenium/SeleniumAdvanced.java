package advancedSelenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SeleniumAdvanced  {
	String gridURL = "@hub.lambdatest.com/wd/hub";
	RemoteWebDriver driver=null;
	
	@Parameters(value= {"Browser Name","Browser Version","Platform Name"}) 
	@BeforeClass
	public void setUp(String browserName,String version,String platform) throws MalformedURLException {
			String username = System.getenv("LT_USERNAME") == null ? "pkanakadurgabqe" : System.getenv("LT_USERNAME");
	        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" : System.getenv("LT_ACCESS_KEY");
	        ;
	        String hub = "@hub.lambdatest.com/wd/hub";
	        
	        DesiredCapabilities cap = new DesiredCapabilities();
	        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
	        
	        //specifying desired capabilities like browsername,platform 
			cap.setCapability("browserName",browserName);
			cap.setCapability("platformName",platform);
			cap.setCapability("browserVersion", version);
			
			//setting lambda capabilities in hashmap
			ltOptions.put("video", true);
			ltOptions.put("console", true);
			ltOptions.put("network", true);
			ltOptions.put("visual", true);
			ltOptions.put("build", "LambdaTest Advanced Java Selenium Build");
			ltOptions.put("project", "SeleniumAdvancedAssignment");
			ltOptions.put("selenium_version", "4.35.0");
			ltOptions.put("w3c", true);
			
			
	        cap.setCapability("LT:Options", ltOptions);
	
	        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), cap);
}
      
	
	
	@Parameters(value= {"URL"})
	@BeforeMethod
  	public void launchPage(String url) {
				driver.get(url);
				driver.manage().window().maximize();
				By ele = By.id("__next");
				new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
	}
	
	
	@Test(timeOut=20000)
	public void testScenario() throws InterruptedException {
		Actions action = new Actions(driver);
		SoftAssert assrt =new SoftAssert();
		WebElement webele = driver.findElement(By.xpath("//a[text()='Explore all Integrations']"));
		
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		((JavascriptExecutor) driver).executeScript(
			    "arguments[0].scrollIntoView({ block: 'center', inline: 'nearest' });", webele );
			Thread.sleep(500); 
			
			String mainWindow =driver.getWindowHandle();
			
			String link  = webele.getAttribute("href");
			((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", link);
			
			Set<String> windows = driver.getWindowHandles();
			
			System.out.println("opened windows:"+windows);
			Reporter.log("opened windows:"+windows);
			for(String window: windows) {
				if(!window.equals(mainWindow)) {
					driver.switchTo().window(window);
					assrt.assertEquals(driver.getCurrentUrl(),link);
				}
			}
			
			//clicking on Codeless Automation link
			driver.findElement(By.xpath("//a[text()='Codeless Automation']")).click();
			
			//clicking on Integrate Testing Whiz with LambdaTest
			WebElement whizEle= driver.findElement(By.xpath("//a[text()='Integrate Testing Whiz with LambdaTest']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", whizEle);
			
			//getting the title of the page 
			String actualPageTitle = driver.getTitle();
			assrt.assertEquals(actualPageTitle, "TestingWhiz Integration With LambdaTest");
			
			//closing current window
			driver.close();
			
			//printing current window count
			windows = driver.getWindowHandles();
			System.out.println("Current Window Count:"+windows.size());
			Reporter.log("Current Window Count:"+windows.size());
			driver.switchTo().window(mainWindow);
			
			//setting url in current window,clicking on Community link and verifying current url
			driver.navigate().to("https://www.lambdatest.com/blog");
			driver.findElement(By.xpath("//a[text()='Community']")).click();
			assrt.assertEquals(driver.getCurrentUrl(),"https://community.lambdatest.com");
					
			assrt.assertAll();
	}
	
	 @AfterClass
 	public void teardown() {
 		if(driver!=null) {
 			driver.quit();
 		}
 	}

}
