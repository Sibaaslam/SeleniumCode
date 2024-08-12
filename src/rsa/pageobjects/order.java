package rsa.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class order extends ReusableCode {
	
		WebDriver driver;
		public order(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> orderItems;
		
		public boolean orderDisplayed(String productname) {
			
			boolean check=orderItems.stream().anyMatch(orderItem-> orderItem.getText().equalsIgnoreCase(productname));
			return check;
			}
			

}
