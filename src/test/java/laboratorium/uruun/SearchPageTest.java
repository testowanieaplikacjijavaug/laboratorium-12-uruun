package laboratorium.uruun;

import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SeleniumExtension.class)
public class SearchPageTest {

    private static WebDriver driver;
    private static SearchPage searchPage;
    private final String BING_URL = "https://www.bing.com";
    private final String GOOGLE_URL = "https://www.google.com";

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        searchPage = PageFactory.initElements(driver, SearchPage.class);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @ValueSource(strings = {BING_URL, GOOGLE_URL})
    public void testSearchTitle(String url) throws InterruptedException {
        searchPage.navigateTo(url);
        searchPage.search("Koty");
        assertTrue(searchPage.getTitle().contains("Koty"));
    }

    @ParameterizedTest
    @ValueSource(strings = {BING_URL, GOOGLE_URL})
    public void testSearchInput(String url) throws InterruptedException {
        searchPage.navigateTo(url);
        searchPage.search("Koty");
        assertTrue(searchPage.getSearchValue().contains("Koty"));
    }
}
