package laboratorium.uruun;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    public WebDriver driver;

    @FindBy(id = "search_button_homepage")
    private WebElement searchSubmit;

    @FindBy(id = "search_form_input_homepage")
    private WebElement searchInput;

    @FindBy(id = "not existing id")
    private WebElement notExisting;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
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
