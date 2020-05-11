package laboratorium.uruun;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class LoginPage {
    public WebDriver driver;

    @FindBy(id = "search_button_homepage")
    private WebElement searchSubmit;

    @FindBy(id = "search_form_input_homepage")
    private WebElement searchInput;

    @FindBy(id = "not existing id")
    private WebElement notExisting;

    public LoginPage(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
    }

    public void search(String text) throws InterruptedException {
        searchInput.sendKeys(text);
        searchSubmit.click();
        Thread.sleep(1000);
    }

    public String getTitle() {
        return driver.getTitle();
    }


}
