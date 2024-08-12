package rsa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends ReusableCode {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	//page for CICD
	@FindBy(id="userEmail")
	WebElement email;
	@FindBy(id="userPassword")
	WebElement passwrd;
	@FindBy(id="login")
	WebElement submit;
	
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errormsg;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void enterLoginDetail(String emailID, String pwd) {
		email.sendKeys(emailID);
		passwrd.sendKeys(pwd);
		
	}
	
	public ProductCatalogue submitLoginDetails() {
		submit.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}
	
	public String errorMessgae() {
		return errormsg.getText();
		
	}
	
	
	



}
