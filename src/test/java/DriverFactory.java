import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class DriverFactory {
     public WebDriver driver;

    @BeforeMethod
    public void setup(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://www.kurs-selenium.pl/demo/");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
