import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
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

public class SimpleFormDemo {
	 
	private String message="Welcome to LambdaTest";
	String actualMsg =null;
	WebDriver driver=null;
	String gridURL = "@hub.lambdatest.com/wd/hub";
	
	/*@Parameters({"browser","version","os"})
	@BeforeClass
	public void setUp(String browser,String version,String os) {
			
		  HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		  ltOptions.put("username", "pkanakadurgabqe");
		  ltOptions.put("accessKey", "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb");
		  ltOptions.put("geoLocation", "US");
		  ltOptions.put("visual", true);
		  ltOptions.put("video", true);
		  ltOptions.put("network", true);
		  ltOptions.put("project", "Untitled");
		  ltOptions.put("tunnel", true);
		  ltOptions.put("selenium_version", "4.35.0");
		  ltOptions.put("build", "LambdaJavaSelenium101");
		  ltOptions.put("project", "JavaSelenium101");
		 
		  
		 if(browser.equalsIgnoreCase("Chrome")) {
				  ChromeOptions browserOptions = new ChromeOptions();
				  browserOptions.setPlatformName(os);
				  browserOptions.setBrowserVersion(version);
				  browserOptions.setCapability("LT:Options", ltOptions);
				  //driver=new ChromeDriver(browserOptions);
				  try {
			            driver = new RemoteWebDriver(new URL("https://pkanakadurgabqe:" + "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" + gridURL), browserOptions);
			        } catch (MalformedURLException e) {
			            System.out.println("Invalid grid URL");
			        } catch (Exception e) {
			            System.out.println(e.getMessage());
			        }
				  
			 }else if(browser.equalsIgnoreCase("Firefox")) {
				 FirefoxOptions browserOptions = new FirefoxOptions();
				 browserOptions.setPlatformName(os);
				 browserOptions.setBrowserVersion(version);
				 browserOptions.setCapability("LT:Options", ltOptions);
				 //driver=new FirefoxDriver(browserOptions);
				 try {
			            driver = new RemoteWebDriver(new URL("https://pkanakadurgabqe:" + "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" + gridURL), browserOptions);
			        } catch (MalformedURLException e) {
			            System.out.println("Invalid grid URL");
			        } catch (Exception e) {
			            System.out.println(e.getMessage());
			        }
			 }else if(browser.equalsIgnoreCase("Microsoft Edge")) {
				 EdgeOptions browserOptions = new EdgeOptions();
				 browserOptions.setPlatformName(os);
				 browserOptions.setBrowserVersion(version);
				 browserOptions.setCapability("LT:Options", ltOptions);
				 //driver=new EdgeDriver(browserOptions);
				 try {
			            driver = new RemoteWebDriver(new URL("https://pkanakadurgabqe:" + "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" + gridURL), browserOptions);
			        } catch (MalformedURLException e) {
			            System.out.println("Invalid grid URL");
			        } catch (Exception e) {
			            System.out.println(e.getMessage());
			        }
			 }else if(browser.equalsIgnoreCase("Internet Explorer")) {
				 EdgeOptions browserOptions = new EdgeOptions();
				 browserOptions.setPlatformName(os);
				 browserOptions.setBrowserVersion(version);
				 browserOptions.setCapability("LT:Options", ltOptions);
				 //driver=new EdgeDriver(browserOptions);
				 try {
			            driver = new RemoteWebDriver(new URL("https://pkanakadurgabqe:" + "zMeNWbVncBwDlKdhvHEdRAmM37UHazGqcnkIg7XSoDlZfzR6Rb" + gridURL), browserOptions);
			        } catch (MalformedURLException e) {
			            System.out.println("Invalid grid URL");
			        } catch (Exception e) {
			            System.out.println(e.getMessage());
			        }
			 }
		 
		 
		
	}*/
	
	@BeforeMethod
	public void launchPage() {
		driver.get("https://www.lambdatest.com/selenium-playground");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	@Test(timeOut = 20000)
	public void testScenario1() {
		
		
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
