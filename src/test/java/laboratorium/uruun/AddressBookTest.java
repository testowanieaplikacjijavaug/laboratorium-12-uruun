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
    private static FirefoxOptions options;

    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.firefoxdriver().setup();
        options = new FirefoxOptions();
        options.setHeadless(true);
    }

    @BeforeEach
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        addressBook = PageFactory.initElements(driver, AddressBook.class);
        addressBook.login();
    }

    @AfterEach
    public void teardown() throws InterruptedException {
        addressBook.destroyAddresses();
        addressBook.signout();
        driver.quit();
    }

    @Test
    public void testLogin() {
        assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/");
    }

    @Test
    public void testSignout() {
        addressBook.signout();
        assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/");
    }

    @Test
    public void testEmptyAddressesPage() {
        addressBook.clickAddresses();
        assertAll(
                () -> assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/addresses"),
                () -> assertThat(addressBook.tableEmpty()).isTrue()
        );
    }

    @Test
    public void testCreateAddresses() {
        addressBook.clickAddresses();
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name", "surname", "address 1", "address 2", "city", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddresses();
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name2", "surname2", "address 1", "address 2", "city2", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddresses();
        List<WebElement> addr1 = addressBook.getAddress(1);
        List<WebElement> addr2 = addressBook.getAddress(2);
        assertAll(
                () -> assertThat(addr1.get(0).getText()).isEqualTo("name"),
                () -> assertThat(addr1.get(1).getText()).isEqualTo("surname"),
                () -> assertThat(addr1.get(2).getText()).isEqualTo("city"),
                () -> assertThat(addr1.get(3).getText()).isEqualTo("AL"),
                () -> assertThat(addr2.get(0).getText()).isEqualTo("name2"),
                () -> assertThat(addr2.get(1).getText()).isEqualTo("surname2"),
                () -> assertThat(addr2.get(2).getText()).isEqualTo("city2"),
                () -> assertThat(addr2.get(3).getText()).isEqualTo("AL")
        );
    }

    @Test
    public void testCreateAddressShow() throws InterruptedException {
        addressBook.clickAddresses();
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name", "surname", "address 1", "address 2", "city", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddresses();
        List<WebElement> addr1 = addressBook.getAddress(1);
        assertAll(
                () -> assertThat(addr1.get(0).getText()).isEqualTo("name"),
                () -> assertThat(addr1.get(1).getText()).isEqualTo("surname"),
                () -> assertThat(addr1.get(2).getText()).isEqualTo("city"),
                () -> assertThat(addr1.get(3).getText()).isEqualTo("AL"),
        );
    }

    @Test
    public void testComeBackFromAddress() {
        addressBook.clickAddresses();
        addressBook.newAddressLink().click();
        addressBook.fillAddress("name", "surname", "address 1", "address 2", "city", "zip");
        addressBook.confirmAddress();
        addressBook.clickAddressList();
        assertThat(addressBook.getURL()).isEqualTo("http://a.testaddressbook.com/addresses");
    }
}
