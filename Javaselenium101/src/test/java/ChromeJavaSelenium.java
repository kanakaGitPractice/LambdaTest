import java.io.FileInputStream;
import java.io.IOException;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ChromeJavaSelenium {
	 
	private String message="Welcome to LambdaTest";
	String actualMsg =null;
	RemoteWebDriver driver=null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	XSSFWorkbook book;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell col;

	    @BeforeClass
	    public void setUp() throws IOException {
			String username = System.getenv("LT_USERNAME") == null ? "pkanakadurgabqe" : System.getenv("LT_USERNAME");
	        String authkey = System.getenv("LT_Access Key") == null ? "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" : System.getenv("LT_Access Key");
	        ;
	        String hub = "@hub.lambdatest.com/wd/hub";
	        
	     /*   FileInputStream file = new FileInputStream("C:\\Users\\pkana\\git\\LamdaTest\\Javaselenium101\\src\\test\\resources\\selenium_java_101.xlsx");
	        book = new XSSFWorkbook(file);
	        sheet = book.getSheet("Sheet1");
	        int rows = sheet.getLastRowNum();
	  	    int cols = sheet.getRow(1).getLastCellNum();
	  	  for(int i =0; i<rows;i++) {
			  XSSFRow row = sheet.getRow(i);
			  for(int j=0;j<cols;j++) {
				  System.out.println(row.getCell(j));
			  }
		  }*/
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setPlatform(Platform.WIN10);
	        caps.setCapability("browserName", "Chrome");
	         caps.setCapability("version","dev");
	        caps.setCapability("build", "selenium With Java");
	        caps.setCapability("name",  this.getClass().getName());
	        caps.setCapability("plugin", "java-java");
	        caps.setCapability("video", true);
	        caps.setCapability("visual", true);
	        caps.setCapability("tunnel", true);
	        caps.setCapability("network", true);

	        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
	
	        caps.setCapability("tags", Tags);
	
	        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);
}
  
	    @BeforeMethod
    	public void launchPage() {
				driver.get("https://www.lambdatest.com/selenium-playground");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   }
        
	    
    //Testing simple form demo
   	@Test(timeOut = 20000)
	public void testSimpleForm() {
		
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.linkText("Simple Form Demo")).click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		if(url.contains("simple-form-demo")) {
			Reporter.log("url contains simple-form-demo text");
		}
		driver.findElement(By.id("user-message")).sendKeys(message);
		driver.findElement(By.cssSelector("button#showInput")).click();
		actualMsg=driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals(actualMsg,message);
	}
   	
	
	@Test(timeOut = 20000)
	public void testSliders() {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.linkText("Drag & Drop Sliders")).click();
		WebElement slider =driver.findElement(By.cssSelector("div.sp__range>input[value='15']"));
		
		int range = Integer.parseInt(driver.findElement(By.id("rangeSuccess")).getText());
		while(range !=95){
			slider.sendKeys(Keys.ARROW_RIGHT);
			range = Integer.parseInt(driver.findElement(By.id("rangeSuccess")).getText());
		}
	}
	
	
	@Test(timeOut = 20000)
	public void submitForm() throws InterruptedException {
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		driver.findElement(By.partialLinkText("Input Form ")).click();
		WebElement btnSubmit = driver.findElement(By.xpath("//button[text()='Submit']"));
		btnSubmit.click();
		Thread.sleep(100);
		
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
	
	@AfterMethod
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}
