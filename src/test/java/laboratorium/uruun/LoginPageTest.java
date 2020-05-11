package laboratorium.uruun;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest {
    static WebDriver driver;
    LoginPage page;

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @BeforeEach
    public void setup() {
        page = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void wpLoginError()
    {
        driver.navigate().to("https://profil.wp.pl/");
        page.login("username", "pass");
        assertThat(page.getError()).isTrue();
    }

    @Test
    public void onetLoginError()
    {
        driver.navigate().to("https://konto.onet.pl/login.html");
        page.login("username", "pass");
        assertThat(page.getError()).isTrue();
    }
}
