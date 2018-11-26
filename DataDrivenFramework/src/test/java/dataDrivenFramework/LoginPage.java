package dataDrivenFramework;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import pageObjects.Login;

public class LoginPage extends BaseTest {
	public static Logger Logger = LogManager.getLogger(LoginPage.class.getName());

	@Test
	public void readData() throws Exception {
		Login newLogin = new Login(driver);
		String excelPath = prop.getProperty("excelPath");

		FileInputStream fs = new FileInputStream(excelPath);
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		
		Logger.info("Workbook Loaded");
		
		XSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			for (int j = 1; j < sheet.getLastRowNum(); j++) {
			
				XSSFCell cell = sheet.getRow(i).getCell(j);
				newLogin.userName().sendKeys(cell.getStringCellValue());
				Logger.info("Username field entered");

				cell = sheet.getRow(i).getCell(j);
				newLogin.passWord().sendKeys(cell.getStringCellValue());
				Logger.info("Password field entered");
				
				
				String message = "Test Case Passed";
				sheet.getRow(i).getCell(3).setCellValue(message);
				FileOutputStream foutput = new FileOutputStream(excelPath);
				wb.write(foutput);
				foutput.close();
			}
		}
		driver = threadSleep();
		
		String returnDateTime = getDateTime();
		getScreenshot(returnDateTime);
	}
}