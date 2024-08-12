package rsa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOut extends ReusableCode {
	WebDriver driver;
	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath="(//button[@class='btn btn-primary'])[3]")
	WebElement pay;
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selcountry;
	
	@FindBy(css=".icon.icon-arrow-right-circle")
	WebElement order;
	
	
	
	public boolean loadOfCart(String productname) {
	cartAppear();
	boolean check=cartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productname));
	return check;
	}
	
	By results=By.cssSelector(".ta-results.list-group.ng-star-inserted");
	
	public ConfirmMsg paymentDetails() {
		pay.click();
		Actions a=new Actions(driver);
		a.sendKeys(country, "Indi").build().perform();
		toLoadCart(results);
		selcountry.click();
		a.moveToElement(order).click().build().perform();
	    ConfirmMsg cm=new ConfirmMsg(driver);
	    return cm;
	   
	}
}


