package laboratorium.uruun;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
}
