package SeleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

WebDriver driver;
	
	
	
	

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	
	
	public void selectCountry(String countryname)
	{
		Actions act=new Actions(driver);
	     act.sendKeys(country,countryname).build().perform();
	     waitForElementToAppear(By.cssSelector(".ta-results"));
	     selectCountry.click();
}
	public ConfirmPage submitOrder()
	{
		submit.click();
		return new ConfirmPage(driver);
		
	}
}