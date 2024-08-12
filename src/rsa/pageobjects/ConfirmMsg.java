package rsa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmMsg {
	WebDriver driver;
	public ConfirmMsg(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".hero-primary")
	WebElement msg;
	
	public String getMsg() {
	String s=msg.getText();
	return s;
	}

}
