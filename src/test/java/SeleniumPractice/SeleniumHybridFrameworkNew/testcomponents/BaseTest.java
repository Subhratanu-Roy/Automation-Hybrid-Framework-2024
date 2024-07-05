package SeleniumPractice.SeleniumHybridFrameworkNew.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.LandingPage;
import SeleniumPractice.SeleniumHybridFrameworkNew.pageobjects.ProductCataloguePage;

public class BaseTest {

	public WebDriver driver;
	public ProductCataloguePage prodCataloguePage;
	Properties properties;
	String useremail;
	String password;

	public WebDriver initializeDriver() throws FileNotFoundException, IOException {

		properties = new Properties();
		String path = System.getProperty("user.dir")
				+ "\\src\\main\\java\\SeleniumPractice\\SeleniumHybridFrameworkNew\\resources\\GlobalData.properties";
		properties.load(new FileInputStream(path));
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: properties.getProperty("browser");
		useremail = properties.getProperty("useremail");
		password = properties.getProperty("password");
		System.out.println("Browser: " + browsername);
		if (browsername.contains("chrome")) {
			if (browsername.contains("headless")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless"); 
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));
			} else
				driver = new ChromeDriver();

		} else if (browsername.contains("firefox")) {
			if (browsername.contains("headless")) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				driver = new FirefoxDriver(options);
			} else
				driver = new FirefoxDriver();

		} else if (browsername.contains("edge")) {
			if (browsername.contains("headless")) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless");
				driver = new EdgeDriver(options);
			} else
				driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	} 

	@BeforeClass(alwaysRun = true)
	public ProductCataloguePage launchApplication() throws FileNotFoundException, IOException {
		driver = initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		prodCataloguePage = landingPage.loginApplication(useremail, password);
		return prodCataloguePage;

	}

	public List<HashMap<String, Object>> jsonDataToMap(String filepath) throws IOException {

		String data = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8); // encoding format to
		// write as string
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, Object>> finalData = mapper.readValue(data,
				new TypeReference<List<HashMap<String, Object>>>() {

				});
		return finalData;

	}

	public String getScreenshotPath(String testname, WebDriver driver) throws IOException {

		String currenttime = new SimpleDateFormat("dd-MM-yyyy-hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String sspath = System.getProperty("user.dir") + "\\Screenshots\\" + testname + "_" + currenttime + ".png";
		File dest = new File(sspath);
		FileUtils.copyFile(file, dest);
		return sspath;

	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		driver.close();
	}

}
