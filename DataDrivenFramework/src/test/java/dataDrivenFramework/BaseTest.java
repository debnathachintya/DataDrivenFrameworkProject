package dataDrivenFramework;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import base.BaseFile;

public class BaseTest extends BaseFile {
	public static Logger Logger = LogManager.getLogger(BaseTest.class.getName());
	
	@BeforeTest
	public void initialize() throws IOException, InterruptedException {
		driver = InitializeDriver("./data/data.properties");
		
		driver = implicitWait();
		
		driver = maximizeDriver();
		
		driver.get(prop.getProperty("url"));
		
		Logger.info("Navigated to the given URL");
		
		driver = threadSleep();
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
		Logger.info("Browser closed");
		
		driver = null;
		Logger.info("Test Passed");
	}
}
