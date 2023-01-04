package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	
WebDriver driver;
	
	public OrderPage(WebDriver driver)
	
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProductNames;
	

	public Boolean OrderPageHistory(String productName) throws InterruptedException
	{
		 Boolean match =ProductNames.stream().anyMatch(cartproduct->cartproduct.getText().equals(productName));
	    return match;    
	}
	

}