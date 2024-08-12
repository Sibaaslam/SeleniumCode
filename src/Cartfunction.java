import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Cartfunction {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Users/HP/Desktop/Selenium/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String productname ="ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("sibaaslam@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Siba@1996");
		driver.findElement(By.id("login")).click();
		wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(".card-body"))));
		List<WebElement> products=driver.findElements(By.cssSelector(".card-body"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean check=cartProducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(check);
		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click();
		Thread.sleep(3000);
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "Indi").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		a.moveToElement(driver.findElement(By.cssSelector(".icon.icon-arrow-right-circle"))).click().build().perform();
		String msg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
		
		
		
	
		
		
		
		
		
		

	}

}
