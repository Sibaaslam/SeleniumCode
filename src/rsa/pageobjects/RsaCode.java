package rsa.pageobjects;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RsaCode extends BaseTest {
	// public static void main(String[] args) throws InterruptedException,
	// IOException {
	
	String productname = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData",groups={"purchase"})
	//public void SubmitOrder(String email, String password,String productname) throws IOException {
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		//LandingPage lp = launchApplication();
		//lp.enterLoginDetail("sibaaslam@gmail.com", "Siba@1996");
		//lp.enterLoginDetail(email, password);
		lp.enterLoginDetail(input.get("email"),input.get("password"));
		ProductCatalogue pc = lp.submitLoginDetails();
		pc.getProductList();
		//pc.findTheDesiredProduct(productname);
		pc.findTheDesiredProduct(input.get("product"));
		//pc.clickTheDesiredProduct(productname);
		pc.clickTheDesiredProduct(input.get("product"));
		Thread.sleep(1000);
		CheckOut co = pc.clickOnCart();
		//boolean check = co.loadOfCart(productname);
		boolean check = co.loadOfCart(input.get("product"));
		Assert.assertTrue(check);

		ConfirmMsg cm = co.paymentDetails();

		// ConfirmMsg cm=new ConfirmMsg(driver);

		String msg = cm.getMsg();
		Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void verifyOrder() throws InterruptedException {
	
	
		lp.enterLoginDetail("sibaaslam@gmail.com", "Siba@1996");
		ProductCatalogue pc = lp.submitLoginDetails();
		order op=pc.clickonOrder();
		System.out.println(op.orderDisplayed(productname));
		Assert.assertTrue(op.orderDisplayed(productname));
	
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data=getJSONDataToMap(System.getProperty("user.dir")+"\\src\\rsa\\data\\PurchaseOrder.json");
	
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "sibaaslam@gmail.com");
//		map.put("password", "Siba@1996");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ZARA COAT 3");
//		return new Object[][] {{map},{map1}};
		
		//return new Object[][] {{"sibaaslam@gmail.com","Siba@1996","ADIDAS ORIGINAL"},{"shetty@gmail.com","Iamking@000","ZARA COAT 3"}};
		
	}

}



;