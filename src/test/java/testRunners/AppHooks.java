package testRunners;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AppHooks {
    @Getter
    private static WebDriver driver;
    @Getter
    private static Properties properties;

    @BeforeAll(order = 1)
    public static void propertiesInit() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(System.getProperty("user.dir") + "//src//test//resources//globalData.properties"));
    }

    @BeforeAll(order = 2)
    public static void driverInit() throws IOException {
        String browserName = properties.getProperty("browser");
        String applicationUrl = properties.getProperty("applicationUrl");
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(applicationUrl);
    }

    @AfterAll(order = 1)
    public static void tearDown() {
        driver.quit();
    }
}
