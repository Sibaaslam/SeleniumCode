package rsa.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableCode {
	WebDriver driver;
	public ReusableCode(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}

	
	
	
	
	By cart=By.cssSelector(".cartSection h3");
	
	
	public void loadingOfElements(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
	
	}
	
	public void toLoadCart(By by) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		
	}
	
//	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
//	WebElement carticon;
	
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement carticon;
	
	By animator=By.cssSelector(".ng-animating");
	By toast=By.cssSelector("#toast-container");
	
	public CheckOut clickOnCart() {
		//toLoadCart(toast);
		//invisibleOfElements(animator);
		carticon.click();
		CheckOut co=new CheckOut(driver);
		return co;
	}
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement ordericon;
	
	public order clickonOrder() {
		ordericon.click();
		order op=new order(driver);
		return op;
	}

	// you u can use the same method with respect to Data type ex: By is the return type of this method you can call this method to wait for any by type element
	public void invisibleOfElements(By animator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(animator));
	}
	public void cartAppear() {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cart));
	}
	
	

	

	
	
	
	

}
