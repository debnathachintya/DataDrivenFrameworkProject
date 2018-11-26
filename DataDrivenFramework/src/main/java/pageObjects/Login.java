package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	public WebDriver driver;
	By username = By.id("user_name");
	By password = By.id("pass_word");
	By submit = By.xpath("//input[@id='btn_login']");
	By reset = By.xpath("//input[@name='Reset']");
	
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement userName() {
		return driver.findElement(username);
	}
	
	public WebElement passWord() {
		return driver.findElement(password);
	}
	
	public WebElement SubMit() {
		return driver.findElement(submit);
	}
	
	public WebElement ReSet() {
		return driver.findElement(reset);
	}
}
