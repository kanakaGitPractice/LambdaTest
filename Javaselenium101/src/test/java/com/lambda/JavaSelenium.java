package com.lambda;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class JavaSelenium {
	 
	private String message="Welcome to LambdaTest";
	String actualMsg =null;
	RemoteWebDriver driver=null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	

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
        @Parameters(value= {"URL"})
	    @BeforeMethod
    	public void launchPage(String url) {
				driver.get(url);
				driver.manage().window().maximize();
				By ele = By.id("__next");
				new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
	   }
        
	    
    //Testing simple form demo
   	@Test(timeOut = 20000,priority=0)
	public void testSimpleForm() {
		
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.linkText("Simple Form Demo")).click();
		String url = driver.getCurrentUrl();
		//System.out.println(url);
		if(url.contains("simple-form-demo")) {
			Reporter.log("url contains simple-form-demo text");
		}
		driver.findElement(By.id("user-message")).sendKeys(message);
		driver.findElement(By.cssSelector("button#showInput")).click();
		actualMsg=driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals(actualMsg,message);
	}
   	
	
	@Test(priority=1)
	public void testSliders() {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		WebElement slider =driver.findElement(By.cssSelector("input[value='15']"));
		WebElement rangeMover = driver.findElement(By.id("rangeSuccess"));
		int range = Integer.parseInt(rangeMover.getText());
		while(range !=95){
			slider.sendKeys(Keys.ARROW_RIGHT);
			range = Integer.parseInt(rangeMover.getText());
		}
		
		
		
	}
	
	
	@Test(timeOut = 20000,priority=2)
	public void submitForm() throws InterruptedException {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.partialLinkText("Input Form ")).click();
		WebElement btnSubmit = driver.findElement(By.xpath("//button[text()='Submit']"));
		btnSubmit.click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("name")).sendKeys("kanaka");
		driver.findElement(By.id("inputEmail4")).sendKeys("kanaka@gmail.com");//
		//new WebDriverWait(driver,Duration.ofSeconds(4)).until(ExpectedConditions.visibilityOf(ele)).sendKeys("kanaka@gmail.com");
		
		driver.findElement(By.id("inputPassword4")).sendKeys("kanaka");
		driver.findElement(By.name("company")).sendKeys("kanaka");
		driver.findElement(By.id("websitename")).sendKeys("kanaka");
		
		Select country = new Select(driver.findElement(By.name("country")));
		country.selectByVisibleText("United States");
		
		driver.findElement(By.id("inputCity")).sendKeys("benton");
		driver.findElement(By.id("inputAddress1")).sendKeys("address1");
		driver.findElement(By.id("inputAddress2")).sendKeys("address2");
		driver.findElement(By.id("inputState")).sendKeys("AR");
		driver.findElement(By.cssSelector("input#inputZip")).sendKeys("12345");
		btnSubmit.click();
		
		actualMsg = driver.findElement(By.cssSelector("p.success-msg")).getText();
		Assert.assertEquals(actualMsg,"Thanks for contacting us, we will get back to you shortly.");
	}
	
	@AfterClass
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
