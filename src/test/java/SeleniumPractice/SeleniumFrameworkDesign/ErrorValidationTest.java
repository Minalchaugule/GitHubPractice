package SeleniumPractice.SeleniumFrameworkDesign;

import java.io.IOException;
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
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.pageObjects.ConfirmPage;
import SeleniumFrameworkDesign.pageObjects.LandingPage;
import SeleniumFrameworkDesign.pageObjects.PaymentPage;
import SeleniumFrameworkDesign.pageObjects.ProductCatalog;
import SeleniumFrameworkDesign.pageObjects.cartPage;
import TestBaseComponents.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends TestBase {

	
	@Test (groups= {"ErrorHandling"})
	
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
	String productName="ZARA COAT 3";
 ProductCatalog productCatalog=LandingPage.LoginApplication("anshika@gmail.com", "Iamking@000");
 
 //find product whose name is zara coat 3 and click on add to cART 
List<WebElement>products=productCatalog.getProductList();

productCatalog.addProductToCart(productName);
cartPage cartPage=productCatalog.goToCartPage();

Boolean match=cartPage.verifyCartProducts("ZARA COAT 33");
 Assert.assertFalse(match);
}
	
	@Test 
	public void Submitorder() throws IOException, InterruptedException
	{
	String productName="ZARA COAT 3";
 LandingPage.LoginApplication("mnlptl84@gmail.com", "Minal@12");
 LandingPage.getErrorMessage();
 Assert.assertEquals("Incorrect email or password.","LandingPage.getErrorMessage()");
 
}
}