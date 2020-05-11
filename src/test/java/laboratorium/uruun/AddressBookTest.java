package laboratorium.uruun;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
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
    public void teardown() throws InterruptedException {
        addressBook.destroyAddresses();
        addressBook.signout();
    }

    @Test
    @Disabled
    public void testLogin() throws InterruptedException {
        assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/");
    }

    @Test
    @Disabled
    public void testEmptyAddressesPage() throws InterruptedException {
        addressBook.clickAddresses();
        assertAll(
                () -> assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/addresses"),
                () -> assertThat(addressBook.tableEmpty()).isTrue()
        );
    }

    @Test
    @Disabled
    public void testCreateAddress() {
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name", "surname", "address 1", "address 2", "city", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddresses();
        List<WebElement> addr = addressBook.getAddress(0);
        assertAll(
                () -> assertThat(addr.get(0).getText()).isEqualTo("name"),
                () -> assertThat(addr.get(1).getText()).isEqualTo("surname"),
                () -> assertThat(addr.get(2).getText()).isEqualTo("city"),
                () -> assertThat(addr.get(3).getText()).isEqualTo("AL")
        );
    }

    @Test
    @Disabled
    public void testCreateAddressShow() {
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name", "surname", "address 1", "address 2", "city", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddresses();
        List<WebElement> addr = addressBook.getAddress(0);
        assertAll(
                () -> assertThat(addr.get(0).getText()).isEqualTo("name"),
                () -> assertThat(addr.get(1).getText()).isEqualTo("surname"),
                () -> assertThat(addr.get(2).getText()).isEqualTo("city"),
                () -> assertThat(addr.get(3).getText()).isEqualTo("AL")
        );
    }

}
