package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent{

	
WebDriver driver;
	
	public cartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement CheckoutEle;

	
	public Boolean verifyCartProducts(String productName)
	{
		 Boolean match =cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equals(productName));
	    return match;    
	}
	
	public PaymentPage goToCheckout()
	{
	CheckoutEle.click();
	PaymentPage PaymentPage=new PaymentPage(driver);
	return PaymentPage;
	}
}
