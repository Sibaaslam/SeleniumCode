package rsa.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rsa.pageobjects.BaseTest;
import rsa.pageobjects.CheckOut;
import rsa.pageobjects.ConfirmMsg;
import rsa.pageobjects.LandingPage;
import rsa.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest {
	LandingPage lp;
	 ProductCatalogue pc;
	 ConfirmMsg cm;
	@Given("I landed on E commerce page")
	public void i_landed_on_E_commerce_page() throws IOException{
		lp=launchApplication();
	}
	 
	
	
	@Given("^logged with username(.+) and password(.+)$")
	public void logged_with_username_password(String username,String password) {
		lp.enterLoginDetail(username,password);
		pc = lp.submitLoginDetails();
	}
	
	
	 @When("^I add product(.+)$")
	 public void i_add_product(String product) {
			pc.getProductList();
			pc.findTheDesiredProduct(product);
			pc.clickTheDesiredProduct(product);
	 }
	  @When("^checkout (.+) and submit the order$")
	  public void checkout_and_submit_the_order(String product) {
		    CheckOut co = pc.clickOnCart();
			boolean check = co.loadOfCart(product);
			Assert.assertTrue(check);
			cm = co.paymentDetails();
	  }
	  
	  @Then("{string} message is displayed on the confirmation page")
	  public void  message_is_displayed_on_the_confirmation_page(String string) {
		  String msg = cm.getMsg();
		   Assert.assertTrue(msg.equalsIgnoreCase(string));
	  }
	  	  
	  @Then("{string} msg should be displayed")
	  public void msg_should_be_displayed(String string) {
		  Assert.assertEquals(lp.errorMessgae(),string);
	  }
	 

}
