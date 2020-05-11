package laboratorium.uruun;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AddressBookTest {
    private static WebDriver driver;
    private AddressBook addressBook;

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
    public void setUp() throws InterruptedException {
        addressBook = PageFactory.initElements(driver, AddressBook.class);
        addressBook.login();
    }

    @AfterEach
    public void teardown() {
        addressBook.signout();
    }

    @Test
    public void testLogin() throws InterruptedException {
        assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/");
    }

    @Test
    public void testEmptyAddressesPage() throws InterruptedException {
        addressBook.clickAddresses();
        assertAll(
                () -> assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/addresses"),
                () -> assertThat(addressBook.tableEmpty()).isTrue()
        );
    }


}
