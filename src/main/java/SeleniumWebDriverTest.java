import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class SeleniumWebDriverTest {

    private static ChromeDriverService service;
    private WebDriver driver;


    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File ("chromedriver"))
                .usingAnyFreePort()
                .build();
        service.start();
    }


    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {

        driver.quit();
    }

    @Test
    public void testOpenGoogleSearchEnginePage() {
        driver.get("http://www.google.com");
        assertEquals ("Google", driver.getTitle ());
    }

    @Test
    public void testInputSearchEngine(){
        driver.get("http://www.google.com");
        WebElement searchBox = driver.findElement (By.name ("q"));
        searchBox.sendKeys ("cnn");
        searchBox.submit ();

        assertEquals("cnn - Google Search", driver.getTitle ());
    }

    @Test
    public void testClickCnnWebsite(){
        driver.get ("https://www.google.com/search?safe=active&source=hp&ei=Te4FWtatHsHujwPQ-YKYDw&q=cnn&oq=cnn&gs_l=psy-ab.3..35i39k1j0i67k1j0i20i263i264k1j0i131k1j0i67k1j0i131k1l2j0i67k1j0j0i131k1.924.1513.0.1730.4.3.0.0.0.0.90.217.3.3.0....0...1.1.64.psy-ab..1.3.216.0...0.x610Gs244rk");
        WebElement clickCnnLink = driver.findElement (By.linkText ("CNN - Breaking News, Latest News and Videos"));
        clickCnnLink.click ();

        assertEquals("CNN - Breaking News, Latest News and Videos", driver.getTitle ());


    }



}
