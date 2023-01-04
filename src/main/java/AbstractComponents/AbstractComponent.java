package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageObjects.OrderPage;
import SeleniumFrameworkDesign.pageObjects.cartPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
	
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
     wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
	
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
     wait.until(ExpectedConditions.visibilityOf(findBy));
}
	public void waitForElementToBeDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
//		 wait.until(ExpectedConditions.invisibilityOf(ele));	
	}
	public cartPage goToCartPage()
	{
	cartHeader.click();
	cartPage cartPage=new cartPage(driver);
	return cartPage;
	}
	
public OrderPage OrderPage()
	{
	orderHeader.click();	
	OrderPage OrderPage=new OrderPage(driver);
	return OrderPage;
	}
	
	public void CountryDropdown()
	{
		Actions act=new Actions(driver);
	     act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
	}
}