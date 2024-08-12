package rsa.pageobjects;

import java.io.File;
import java.io.FileInputStream;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public  WebDriver driver;
	LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\SeleniumE2EMaven\\src\\rsa\\GlobalData.Properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String browserName=	System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		
		//if(browserName.equalsIgnoreCase("chrome")) {
		if(browserName.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			//System.setProperty("webdriver.chrome.driver", "C:/Users/HP/Desktop/Selenium/chromedriver.exe");
		
		 driver = new ChromeDriver(options);	
		 driver.manage().window().setSize(new Dimension(1440,900));
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:/Users/HP/Desktop/Selenium/msedgedriver.exe");
			 driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			//	firefox code
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	

	private Dimension newDimensions(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<HashMap<String, String>> getJSONDataToMap(String file) throws IOException {
		//read json to String
		String jsonContent=FileUtils.readFileToString(new File(file), StandardCharsets.UTF_8);
		//String to HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String takeScreenshot(String testcaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(src, file);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initializeDriver();
	    lp=new LandingPage(driver);
		lp.goTo();
		return lp;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
}
