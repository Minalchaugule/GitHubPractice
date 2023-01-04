package SeleniumPractice.SeleniumFrameworkDesign;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     WebDriverManager.chromedriver().setup();
     WebDriver driver=new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     driver.get("https://rahulshettyacademy.com/client");
    
     
     driver.findElement(By.id("userEmail")).sendKeys("mnlptl84@gmail.com");
     driver.findElement(By.id("userPassword")).sendKeys("Minal@123");
     driver.findElement(By.id("login")).click();
     
     
     String productName="ZARA COAT 3";
     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
     List <WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
     
     //find product whose name is zara coat 3 and click on add to cART 
     WebElement prod=products.stream().filter(product-> 
     product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
     prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
     
     //VERIFY toast message and wait till loading gets disappear
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
     wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
     driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
     
     //verify if added product is in cart list
    List <WebElement> cartproducts =driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
    
    Boolean match =cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equals(productName));
     Assert.assertTrue(match);
     
     //click on check out 
     driver.findElement(By.cssSelector(".totalRow button")).click();
     Actions act=new Actions(driver);
     act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
   driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
   //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
   driver.findElement(By.cssSelector(".action__submit")).click();
   
  String Confirmmsg =driver.findElement(By.cssSelector(".hero-primary")).getText();
  Assert.assertTrue(Confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
   driver.close();  
     
	}

}
