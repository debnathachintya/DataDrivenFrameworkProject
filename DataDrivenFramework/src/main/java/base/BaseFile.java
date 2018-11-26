package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BaseFile {
	public static WebDriver driver;
	public Properties prop;
	public static Logger Logger = LogManager.getLogger(BaseFile.class.getName());
	
	public WebDriver InitializeDriver(String propFilePath) throws IOException {
		prop = new Properties();
		FileInputStream fs = new FileInputStream(propFilePath);
		prop.load(fs);
		
		Logger.info("Properties File Loaded");
		
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
			driver = new ChromeDriver();
			
			Logger.info("Chrome Driver Initialized");
		}
		else {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("geckoDriverPath"));
			driver = new FirefoxDriver();
			
			Logger.info("Firefox Driver Initialized");
		}
		
		return driver;
	}
	
	public WebDriver maximizeDriver() {
		driver.manage().window().maximize();
		
		Logger.info("Browser Window Maximized");
		return driver;
	}
	
	public WebDriver implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Logger.info("Implicit wait time added");
		return driver;
	}
	
	public WebDriver threadSleep() throws InterruptedException {
		Thread.sleep(3000);
		Logger.info("Waiting Time Added");
		
		return driver;
	}
	
	public String getDateTime() {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss");
		
		Logger.info("Current Date Time Captured");
		return df.format(dt);
	}
	
	public void getScreenshot(String timeStamp) throws IOException {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String outputDirectory = prop.getProperty("outputDIR");
		String format = prop.getProperty("outputFormat");

		FileUtils.copyFile(file, new File(outputDirectory+timeStamp+format));		
		Logger.info("Screenshot Captured");
	}
	
	public void getExcelData(String excelPath) throws IOException {
		FileInputStream fs = new FileInputStream(excelPath);
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		String cellValue = sheet.getRow(0).getCell(0).getStringCellValue();
		
		Logger.info("CellValue"+cellValue);
	}
}