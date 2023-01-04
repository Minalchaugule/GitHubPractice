package SeleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword")).sendKeys("Minal@123");
	//driver.findElement(By.id("login")).click();
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginbutton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement ErrorMessage;
	
	public ProductCatalog LoginApplication(String email,String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginbutton.click();
		ProductCatalog productCatalog=new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void GoToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(ErrorMessage);
		ErrorMessage.getText();	
		return ErrorMessage.getText();
	}
	
}
