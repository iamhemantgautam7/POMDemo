package testScripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.ProductListPage;

public class PlaceOrderTest {
	
	WebDriver driver;
	LoginPage loginPage;
	ProductListPage productListPage;
	CartPage cartPage;
	CheckOutPage checkOutPage;
	
	
	public PlaceOrderTest() throws IOException {
		TestBase.initDriver();
		driver=TestBase.getDriver();
		loginPage =new LoginPage(driver);
		productListPage=new ProductListPage(driver);
		cartPage=new CartPage(driver);
		checkOutPage=new CheckOutPage(driver);
		
		
	}
	
	@BeforeTest
	public void setup() {
		TestBase.openUrl("https://www.saucedemo.com/");
	}
	
  @Test(priority =1)
  public void validLogin() {
	  
	  loginPage.login("standard_user", "secret_sauce");
	 
	  
	  
  }
  @Test(priority =2)
  public void addItem() {
	  productListPage.addToCart();
	  productListPage.viewCart();
	//  productListPage.isOnProducts();
  }
  
  @Test(priority=3)
  public void checkoutTest() {
	  System.out.println(cartPage.isItemAdded());
	  cartPage.checkoutItem();
  }
  
  @Test(priority=4)
  public void completeOrder() {
	  checkOutPage.enterDetails("Hemant", "Gautam", "208017");
	  checkOutPage.continueOrder();
	  checkOutPage.finish();
	  
	  
	  Assert.assertEquals(checkOutPage.getSuccessMsg(), "Thank you for your order!");
	  
	 
  }
  
  
  
 
  
}
