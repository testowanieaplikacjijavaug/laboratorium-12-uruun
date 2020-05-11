package laboratorium.uruun;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AddressBook {
    public WebDriver driver;
    private final String email = "chepuhodoc@wemel.site";
    private final String password = "testpass";



    public AddressBook(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void login() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.navigate().to("http://a.testaddressbook.com/sign_in");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("session_email")));
        emailInput().sendKeys(email);
        passInput().sendKeys(password);
        passInput().sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    public void signout() {
        signOut().click();
    }

    public void clickAddresses() {
        addresses().click();
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

    public boolean tableEmpty() {
        return tableBody().findElements(By.cssSelector("*")).isEmpty();
    }

    public void fillAddress(String first, String last, String address1, String address2, String city, String zip) {
        firstName().sendKeys(first);
        lastName().sendKeys(last);
        streetAddress().sendKeys(address1);
        secondaryAddress().sendKeys(address2);
        city().sendKeys(city);
        zipCode().sendKeys(zip);
    }

    public void confirmAddress() {
        create().click();
    }

    public void destroyAddresses() throws InterruptedException {
        clickAddresses();
        WebElement destroy;
        while (!driver.findElements(By.linkText("Destroy")).isEmpty()) {
            destroy = driver.findElement(By.linkText("Destroy"));
            destroy.click();
            System.out.println(driver.switchTo().alert().getText());
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        }
    }

    public List<WebElement> getAddress(int id) {
        return driver.findElement(By.xpath("/html/body/div/table/tbody/tr["+id+"]")).findElements(By.cssSelector("*"));
    }

    private WebElement emailInput() {
        return driver.findElement(By.id("session_email"));
    };

    private WebElement passInput() {
        return driver.findElement(By.id("session_password"));
    };

    private WebElement signOut() {
        return driver.findElement(By.linkText("Sign out"));
    };

    private WebElement addresses() {
        return driver.findElement(By.linkText("Addresses"));
    };

    private WebElement tableBody() {
        return driver.findElement(By.tagName("tbody"));
    };

    public WebElement newAddressLink() {
        return driver.findElement(By.linkText("New Address"));
    };

    private WebElement firstName() {
        return driver.findElement(By.id("address_first_name"));
    };

    private WebElement lastName() {
        return driver.findElement(By.id("address_last_name"));
    };

    private WebElement streetAddress() {
        return driver.findElement(By.id("address_street_address"));
    };

    private WebElement secondaryAddress() {
        return driver.findElement(By.id("address_secondary_address"));
    };

    private WebElement city() {
        return driver.findElement(By.id("address_city"));
    };

    private WebElement zipCode() {
        return driver.findElement(By.id("address_zip_code"));
    };

    private WebElement create() {
        return driver.findElement(By.xpath("/html/body/div/div/div/form/div[17]/input"));
    }
}
