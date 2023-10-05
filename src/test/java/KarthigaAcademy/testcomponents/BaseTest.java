package KarthigaAcademy.testcomponents;

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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import karthigaacademy.pageobjects.LandingPage;
import karthigaacademy.pageobjects.ProductCatalogue;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver InitializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\karthigaacademy\\resources\\Global.properties");
		prop.load(file);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions option=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless"))
			{
				option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File destfile=new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, destfile);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}


	public List<HashMap<String, String>> getJsonDataToMap(String fileName) throws IOException
	{
		//Read Json to String
	String jsonToStringcontent=  FileUtils.readFileToString
			(new File(fileName),StandardCharsets.UTF_8);
	
	//String to hashmap--jackson databind
	ObjectMapper objmap=new ObjectMapper();
	List<HashMap<String,String>> data=objmap.readValue(jsonToStringcontent, new TypeReference<List<HashMap<String,String>>>(){
	});
	
	return data;
	}

	@BeforeMethod(alwaysRun=true)
	public LandingPage lanchApplication() throws IOException {
		driver = InitializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goInto();
		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
}
