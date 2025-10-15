package com.lambdatest;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.net.URL;
import java.time.Duration;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
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

public class LambdaTestNGTest {
	
	WebDriverWait wait = null;
	RemoteWebDriver driver = null;
	
	@Parameters(value= {"Browser Name","URL","Browser Version","Platform Name"}) 
	@BeforeClass
	public void setUp(String browserName,String url,String version,String platform) throws MalformedURLException {
		
		String userName = System.getenv("LT_USERNAME") == null ? "pkanakadurgabqe" : System.getenv("LT_USERNAME");
        String authKey = System.getenv("LT_ACCESS_KEY") == null ? "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" : System.getenv("LT_ACCESS_KEY");
        
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
		ltOptions.put("build", "LambdaTest TestNg Build");
		ltOptions.put("project", "LambdaTestNgTest");
		
		if(!browserName.equalsIgnoreCase("Internet Explorer")){
			ltOptions.put("selenium_version", "4.35.0");
			ltOptions.put("w3c", true);
		 }
		
       
        cap.setCapability("LT:Options", ltOptions);
       
        driver = new RemoteWebDriver(new URL("https://" + userName + ":" + authKey + hub), cap);
	}
	
	@Parameters(value= {"URL"})
	@BeforeMethod
	public void launchPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
	}
	
	
	@Test(timeOut=20000,priority=0)
	public void verifyPageTitle() {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		By ele = By.id("__next");
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(ele));
		
		//getting page title
		String actualPageTitle = driver.getTitle();
		Reporter.log("Page Title:"+actualPageTitle);
		//getting actual page title as Selenium Grid Online | Run Selenium Test On Cloud"
		SoftAssert  assrt = new SoftAssert ();
		assrt.assertEquals(actualPageTitle, "LambdaTest");
		assrt.assertAll();
	}
	
	@Test(timeOut=20000,priority=1)
	public void validateCheckbox() {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		WebElement lnkCheckbox = driver.findElement(By.linkText("Checkbox Demo"));
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(lnkCheckbox));
		lnkCheckbox.click();
		
		WebElement chkbox =driver.findElement(By.cssSelector(".mt-40>label>input"));
		chkbox.click();
		Assert.assertTrue(chkbox.isSelected());
		chkbox.click();
		Assert.assertFalse(chkbox.isSelected());
	}

	
	@Test(timeOut=20000,priority=2)
	public void validateAlert() throws InterruptedException {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		WebElement lnkAlert = driver.findElement(By.linkText("Javascript Alerts"));
		
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(lnkAlert));
		lnkAlert.click();
		
		List<WebElement> btnsclickme = driver.findElements(By.xpath("//button[text()='Click Me']"));
		
		
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Click Me']"))));
		driver.findElement(By.xpath("//button[text()='Click Me']")).click();
		
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		Alert alrt =driver.switchTo().alert();
		String alertText =alrt.getText();
		Assert.assertEquals(alertText,"I am an alert box!" );
		alrt.accept();
		
	}
	
	@AfterClass
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}
}
