package rsa.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends ReusableCode {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".card-body")
	List<WebElement> products;
	@FindBy(css=".card-body")
	WebElement load;
	
	By eachProductName=By.cssSelector("h5");
	By addToCart=By.cssSelector("button[class='btn w-10 rounded']");
	
	By toast=By.cssSelector("#toast-container");
	By animator=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		loadingOfElements(load);
		return products;
	}
	
	public WebElement findTheDesiredProduct(String productname) {
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(eachProductName).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);	
		return prod;
	}
	
	public void clickTheDesiredProduct(String productname) {
		WebElement prod=findTheDesiredProduct(productname);
			prod.findElement(addToCart).click();
			toLoadCart(toast);
			invisibleOfElements(animator);
			
		
	}
	

	
	
	
	
	
	
	

}
