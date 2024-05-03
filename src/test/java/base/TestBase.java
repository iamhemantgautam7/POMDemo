package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBase {

	private static TestBase testBase;
	private static WebDriver driver;
	private static Properties prop;

	private TestBase() throws IOException {

		prop = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//configFile//config.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		fis.close();

		String strBrowser = prop.getProperty("browser");

		// String strBrowser = "edge";
		if (strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (strBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();

	}

	public static void initDriver() throws IOException {
		if (testBase == null) {
			testBase = new TestBase();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void openUrl(String url) {
		driver.get(url);
	}

	public static void tearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		testBase = null;

	}

}