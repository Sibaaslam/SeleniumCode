package rsa.pageobjects;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends BaseTest {
	
	String productname = "ADIDAS ORIGINAL";
	
	@Test(groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException {
		lp.enterLoginDetail("sibaaslam@gmail.com", "Si1996");
		ProductCatalogue pc = lp.submitLoginDetails();
		Thread.sleep(1000);
		Assert.assertEquals(lp.errorMessgae(),"Incorrect email or password.");
	}
	
	@Test(groups= {"Error Handling"})
	public void productValidationTest() throws InterruptedException {
		lp.enterLoginDetail("shetty@gmail.com", "Iamking@000");
		ProductCatalogue pc = lp.submitLoginDetails();
		pc.getProductList();
		pc.findTheDesiredProduct(productname);
		pc.clickTheDesiredProduct(productname);
       // Thread.sleep(1000);
		CheckOut co = pc.clickOnCart();
		boolean check = co.loadOfCart("ZARA");

		Assert.assertFalse(check);
	}

}
