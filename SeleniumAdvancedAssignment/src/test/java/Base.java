import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base {
	
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
			ltOptions.put("build", "LambdaTest Java Selenium Build");
			ltOptions.put("project", "JavaSelenium101");
			
			if(!browserName.equalsIgnoreCase("Internet Explorer")){
				ltOptions.put("selenium_version", "4.35.0");
				ltOptions.put("w3c", true);
			 }
			
	       
	        cap.setCapability("LT:Options", ltOptions);
	
	        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), cap);
}
      
        
        @AfterClass
    	public void teardown() {
    		if(driver!=null) {
    			driver.quit();
    		}
    	}

  

}
