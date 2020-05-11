package laboratorium.uruun;

import io.github.bonigarcia.seljup.SeleniumExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SeleniumExtension.class)
public class DDGPageTest {

    private static WebDriver driver;
    private static DDGPage ddgpage;

    @BeforeAll
    public static void setUpDriver(){
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        ddgpage = PageFactory.initElements(driver, DDGPage.class);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testTitlePage() {
        assertEquals("DuckDuckGo — Privacy, simplified.", ddgpage.getTitle());
    }

    @Test
    public void testSearch() throws InterruptedException {
        ddgpage.search("Koty");
        assertTrue(ddgpage.getTitle().contains("Koty"));
    }

    @Test
    public void testNotExisting() {
        WebElement notExisting = ddgpage.getNotExisting();
        assertThat(notExisting).isNotNull();
    }
}
