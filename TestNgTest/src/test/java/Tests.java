import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tests {
	
	@Test(timeOut=20000,priority=2)
	public void validateAlert() throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.executeScript("lambdatest_executor: {\"action\": \"stepcontext\", \"arguments\": {\"data\": \"Opening WebApp\", \"level\": \"info\"}}");
		WebElement lnkAlert = driver.findElement(By.linkText("Javascript Alerts"));
		
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(lnkAlert));
		lnkAlert.click();
		
		List<WebElement> btnsclickme = driver.findElements(By.xpath("//button[text()='Click Me']"));
		System.out.println(btnsclickme.size());
		
		/*
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Click Me']"))));
		driver.findElement(By.xpath("//button[text()='Click Me']")).click();
		
		new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		Alert alrt =driver.switchTo().alert();
		String alertText =alrt.getText();
		Assert.assertEquals(alertText,"I am an alert box!" );
		alrt.accept();*/
		
	}

}
