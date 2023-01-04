package SeleniumPractice.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageObjects.ConfirmPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.OrderPage;
import SeleniumFrameworkDesign.pageObjects.PaymentPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalog;
import SeleniumFrameworkDesign.pageObjects.cartPage;
import TestBaseComponents.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseOrderTest extends TestBase {

	String productName="ZARA COAT 3";
	@Test(dataProvider="getData" ,groups={"Purchase"}) 
		
		//public void Submitorder(String email,String password ,String productName) throws IOException, InterruptedException
		public void Submitorder(HashMap<String,String> input) throws IOException, InterruptedException
		{
		
     ProductCatalog productCatalog=LandingPage.LoginApplication(input.get("email"),input.get("password"));
     
     //find product whose name is zara coat 3 and click on add to cART 
    List<WebElement>products=productCatalog.getProductList();
    
    productCatalog.addProductToCart(input.get("productName"));
    cartPage cartPage=productCatalog.goToCartPage();
    
    Boolean match=cartPage.verifyCartProducts(input.get("productName"));
     Assert.assertTrue(match);
     PaymentPage PaymentPage=cartPage.goToCheckout();
     PaymentPage.selectCountry("India");
     ConfirmPage ConfirmPage=PaymentPage.submitOrder();
   
  String Confirmmsg =ConfirmPage.getConfirmationMessage();
  Assert.assertTrue(Confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   
	}
	
	@Test(dependsOnMethods= {"Submitorder"})
	 public void OrderHistoryTest() throws InterruptedException {
		 ProductCatalog productCatalog=LandingPage.LoginApplication("mnlptl84@gmail.com", "Minal@123");	
		 OrderPage OrderPage=productCatalog.OrderPage();
		 Assert.assertTrue(OrderPage.OrderPageHistory(productName));
		 
	}
	
	//
	
//	@DataProvider
//	public Object[][] getData()
//	{
//	return new Object[][] {{"mnlptl84@gmail.com","Minal@123","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "mnlptl84@gmail.com");
//		map.put("password", "Minal@123");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>>data =getJsondataToMap(System.getProperty("user/dir")+"//src//test//java//SeleniumFrameworkDesignData//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

}
