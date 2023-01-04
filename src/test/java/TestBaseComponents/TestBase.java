package TestBaseComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public WebDriver driver;
	public LandingPage LandingPage;
	
	public WebDriver InitializeDriver() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
		prop.load(fis);
		String BrowserName=prop.getProperty("browser");
		
		if(BrowserName.equalsIgnoreCase("Chrome"))
		{	
		WebDriverManager.chromedriver().setup();
	      driver=new ChromeDriver();	
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
		      driver=new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.edgedriver().setup();
		      driver=new EdgeDriver();
		     
		}
		driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	     return driver;
	}
	
	public List<HashMap<String, String>> getJsondataToMap(String FilePath) throws IOException
	{
		//read json to string
		String jsonContent= FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		//convert string to hashmap -need Jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user/dir")+"//Reports//"+ testCaseName +".png");
		FileUtils.copyFile(Source,file);
		return System.getProperty("user.dir")+ "//reports//"+ testCaseName +".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
	 driver=InitializeDriver();
	   LandingPage=new LandingPage(driver);
     LandingPage.GoToUrl();
     return LandingPage;
	}

	@AfterMethod(alwaysRun=true)
 public void tearDown()
  {
	  driver.close();
  }
}
