package laboratorium.uruun;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {
    public WebDriver driver;

    @FindBy(name = "q")
    private WebElement searchInput;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public void search(String text) throws InterruptedException {
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getSearchValue() {
        return searchInput.getAttribute("value");
    }
}
