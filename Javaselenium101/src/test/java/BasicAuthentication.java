import java.util.HashMap;

import org.openqa.selenium.edge.EdgeOptions;

public class BasicAuthentication {

	
	public void setUp() {
		EdgeOptions browserOptions = new EdgeOptions();
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
		  browserOptions.setCapability("LT:Options", ltOptions);
	}
}
